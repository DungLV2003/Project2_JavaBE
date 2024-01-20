package com.javaweb.utils;

import javax.validation.Path.ReturnValueNode;

public class NumberUtil {
	public static boolean isNumber(String value) {
		try {
			Long numberLong = Long.parseLong(value);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
}
