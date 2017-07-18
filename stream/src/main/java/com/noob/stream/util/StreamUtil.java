package com.noob.stream.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noob.core.util.JsonUtil;
import com.noob.stream.constant.ExceptionCode;
import com.noob.stream.constant.LogPrefix;
import com.noob.stream.constant.ResultCode;
import com.noob.stream.model.Result;
import com.noob.stream.model.SelfException;

public class StreamUtil {

	private static Logger logger = LoggerFactory.getLogger(StreamUtil.class);

	private static String LOG = "{} {}";
	private static String LOG_BEGIN = LOG + " reqInfo: {}";
	private static String LOG_END = LOG + " cost:{}ms, response:{}";

	/**
	 * 获取对象
	 */
	public static <T> T getObjectFromOptional(Optional<T> optional) {
		return optional != null && optional.isPresent() ? optional.get() : null;
	}

	/**
	 * 带结果码返回的统一处理过程
	 * 
	 * @param topic
	 *            主题
	 * @param logInfo
	 *            日志信息
	 * @param validatePorcess
	 *            校验方法
	 * @param executeProcess
	 *            执行方法
	 * @param exceptionResult
	 *            异常后的结果码
	 * @param exceptionCode
	 *            异常错误信息码
	 * @return
	 */
	public static <T extends Result> T executeExceptionReturnUnknown(String topic,
			Supplier<String> logInfo, Supplier<Boolean> validatePorcess, Callable<T> executeProcess,
			String exceptionCode) {
		return executeReturnResult(topic, logInfo, validatePorcess, executeProcess,
				ResultCode.UNKNOWN, exceptionCode);

	}

	public static <T extends Result> T executeExceptionReturnFail(String topic,
			Supplier<String> logInfo, Supplier<Boolean> validatePorcess, Callable<T> executeProcess,
			String exceptionCode) {
		return executeReturnResult(topic, logInfo, validatePorcess, executeProcess, ResultCode.FAIL,
				exceptionCode);

	}

	/**
	 * 通用日志输出
	 * 
	 * @param topic
	 *            主题
	 * @param log
	 *            日志
	 * @param executeProcess
	 *            执行过程
	 * @throws Exception
	 */
	public static <T> void executeMethod(String topic, Supplier<String> log,
			Runnable executeProcess) {
		String logInfo = log.get();
		beginLog(topic, logInfo);

		try {
			executeProcess.run();
		} catch (Throwable e) {
			errorLog(topic, logInfo, e);
		}

		briefLog(topic);
	}

	/**
	 * 通用日志输出， ServiceLevelException
	 * 
	 * @param topic
	 *            主题
	 * @param entity
	 *            对象
	 * @param executeProcess
	 *            执行过程
	 * @throws Exception
	 */
	public static <T> void executeMethodThrowServiceLevelException(String topic, T entity,
			Runnable executeProcess) throws Exception {
		executeThrowException(topic, entity, executeProcess, SelfException.class);
	}

	private static <T extends Result> T executeReturnResult(String topic, Supplier<String> logInfo,
			Supplier<Boolean> validatePorcess, Callable<T> executeProcess, Integer exceptionResult,
			String exceptionCode) {
		return executeReturnResult(topic, logInfo,
				() -> validatePorcess == null || validatePorcess.get() ? executeProcess.call()
						: (T) ResultMessageUtil.failResult(ExceptionCode.PARA_ERROR),
				exceptionResult, exceptionCode);

	}

	private static <T extends Result> T executeReturnResult(String topic, Supplier<String> log,
			Callable<T> executeProcess, Integer exceptionResult, String exceptionCode) {

		Instant starTime = Instant.now();
		String logInfo = log.get();
		beginLog(topic, logInfo);

		T result = tryCatch(executeProcess, e -> {
			errorLog(topic, logInfo, e);
			return (T) (exceptionResult != null && exceptionResult == ResultCode.UNKNOWN
					? ResultMessageUtil.unknownResult()
					: ResultMessageUtil.failResult(exceptionCode));
		});

		endLog(topic, starTime, result);

		return result;
	}

	/**
	 * 捕获异常
	 * 
	 * @param executeProcess
	 *            执行过程
	 * @param exectionMethod
	 *            异常之后的执行方式
	 */
	private static <T extends Result> T tryCatch(Callable<T> executeProcess,
			Function<Exception, T> exectionMethod) {
		T result = null;
		try {
			result = executeProcess.call();
		} catch (Exception e) {
			result = exectionMethod.apply(e);
		}
		return result;
	}

	/**
	 * 根据指定的异常抛出
	 */
	private static <T> void executeThrowException(String topic, T entity, Runnable executeProcess,
			Class<? extends Exception> exceptionClass) throws Exception {

		String jsonStr = JsonUtil.toJson(entity);
		beginLog(topic, jsonStr);

		try {
			executeProcess.run();
		} catch (Throwable e) {
			errorLog(topic, jsonStr, e);
			throw exceptionClass == null ? new Exception(e)
					: exceptionClass.getDeclaredConstructor(Throwable.class).newInstance(e);
		}

		briefLog(topic);
	}

	private static void beginLog(String topic, String jsonStr) {
		logger.info(LOG_BEGIN, topic, LogPrefix.BEGIN, jsonStr);
	}

	private static void briefLog(String topic) {
		logger.info(LOG, topic, LogPrefix.END);
	}

	/**
	 * 异常日志
	 * 
	 * @param topic
	 *            主题
	 * @param jsonStr
	 *            异常数据
	 * @param e
	 *            异常
	 */
	private static void errorLog(String topic, String jsonStr, Throwable e) {
		logger.error(LOG_BEGIN, topic, LogPrefix.EXCEPTION, jsonStr, e);
	}

	/**
	 * 方法结束日志
	 * 
	 * @param topic
	 *            主题
	 * @param starTime
	 *            开始时间
	 * @param result
	 *            返回结果
	 */
	private static <T> void endLog(String topic, Instant starTime, T reponseEntity) {
		logger.info(LOG_END, topic, LogPrefix.END,
				Duration.between(starTime, Instant.now()).toMillis(),
				JsonUtil.toJson(reponseEntity));
	}

}
