package com.bookingapi.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;

public class TestUserRepository {

	private static final JsonNode users;

	static {
		try {
			String env = ConfigReader.getEnv();
			String fileName = "config/users-" + env + ".json";
			InputStream input = TestUserRepository.class.getClassLoader().getResourceAsStream(fileName);

			users = new ObjectMapper().readTree(input);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load the file ", e);
		}
	}

	public static String getUserName(String userKey) {
		return users.get(userKey).get("username").asText();
	}

	public static String getPassword(String userKey) {
		return users.get(userKey).get("password").asText();
	}

}
