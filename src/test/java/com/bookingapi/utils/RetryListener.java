package com.bookingapi.utils;

import java.lang.reflect.*;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer {

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
/*
 * ITestAnnotation annotation - This represents the @Test annotation of your
 * test method.
 * Class testClass - This represents the class containing the test method.
 * 
 * Constructor testConstructor - This represents a constructor of the test class.
 * 
 * Method testMethod - actual Java method being tested.
 * 
 *  implement IRetryAnalyzer to define the retry logic. Then I
 * implement IAnnotationTransformer to dynamically assign the retry analyzer to
 * TestNG test methods, so I don't have to specify retryAnalyzer on every @Test.
 * Finally, I register the listener in testng.xml."
 * 
 * 
 */
