package com.bookingapi.examples;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bookingapi.client.BookingApiClient;
import com.bookingapi.pojo.Booking;
import com.bookingapi.utils.JsonDataReader;
import static org.assertj.core.api.Assertions.assertThat;


import io.restassured.response.Response;

public class DataProviderTest {

	private final BookingApiClient apiClient = new BookingApiClient();

	@DataProvider(name = "bookingData")
	public Object[][] bookingData() throws Exception {
		List<Booking> bookings = JsonDataReader.readList("testdata/bookingTestData.json", Booking.class);

		Object[][] data = new Object[bookings.size()][1];

		for (int i = 0; i < bookings.size(); i++) {
			data[i][0] = bookings.get(i);

		}
		return data;

	}
	
	@Test(dataProvider = "bookingData")
	public void createBookingWithJson(Booking booking) {
		Response response = apiClient.createBooking(booking);
		System.out.println("Response body for " + booking.getFirstname() + ": " + response.asString());
		
		assertThat(response.getStatusCode()).isEqualTo(200);
		assertThat(response.jsonPath().getString("booking.firstname")).isEqualTo(booking.getFirstname());
	}

}
