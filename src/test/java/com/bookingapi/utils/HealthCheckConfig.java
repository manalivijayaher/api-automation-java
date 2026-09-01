package com.bookingapi.utils;

public class HealthCheckConfig {

	private String name;
	private String baseUrlKey;
	private String healthPath;
	private int expectedStatus;
	private String tag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBaseUrlKey() {
		return baseUrlKey;
	}

	public void setBaseUrlKey(String baseUrlKey) {
		this.baseUrlKey = baseUrlKey;
	}

	public String getHealthPath() {
		return healthPath;
	}

	public void setHealthPath(String healthPath) {
		this.healthPath = healthPath;
	}

	public int getExpectedStatus() {
		return expectedStatus;
	}

	public void setExpectedStatus(int expectedStatus) {
		this.expectedStatus = expectedStatus;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}