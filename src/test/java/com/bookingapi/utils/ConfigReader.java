package com.bookingapi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//the class that reads config.properties

public class ConfigReader {
	private static final Properties properties = new Properties();
	static {
		String env = System.getProperty("env", "dev");
		String fileName = "config/config-" + env + ".properties";

		try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
			if (input == null) {
				throw new RuntimeException("Config file not found " + fileName);
			}
			properties.load(input);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load the file" + fileName, e);
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static String getEnv() {
		return System.getProperty("env", "dev");
	}
}
/*
 * getResourceAsStream("config.properties") -- Java's way of opening a file that
 * lives in resources (this only works because it's on the classpath, which is
 * another reason it must live there, not in java).
 * 
 * 
 * properties.load(input)  reads the file and turns base.url=... into a
 * key-value pair internally.
 * 
 * 
 * get(String key)  anyone in your project calls ConfigReader.get("base.url")
 * and gets the URL back, without needing to know or care how the file was read.
 */
