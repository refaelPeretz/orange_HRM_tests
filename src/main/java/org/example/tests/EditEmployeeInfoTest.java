package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.base.BaseDriver;
import org.example.base.ExtentReportManager;
import org.example.pages.AdminPage;
import org.example.pages.LoginPage;
import org.example.pages.PIMPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditEmployeeInfoTest {
    private LoginPage loginPage;
    private AdminPage adminPage;
    private PIMPage pimPage;
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
        pimPage = new PIMPage(BaseDriver.getDriver()); // Initialize the PIMPage object

        test = ExtentReportManager.createTest("Update Employee Test"); // Start logging for this test
    }

    @Test
    public void testUpdateEmployee() {
        test.info("Starting Update Employee Test"); // Log the start of the test

        // Step 1: Login as Admin
        loginPage.setUserame("Admin"); // Enter the username
        test.info("Entered username: Admin");
        loginPage.setPassword("admin123"); // Enter the password
        test.info("Entered password: admin123");
        loginPage.clickLoginButton(); // Click the login button
        test.info("Login button pressed");

        assertTrue(loginPage.isLoginSuccessful(), "Admin login failed!"); // Validate login success
        test.pass("Admin login successful");

        // Add a new employee
        String firstName = "Dano";
        String lastName = "Dinua";
        adminPage.navigateToPim(); // Navigate to the PIM section
        adminPage.addEmployee(firstName, lastName, "danidin5", "Password123", "Password123"); // Add a new employee
        test.pass("New employee created successfully");

        // Update the last name field
        String newLastName = pimPage.updateLastNameField("smith");
        test.pass("Updated the last name field");

        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
            test.warning("Interrupted during wait");
        }

        pimPage.clickSubmitButton(); // Submit the update
        test.pass("Submitted the update");

        try {
            Thread.sleep(6000); // Wait for 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
            test.warning("Interrupted during wait");
        }

        // Validate the last name update
        String result = pimPage.getLastName(); // Get the updated last name
        System.out.println(result);
        assertTrue(result.equals(newLastName), "Last name was not updated correctly!"); // Assert the last name was updated
        test.pass("Last name updated successfully: " + newLastName);
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
