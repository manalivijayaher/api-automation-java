package com.bookingapi.utils;

import com.bookingapi.pojo.Booking;
import com.bookingapi.pojo.BookingDates;

public class BookingBuilder {

	public static Booking buildBooking(String firstname, String lastname, int totalprice, String checkin,
			String checkout ,String additionalneeds, boolean depositpaid) {
		BookingDates dates = new BookingDates();
		dates.setCheckin(checkin);
		dates.setCheckout(checkout);

		Booking booking = new Booking();
		booking.setFirstname(firstname);
		booking.setLastname(lastname);
		booking.setTotalprice(totalprice);
		booking.setDepositpaid(depositpaid);
		booking.setBookingdates(dates);
		booking.setAdditionalneeds(additionalneeds);

		return booking;

	}
}
