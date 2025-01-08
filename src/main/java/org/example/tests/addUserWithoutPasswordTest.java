package org.example.tests;

import org.example.base.BaseDriver;
import org.example.base.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import org.example.pages.LoginPage;
import org.example.pages.AdminPage;
import org.example.pages.PIMPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class addUserWithoutPasswordTest {

    private LoginPage loginPage;
    private AdminPage adminPage;
    private PIMPage pimPage;

    private static ExtentTest test;

    @BeforeAll
    public static void setupReport() {
        ExtentReportManager.getExtentReports(); // Initialize Extent Reports for logging
    }

    @BeforeEach
    public void setUp() {
        BaseDriver.setupDriver("chrome"); // Set up the Chrome browser driver
        BaseDriver.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // Open the login page

        loginPage = new LoginPage(BaseDriver.getDriver()); // Create a LoginPage object
        adminPage = new AdminPage(BaseDriver.getDriver()); // Create an AdminPage object
        pimPage = new PIMPage(BaseDriver.getDriver()); // Create a PIMPage object

        test = ExtentReportManager.createTest("Add User Without Password Test"); // Start logging for this test
    }

    @Test
    public void testAddUserNoPasswordError() {
        test.info("Starting Add User Without Password Test"); // Log test start

        // Step 1: Login as Admin
        loginPage.setUserame("Admin"); // Enter username
        test.info("Entered username: Admin");
        loginPage.setPassword("admin123"); // Enter password
        test.info("Entered password: admin123");
        loginPage.clickLoginButton(); // Press the login button
        test.info("Login button pressed");

        // Step 2: Navigate to the PIM section
        test.info("Navigating to PIM section to add a new employee");
        adminPage.navigateToPim();
        adminPage.addEmployee("TestFirstName", "TestLastName", "TestUser", "", "testpassword123"); // Add a new employee without a password

        // Step 3: Validate error message
        boolean isErrorDisplayed = pimPage.isPasswordErrorDisplayed(); // Check if the error message is visible
        test.info("Validating error message for missing password");
        assertTrue(isErrorDisplayed, "Error message for missing password was not displayed!"); // Assert that the error is displayed

        if (isErrorDisplayed) {
            test.pass("Error message for missing password displayed successfully"); // Log success if error message is visible
        } else {
            test.fail("Error message for missing password not displayed"); // Log failure if error message is not visible
        }
    }

    @AfterEach
    public void closeBrowser() {
        BaseDriver.closeDriver(); // Close the browser
        test.info("Browser closed");
    }

    @AfterAll
    public static void closeReport() {
        ExtentReportManager.flushReports(); // Finalize and save Extent Reports
    }
}
