package com.bookingapi.stepdefs;

import com.bookingapi.client.BookingApiClient;
import com.bookingapi.context.ScenarioContext;
import com.bookingapi.pojo.Booking;
import com.bookingapi.utils.BookingBuilder;
import com.bookingapi.utils.ResponseValidator;
import com.bookingapi.utils.TestUserRepository;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static org.assertj.core.api.Assertions.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BookingSteps {

	private final BookingApiClient apiClient = new BookingApiClient();
	private final ScenarioContext ctx = new ScenarioContext();
	private Booking booking;
	private Response response;
	private int bookingId;
	private String authToken;

	@Given("user have valid booking details for {string} {string} {string} {string} {string} {string} {string}")
	public void validBookingDetails(String firstname, String lastname, String totalprice, String checkin,
			String checkout, String additionalneeds, String depositpaid) {
		booking = BookingBuilder.buildBooking(firstname, lastname, Integer.parseInt(totalprice), checkin, checkout,
				additionalneeds, Boolean.parseBoolean(depositpaid));
	}

	@When("user creates a booking")
	public void createBooking() {
		System.out.println("Running on thread: " + Thread.currentThread().getName());
		response = apiClient.createBooking(booking);
		response.prettyPrint();
		ctx.set("response", response);

	}

	@Then("the booking should be created successfully")
	public void checkIfBookingCreated() {
		assertThat(response.getStatusCode()).isEqualTo(200);
	}

	/*
	 * @Then("the response should match the booking schema") public void
	 * validateSchema() {
	 * response.then().assertThat().body(matchesJsonSchemaInClasspath(
	 * "schemas/bookingResponseSchema.json")); }
	 * 
	 * @And("the response headers should be valid") public void validateHeaders() {
	 * ResponseValidator.validateHeaders(response); }
	 */

	@When("user fetches the created booking")
	public void fetchBookingID() {
		bookingId = response.jsonPath().getInt("bookingid");
		response = apiClient.getBooking(bookingId); // from here on, response means "the result of the get call," not
													// the create call anymore.
	}

	@Then("the fetched booking details should match what was created")
	public void fetchAndVerifyBookingDetails() {
		assertThat(response.jsonPath().getString("firstname")).isEqualTo(booking.getFirstname());
		assertThat(response.jsonPath().getString("lastname")).isEqualTo(booking.getLastname());
		assertThat(response.jsonPath().getInt("totalprice")).isEqualTo(booking.getTotalprice());
	}

	@When("user logs in as admin")
	public void loginAsAdmin() {
		
		String username = TestUserRepository.getUserName("bookingAdmin");
		String password = TestUserRepository.getPassword("bookingAdmin");

		authToken = apiClient.getAuthToken(username, password);
	}

	@And("user updates the booking total price to {string}")
	public void updateBookingPrice(String newPrice) {
		booking.setTotalprice(Integer.parseInt(newPrice));
		response = apiClient.updateBooking(bookingId, booking, authToken);
	}

	@Then("the booking should be updated successfully")
	public void verifyUpdationStatus() {
		assertThat(response.getStatusCode()).isEqualTo(200);
	}

	@When("user deletes the booking")
	public void deleteBooking() {
		response = apiClient.deleteBooking(bookingId, authToken);
	}

	@Then("the booking should be deleted successfully")
	public void verifyDeleteStatus() {
		assertThat(response.getStatusCode()).isEqualTo(201);
	}

	@And("the response should be fully valid")
	public void the_response_should_be_fully_valid() {
		ResponseValidator.validateFullResponse(response, "schemas/bookingResponseSchema.json", 3000);
	}

	@And("the response should contain a booking id")
	public void checkBookingId() {
		int bookingId = response.jsonPath().getInt("bookingid");
		assertThat(bookingId).isGreaterThan(0);
		ctx.set("bookingId", bookingId);

	}
}
