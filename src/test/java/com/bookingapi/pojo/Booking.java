package com.bookingapi.pojo;

//the POJO (Plain Old Java Object)
public class Booking {

	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private BookingDates bookingdates;
	private String additionalneeds;

	// getters and setters for each field

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public BookingDates getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}
}

/*
 * A POJO is just a Java class shaped exactly like that JSON, so instead of
 * building JSON strings by hand (error-prone, ugly), you build a normal Java
 * object and let Jackson turn it into JSON for you.
 * 
 * 
 * Expected JSON - { "firstname": "Jane", "lastname": "Doe", "totalprice": 150,
 * "depositpaid": true, "bookingdates": { "checkin": "2024-01-01", "checkout":
 * "2024-01-05" }, "additionalneeds": "Breakfast" }
 * 
 * firstname, not firstName: Jackson matches JSON keys to Java field names
 * automatically. If they don't match, you either get null values or have to add
 * extra annotations to map them manually  matching exactly is the simplest
 * path.
 */
