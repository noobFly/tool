package com.noob.stream.util;

import com.noob.stream.constant.ResultCode;
import com.noob.stream.model.Result;
import com.noob.stream.model.ResultData;

public class ResultMessageUtil {

	public static Result failResult() {
		return failResult(null);
	}

	public static Result failResult(String errorCode) {
		Result message = result();
		failResult(message, errorCode);
		return message;
	}

	public static Result failResult(String errorCode, String errorDesc) {
		Result message = result();
		failResult(message, errorCode, errorDesc);
		return message;
	}

	public static void failResult(Result result, String errorCode) {
		result(result, ResultCode.FAIL, errorCode);
	}

	public static void failResult(Result result, String errorCode, String errorDesc) {
		result(result, ResultCode.FAIL, errorCode, errorDesc, null);
	}

	public static Result successResult() {
		Result result = result();
		successResult(result);
		return result;

	}

	public static void successResult(Result result) {
		successResult(result, null);
	}

	public static void successResult(Result result, String info) {
		result(result, ResultCode.SUCCESS, null, null, info);
	}

	public static Result unknownResult() {
		Result result = result();
		unknownResult(result);
		return result;
	}

	public static void unknownResult(Result result) {
		unknownResult(result, null, null);
	}

	public static void unknownResult(Result result, String errorCode) {
		unknownResult(result, errorCode, null);
	}

	public static void unknownResult(Result result, String errorCode, String errorDesc) {
		result(result, ResultCode.UNKNOWN, errorCode, errorDesc);
	}

	public static void result(Result result, int resultCode, String errorCode) {
		result(result, resultCode, errorCode, null);
	}

	public static void result(Result result, int resultCode, String errorCode, String errorDesc) {
		result(result, resultCode, errorCode, errorDesc, null);
	}

	public static void result(Result result, int resultCode, String errorCode, String errorDesc,
			String info) {
		result.setResultCode(resultCode);
		result.setErrorCode(errorCode);
		result.setErrorDesc(errorDesc);
		result.setMessage(info);
	}

	public static <E> ResultData<E> dataResult() {
		return new ResultData<>();
	}

	public static <E> ResultData<E> successDataResult() {
		ResultData<E> result = dataResult();
		successResult(result);
		return result;
	}

	public static <E> ResultData<E> unknownDataResult() {
		ResultData<E> result = dataResult();
		unknownResult(result);
		return result;
	}

	public static Result result() {
		return new Result();
	}

}
