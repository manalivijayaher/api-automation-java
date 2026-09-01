package com.bookingapi.client;

import com.bookingapi.utils.ConfigReader;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class ExerciseApiClient {

	private static final RequestSpecification  requestSpec = new RequestSpecBuilder().setBaseUri(ConfigReader.get("exercise.base.url")).build();
	
	public Response getAllProducts() {
		return given().spec(requestSpec).when().get("/api/productsList");
	}
	
	
}
