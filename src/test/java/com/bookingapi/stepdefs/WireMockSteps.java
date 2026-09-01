package com.bookingapi.stepdefs;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class WireMockSteps {

    private WireMockServer wireMockServer;
    private Response response;

    @Given("the booking service is down")
    public void the_booking_service_is_down() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        configureFor("localhost", 8089);

        stubFor(post(urlEqualTo("/booking"))
                .willReturn(aResponse().withStatus(500)));
    }

    @Given("the booking service is slow to respond")
    public void the_booking_service_is_slow() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        configureFor("localhost", 8089);

        stubFor(post(urlEqualTo("/booking"))
                .willReturn(aResponse().withFixedDelay(5000).withStatus(200)));
    }

    @When("user tries to create a booking")
    public void user_tries_to_create_a_booking() {
        response = given()
                .baseUri("http://localhost:8089")
                .contentType("application/json")
                .body("{}")
                .when()
                .post("/booking");
    }

    @Then("the system should handle the error gracefully")
    public void the_system_should_handle_the_error() {
        assertThat(response.getStatusCode()).isEqualTo(500);
        wireMockServer.stop();
    }

    @Then("the request should fail with a timeout")
    public void the_request_should_fail_with_timeout() {
        assertThat(response.getTime()).isGreaterThan(4000);
        wireMockServer.stop();
    }
}