package com.bookingapi.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.bookingapi.utils.ConfigReader;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);

	}

	// get all products name;

	private By productsName = By.cssSelector(".single-products .productinfo p");

	public void open() {
		driver.get(ConfigReader.get("web.base.url"));
	}

	public List<String> getVisibleProductNames() {

		wait.until(ExpectedConditions.presenceOfElementLocated(productsName));

		List<String> names = new ArrayList<>();
		for (WebElement name : driver.findElements(productsName)) {
			names.add(name.getText());
		}

		return names;
	}

	public List<String> getVisibleProductNamesPrice() {

		wait.until(ExpectedConditions.presenceOfElementLocated(productsName));

		List<String> names = new ArrayList<>();
		for (WebElement name : driver.findElements(productsName)) {
			names.add(name.getText());
		}

		return names;
	}

}
