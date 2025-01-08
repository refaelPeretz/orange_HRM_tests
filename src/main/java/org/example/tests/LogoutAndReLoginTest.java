package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.base.BaseDriver;
import org.example.base.ExtentReportManager;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutAndReLoginTest {
    private LoginPage loginPage;
    private static ExtentTest test;

    @BeforeAll
    public static void setupReport() {
        ExtentReportManager.getExtentReports(); // Initialize Extent Reports
    }

    @BeforeEach
    public void setUp() {
        BaseDriver.setupDriver("chrome"); // Set up Chrome browser
        BaseDriver.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // Open the login page

        loginPage = new LoginPage(BaseDriver.getDriver()); // Initialize LoginPage object

        test = ExtentReportManager.createTest("TC015 - Logout functionality and re-login requirement test P"); // Start logging for this test
    }

    @Test
    public void testLogoutAndReLoginRequirement() {
        test.info("Starting TC015 - Logout functionality and re-login requirement test P."); // Log test start

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
            BaseDriver.takeScreenshot("testLogoutAndReLoginLoginFail"); // Take a screenshot if login failed
            test.fail("Login failed"); // Log failure if login failed
            return;
        }

        // Step 2: Perform Logout
        loginPage.logout(); // Log out of the system
        test.info("Logged out successfully");

        // Step 3: Verify redirected to login page
        boolean isAtLoginPage = loginPage.isAtLoginPage(); // Check if redirected to login page
        if (isAtLoginPage) {
            test.pass("Logout successful, redirected to login page"); // Log success if redirected correctly
        } else {
            BaseDriver.takeScreenshot("testLogoutNotRedirectedToLogin"); // Take a screenshot if not redirected
            test.fail("Logout failed, not redirected to login page"); // Log failure if not redirected
        }
        assertTrue(isAtLoginPage, "Logout failed, not redirected to login page"); // Assert redirection

        // Step 4: Attempt re-login with valid credentials
        loginPage.setUserame("Admin"); // Re-enter admin username
        test.info("Re-entered username: Admin");
        loginPage.setPassword("admin123"); // Re-enter admin password
        test.info("Re-entered password: admin123");
        loginPage.clickLoginButton(); // Click the login button again
        test.info("Re-login button pressed");

        boolean isReLoginSuccessful = loginPage.isLoginSuccessful(); // Check if re-login was successful
        if (isReLoginSuccessful) {
            test.pass("Re-login successful"); // Log success if re-login passed
        } else {
            BaseDriver.takeScreenshot("testReLoginFail"); // Take a screenshot if re-login failed
            test.fail("Re-login failed"); // Log failure if re-login failed
        }
        assertTrue(isReLoginSuccessful, "Re-login failed!"); // Assert re-login success
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
