package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.base.BaseDriver;
import org.example.base.ExtentReportManager;
import org.example.pages.AdminPage;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminDashboardFieldValidation {
    private LoginPage loginPage;
    private AdminPage adminPage;
    private static ExtentTest test;

    @BeforeAll
    public static void setupReport() {
        ExtentReportManager.getExtentReports(); // Initialize Extent Reports
    }

    @BeforeEach
    public void setUp() {
        BaseDriver.setupDriver("chrome"); // Set up Chrome browser
        BaseDriver.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // Open the login page

        loginPage = new LoginPage(BaseDriver.getDriver()); // Initialize the LoginPage object
        adminPage = new AdminPage(BaseDriver.getDriver()); // Initialize the AdminPage object

        test = ExtentReportManager.getExtentReports().createTest("Admin Dashboard Fields Display Test"); // Start logging for the test
    }

    @Test
    public void testLoginOk() {
        test.info("Starting Admin Dashboard Fields Display Test"); // Log test start

        // Step 1: Login as Admin
        loginPage.setUserame("Admin"); // Enter the username
        test.info("Entered username: Admin");
        loginPage.setPassword("admin123"); // Enter the password
        test.info("Entered password: admin123");
        loginPage.clickLoginButton(); // Click the login button
        test.info("Login button pressed");

        // Validate login success
        boolean isSuccessful = loginPage.isLoginSuccessful(); // Check if login was successful
        assertTrue(isSuccessful, "Login failed!"); // Assert login success
        if (isSuccessful) {
            test.pass("Login successful"); // Log success if login passed
        } else {
            BaseDriver.takeScreenshot("testSuccessfulLogin"); // Take a screenshot if login failed
            test.fail("Login failed");
            return;
        }

        // Step 2: Validate Admin Dashboard Tabs
        test.info("Validating Admin Dashboard Tabs"); // Log tab validation step

        List<String> expectedTabs = Arrays.asList("Admin", "PIM", "Leave", "Time", "Recruitment", "My Info", "Performance", "Dashboard", "Directory", "Maintenance", "Claim", "Buzz"); // Expected tabs
        List<String> actualTabs = adminPage.getAdminTabs(); // Get actual tabs from the AdminPage

        // Compare actual tabs with expected tabs
        if (actualTabs.equals(expectedTabs)) {
            test.pass("Admin Dashboard Tabs match the expected list"); // Log success if tabs match
        } else {
            BaseDriver.takeScreenshot("adminDashboardTabsMismatch"); // Take a screenshot if tabs don't match
            test.fail("Admin Dashboard Tabs do not match! \n" +
                    "Expected: " + String.join(", ", expectedTabs) + "\n" +
                    "Actual: " + String.join(", ", actualTabs)); // Log mismatch details
        }

        assertTrue(actualTabs.equals(expectedTabs), "Admin Dashboard Tabs do not match the expected ones!"); // Assert tabs match
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
