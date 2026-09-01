package com.bookingapi.stepdefs.hybrid;

import java.util.List;
import java.util.Map;

import com.bookingapi.client.ExerciseApiClient;
import com.bookingapi.web.DriverFactory;
import com.bookingapi.web.pages.HomePage;
import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class ProductAvailabilitySteps {

	private final ExerciseApiClient eClient = new ExerciseApiClient();
	private HomePage homePage;

	@Given("the product {string} with price {string} exists via the products API")
	public void verifyIfProductExist(String productName, String productPrice) {
		Response response = eClient.getAllProducts();

		List<Map<String, Object>> products = response.jsonPath().getList("products");

		boolean isFound = false;

		for (Map<String, Object> product : products) {
			String name = (String) product.get("name");
			String price = (String) product.get("price");
			if (name.equals(productName) && price.equals(productPrice)) {
				isFound = true;
				break;
			}
		}
		// check if list contains desired product name.
		assertThat(isFound).isTrue();
	}

	@When("user opens the storefront homepage")
	public void openHomePage() {
		homePage = new HomePage(DriverFactory.getDriver());
		homePage.open();
	}

	@Then("{string} should be visible with price {string} in the product listing page")
	public void verifyIfProductIsVisible(String productName) {
		assertThat(homePage.getVisibleProductNames().equals(productName));

	}

}
