package com.bookingapi.utils;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.assertj.core.api.SoftAssertions;

public class ResponseValidator {

	public static void validateFullResponse(Response response, String schemaPath, int maxTimeMs) {

		SoftAssertions soft = new SoftAssertions();
		try {
			response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
		} catch (Exception e) {
			soft.fail("Schema Validation failed: " + e.getMessage());
		}

		soft.assertThat(response.getContentType()).as("Content-Type header should be application/json").contains("application/json");
//		soft.assertThat(response.header("Accept")).as("Accept header").contains("application/json");
		

		soft.assertThat(response.getTime()).as("Response Time").isLessThan((long) maxTimeMs);

		try {
		soft.assertAll();
		}catch(AssertionError e) {
			System.out.println("Validation failed for response: " +response.asString());
			throw e;
		}

	}

}
