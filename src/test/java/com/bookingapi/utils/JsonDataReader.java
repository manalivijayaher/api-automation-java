package com.bookingapi.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.bookingapi.pojo.Booking;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonDataReader {

	public static final ObjectMapper mapper = new ObjectMapper();

	public static <T> List<T> readList(String resourcePath, Class<T> targetClass) throws Exception {

		try {

			InputStream input = JsonDataReader.class.getClassLoader().getResourceAsStream(resourcePath);

			CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, targetClass);

			return mapper.readValue(input, listType);

		} catch (Exception e) {
			throw new Exception("Failed to read the test data JSON: " + resourcePath, e);
		}

	}
}
/*
 * <T> makes this method generic  meaning
 * "works with any class, decided by whoever calls it." Class<T> targetClass is
 * how you tell it which class to build (Booking.class, Room.class, anything).
 * Jackson's readValue(input, listType) does the entire
 * "read JSON, match field names to your POJO's fields, build objects" job
 * automatically  this is actually exactly what Jackson already does behind the
 * scenes every time RestAssured serializes/deserializes for you; we're just
 * using that same built-in capability directly
 */
