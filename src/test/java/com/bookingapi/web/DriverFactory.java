package com.bookingapi.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
	    if (driver.get() == null) {
	        WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();

	        if (System.getenv("CI") != null) {
	            options.addArguments("--headless=new");
	            options.addArguments("--no-sandbox");
	            options.addArguments("--disable-dev-shm-usage");
	        }

	        driver.set(new ChromeDriver(options));
	    }
	    return driver.get();
	}
	public static void quitDriver() {
		if(driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

}
