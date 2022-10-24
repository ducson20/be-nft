package com.asra.developer.common.utils;

public class StringUtils {

	public static final String EMPTY = "";

	public static final String COMMA_SPACE = ", ";

	public static String nullToEmpty(String value) {
		return value != null ? value : EMPTY;
	}

	public static String nullToEmpty(Object value) {
		return value != null ? value.toString() : EMPTY;
	}

	public static boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

	public static boolean isBlank(String value) {
		return value == null || value.isEmpty();
	}

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}

}
