package org.example.tests;

import org.example.base.BaseDriver;
import org.example.base.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidLoginTest {
    private LoginPage loginPage;
    private static ExtentTest test;

    @BeforeAll
    public static void setupReport() {
        ExtentReportManager.getExtentReports(); // Initialize Extent Reports for logging
    }

    @BeforeEach
    public void setUp() {
        BaseDriver.setupDriver("chrome"); // Set up Chrome browser
        BaseDriver.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // Open the login page

        loginPage = new LoginPage(BaseDriver.getDriver()); // Initialize the LoginPage object
        test = ExtentReportManager.createTest("Invalid Login Test"); // Start logging for the test
    }

    @Test
    public void testInvalidLogin() {
        test.info("Starting Invalid Login Test"); // Log test start

        // Entering invalid user credentials
        loginPage.setUserame("Kobiyonasi"); // Enter invalid username
        test.info("Entered username: Kobiyonasi");
        loginPage.setPassword("1245"); // Enter invalid password
        test.info("Entered password: 1245");
        loginPage.clickLoginButton(); // Click the login button
        test.info("Login button pressed");

        // Validate error message
        boolean isErrorDisplayed = loginPage.isErrorDisplayed(); // Check if error message is displayed
        if (isErrorDisplayed) {
            test.pass("Error message displayed as expected"); // Log success if error message is visible
        } else {
            BaseDriver.takeScreenshot("testInvalidLogin"); // Take a screenshot if error message is not visible
            test.fail("Error message not displayed"); // Log failure if error message is not visible
        }

        assertTrue(isErrorDisplayed, "Error message not displayed when login failed!"); // Assert that the error message is displayed
    }

    @AfterEach
    public void closeBrowser() {
        BaseDriver.closeDriver(); // Close the browser
        test.info("Browser closed");
    }

    @AfterAll
    public static void closeReport() {
        ExtentReportManager.flushReports(); // Finalize Extent Reports
    }
}
