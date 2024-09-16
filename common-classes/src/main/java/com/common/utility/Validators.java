package com.common.utility;

public class Validators {

	public static boolean validIntegerForId( Integer integer ) {
		return integer != null && integer > 0;
	}
}
