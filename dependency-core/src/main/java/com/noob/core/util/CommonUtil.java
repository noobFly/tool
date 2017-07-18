package com.noob.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by xiongwenjun on 2017/4/8.
 */
public class CommonUtil {

	public static final String EMPTY = "";
	public static final String slant = "/";

	public static final String YYMMDDHHMMSSSSS = "yyMMddHHmmssSSS";

	/**
	 * 获取一个21位长度的订单号
	 *
	 * @param pre 前缀传入3位
	 * @return
	 * @Description:
	 */
	public static String generateNumber(String pre, String format) {
		StringBuilder sbTradeNo = new StringBuilder();
		sbTradeNo.append(pre);
		sbTradeNo.append(date2String(new Date(), format)).append(generateNumber(2));
		return sbTradeNo.toString();
	}

	public static String date2String(Date date, String format) {
		if (null == date || format == null || format.trim().length() == 0) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setLenient(false); // 设置Calendar是否宽松解析字符串，如果为false，则严格解析；默认为true，宽松解析
		String time = df.format(date);
		return time;
	}

	/**
	 * 生成指定长度的纯数字随机串
	 */
	public static String generateNumber(int codeLength) {
		// 10个数字
		final int maxNum = 8;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < codeLength) {
			// 生成随机数，取绝对值，防止生成负数
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	public static String trimEmptyStr(String str) {
		return str == null ? "" : str.trim();
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);

	}

	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String slant(String path) {

		return path.startsWith(slant) ? path : slant.concat(path);
	}

}
