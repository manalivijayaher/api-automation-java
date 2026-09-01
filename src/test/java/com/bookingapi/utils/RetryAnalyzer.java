package com.bookingapi.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	/*
	 * The retry(ITestResult result) method gets called automatically by TestNG
	 * whenever a test fails  you just tell it "yes, try again" (return true) or
	 * "no, stop, it's a real failure" (return false). Here, we retry up to
	 * MAX_RETRY_COUNT (2) times before giving up.
	 */
	private int retryCount = 0;
	private static final int MAX_RETRY_COUNT = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < MAX_RETRY_COUNT) {
			retryCount++;
			System.out.println("Retrying Test: " + result.getName() + "Attempt: " + retryCount);
			return true;
		}
		return false;
	}

}
