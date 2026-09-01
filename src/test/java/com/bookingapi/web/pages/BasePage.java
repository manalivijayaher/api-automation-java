package com.bookingapi.web.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Locator strategy: every page object should declare a PRIMARY locator
//(a data-test attribute where possible - stable, automation-intended)
//and a FALLBACK locator (CSS class or similar), passed together into
//findWithFallback() below.

public class BasePage {
	protected final WebDriver driver;
	protected final WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	protected WebElement findWithFallback(By... locators) {
		Exception lastException = null;

		for (By locator : locators) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				return driver.findElement(locator);
			} catch (Exception e) {
				lastException = e;
			}
		}
		throw new RuntimeException("None of the provided locators matched an element.", lastException);

	}

}
