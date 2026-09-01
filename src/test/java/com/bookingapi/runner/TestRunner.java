package com.bookingapi.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/FeatureFiles",
        glue = "com.bookingapi.stepdefs",
        tags = "not @wip",
        plugin = {"pretty", "summary",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();

	}
}
/*
 * PLUGIN_PROPERTY_NAME, value = "pretty, summary"  controls how results print
 * to console. pretty = readable step-by-step output, summary = a pass/fail
 * count at the end. This class has no methods, no body  its only job is to
 * exist with these annotations so JUnit knows how to launch Cucumber. That
 * surprises people the first time  it's a marker/config class, not logic.
 */
