package org.example.tests;

import org.example.base.BaseDriver;
import org.example.base.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import org.example.pages.LoginPage;
import org.example.pages.AdminPage;
import org.example.pages.UserPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
This test performs multiple validations:
1. Valid login with correct credentials for the Admin user.
2. Adding a new employee with all relevant fields filled.
3. Login validation for the newly created user with their credentials.
4. Permissions check to ensure the user does not have access to Admin sections.

The test is named 'Login Test' because it primarily focuses on login validations for two user types: a regular user and an Admin.
*/

public class LoginTest {
    private LoginPage loginPage;
    private AdminPage adminPage;
    private UserPage userPage;

    private static ExtentTest test;

    @BeforeAll
    public static void setupReport() {
        ExtentReportManager.getExtentReports(); // Initialize Extent Reports for logging
    }

    @BeforeEach
    public void setUp() {
        BaseDriver.setupDriver("chrome"); // Set up Chrome browser
        BaseDriver.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // Open the login page

        loginPage = new LoginPage(BaseDriver.getDriver()); // Initialize LoginPage object
        adminPage = new AdminPage(BaseDriver.getDriver()); // Initialize AdminPage object
        userPage = new UserPage(BaseDriver.getDriver()); // Initialize UserPage object

        test = ExtentReportManager.createTest("Login Test"); // Start logging for this test
    }

    @Test
    public void testLoginOk() {
        test.info("Starting Login Test"); // Log the start of the test

        // Step 1: Login as Admin
        loginPage.setUserame("Admin"); // Enter admin username
        test.info("Entered username: Admin");
        loginPage.setPassword("admin123"); // Enter admin password
        test.info("Entered password: admin123");
        loginPage.clickLoginButton(); // Click the login button
        test.info("Login button pressed");

        boolean isSuccessful = loginPage.isLoginSuccessful(); // Check if login was successful
        assertTrue(isSuccessful, "Login failed!"); // Assert login success
        if (isSuccessful) {
            test.pass("Login successful"); // Log success if login passed
        } else {
            BaseDriver.takeScreenshot("testSuccessfulLogin"); // Take screenshot if login failed
            test.fail("Login failed"); // Log failure if login failed
            return;
        }

        // Step 2: Perform Admin Actions - Create a new user
        test.info("Navigating to PIM section to add a new employee"); // Log navigation to PIM section
        adminPage.navigateToPim();
        adminPage.addEmployee("TestFirstName", "TestLastName", "TestUser", "testpassword123", "testpassword123"); // Add a new employee
        test.pass("New employee created successfully"); // Log success for new employee creation

        // Step 3: Logout as Admin
        test.info("Logging out as Admin"); // Log admin logout
        adminPage.logout();
        test.pass("Logged out as Admin");

        // Step 4: Login as the new user
        test.info("Logging in as the newly created user"); // Log new user login
        loginPage.setUserame("TestUser"); // Enter new user username
        test.info("Entered username: TestUser");
        loginPage.setPassword("testpassword123"); // Enter new user password
        test.info("Entered password: testpassword123");
        loginPage.clickLoginButton(); // Click login button for new user
        test.info("Login button pressed");

        boolean isUserLoginSuccessful = loginPage.isLoginSuccessful(); // Check if new user login was successful
        assertTrue(isUserLoginSuccessful, "Login as new user failed!"); // Assert login success for new user
        if (isUserLoginSuccessful) {
            test.pass("Login as new user successful"); // Log success for new user login
        } else {
            BaseDriver.takeScreenshot("testNewUserLogin"); // Take screenshot if new user login failed
            test.fail("Login as new user failed"); // Log failure for new user login
        }

        // Step 5: Validate user menu tabs
        test.info("Validating user menu tabs"); // Log menu tabs validation

        if (userPage.validateMenuTabs()) {
            test.pass("User menu tabs validated successfully"); // Log success if menu tabs are correct
        } else {
            BaseDriver.takeScreenshot("menuTabsMismatch"); // Take screenshot if menu tabs mismatch
            test.fail("User menu tabs do not match the expected ones! \n" +
                    "Expected: " + String.join(", ", UserPage.EXPECTED_TABS) + "\n" +
                    "Actual: " + String.join(", ", userPage.getMenuTabs())); // Log mismatch details
        }
        assertTrue(userPage.validateMenuTabs(), "User menu tabs do not match the expected ones!"); // Assert menu tabs validation
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
