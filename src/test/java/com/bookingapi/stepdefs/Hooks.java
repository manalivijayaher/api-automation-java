package com.bookingapi.stepdefs;

import java.util.List;
import java.util.Map;

import com.bookingapi.utils.ConfigReader;
import com.bookingapi.utils.HealthCheckConfig;
import com.bookingapi.utils.JsonDataReader;
import com.bookingapi.web.DriverFactory;

import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Hooks {

	// health check
	@BeforeAll
	public static void checkEnvironmentHealth() throws Exception {
		String requestedTags = System.getProperty("cucumber.filter.tags", "");

		List<HealthCheckConfig> checks = JsonDataReader.readList("config/healthChecks.json", HealthCheckConfig.class);

		for (HealthCheckConfig check : checks) {
			if (!requestedTags.isEmpty() && !requestedTags.contains(check.getTag())) {
				System.out.println("Skipping health check for " + check.getName() + " (not part of this run)");
				continue;
			}

			String baseUrl = ConfigReader.get(check.getBaseUrlKey());
			if (baseUrl == null) {
				throw new IllegalStateException(check.getName()
						+ " health check failed: no base URL configured for key '" + check.getBaseUrlKey() + "'.");
			}

			Response healthCheck = RestAssured.given().baseUri(baseUrl).when().get(check.getHealthPath());

			if (healthCheck.getStatusCode() != check.getExpectedStatus()) {
				throw new IllegalStateException(check.getName() + " health check failed! URL: " + baseUrl + check.getHealthPath()
			    + " | Expected " + check.getExpectedStatus() + " but got " + healthCheck.getStatusCode()
			    + ". Aborting test run.");
			}
		}
		System.out.println("Environment health check passed. Proceeding with test suite.");
	}

	@Before
	public void beforeScenario(Scenario scenario) {
		System.out.println("Starting scenario: " + scenario.getName());
	}

	@After
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			System.out.println("Scenario Failed: " + scenario.getName());
		}

		System.out.println("Finished Scenario: " + scenario.getName());
		DriverFactory.quitDriver();
	}
}
