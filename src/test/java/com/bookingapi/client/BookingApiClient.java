package com.bookingapi.client;

import com.bookingapi.utils.ConfigReader;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

//the class that talks to the API

public class BookingApiClient {
	/*
	 * useRelaxedHTTPSValidation() tells RestAssured
	 * "don't verify the SSL certificate's authenticity, just trust the connection and proceed."
	 * One line, applied once via a static block (same pattern you saw earlier in
	 * ConfigReader 	 runs automatically the first time the class is used).
	 */
	static {
		io.restassured.RestAssured.useRelaxedHTTPSValidation();
	}

	private static final String BASE_URL = ConfigReader.get("booking.base.url");

	private static final RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri(BASE_URL)
			.setContentType("application/json").build();

	private static final ResponseSpecification resSpec = new ResponseSpecBuilder().build();

	public Response createBooking(Object bookingPayload) {
		return given().spec(reqSpec).body(bookingPayload).when().post("/booking").then().spec(resSpec).extract()
				.response();
	}

	public Response getBooking(int bookingID) {
		return given().spec(reqSpec).when().get("/booking/" + bookingID).then().spec(resSpec).extract().response();
	}

	public Response deleteBooking(int bookingID, String authToken) {
		return given().spec(reqSpec).header("Cookie", "token=" + authToken).when().delete("/booking/" + bookingID)
				.then().spec(resSpec).extract().response();
	}

	public String getAuthToken(String username, String password) {
		String body = "{ \"username\" : \"" + username + "\" , \"password\" : \"" + password + "\"}";
		Response authResponse = given().spec(reqSpec).body(body).when().post("/auth");

		return authResponse.jsonPath().getString("token");
	}

	public Response updateBooking(int bookingId, Object bookingPayload, String token) {
		return given().spec(reqSpec).header("Cookie", "token=" + token).body(bookingPayload).when()
				.put("/booking/" + bookingId).then().spec(resSpec).extract().response();
	}
	
	public Response searchBooking(String firstname, String lastname) {
		return given().spec(reqSpec).queryParam("firstname",firstname).queryParam("lastname", lastname).when().get("/booking");	
		
	}
}
/*
 * Why return Response instead of checking pass/fail inside this class? This
 * class's only job is "make the call, hand back what came back." The checking
 * (was it 200? was the body correct?) belongs in your step definitions/tests 
 * this keeps each file doing one job, which is exactly the
 * separation-of-concerns point to probe for.
 */
