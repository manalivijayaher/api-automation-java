package com.bookingapi.pojo;

public class BookingDates {

	private String checkin;
	private String checkout;

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
}
/*
 * Why a separate BookingDates class instead of cramming everything into
 * Booking: 
 * 
 * the JSON itself is nested (bookingdates is an object inside the
 * booking object), 
 * so your Java structure mirrors it. 
 * 
 * This one-to-one mapping
 * is why ask "how do you handle nested JSON" "I created a matching nested POJO."
 */
