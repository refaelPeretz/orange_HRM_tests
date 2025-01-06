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

public class LoginTest {
    private LoginPage loginPage;
    private AdminPage adminPage;
    private UserPage userPage;

    private static ExtentTest test;

    @BeforeAll
    public static void setupReport() {
        ExtentReportManager.getExtentReports();
    }

    @BeforeEach
    public void setUp() {
        BaseDriver.setupDriver("chrome");
        BaseDriver.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new LoginPage(BaseDriver.getDriver());
        adminPage = new AdminPage(BaseDriver.getDriver());
        userPage = new UserPage(BaseDriver.getDriver());

        test = ExtentReportManager.createTest("Login Test");
    }

    @Test
    public void testLoginOk() {
        test.info("Starting Login Test");

        // Step 1: Login as Admin
        loginPage.setUserame("Admin");
        test.info("Entered username: Admin");
        loginPage.setPassword("admin123");
        test.info("Entered password: admin123");
        loginPage.clickLoginButton();
        test.info("Login button pressed");

        boolean isSuccessful = loginPage.isLoginSuccessful();
        assertTrue(isSuccessful, "Login failed!");
        if (isSuccessful) {
            test.pass("Login successful");
        } else {
            BaseDriver.takeScreenshot("testSuccessfulLogin");
            test.fail("Login failed");
            return;
        }

        // Step 2: Perform Admin Actions - Create a new user
        test.info("Navigating to PIM section to add a new employee");
        adminPage.navigateToPim();
        adminPage.addEmployee("TestFirstName", "TestLastName", "TestUser", "testpassword123", "testpassword123");
        test.pass("New employee created successfully");

        // Step 3: Logout as Admin
        test.info("Logging out as Admin");
        adminPage.logout();
        test.pass("Logged out as Admin");

        // Step 4: Login as the new user
        test.info("Logging in as the newly created user");
        loginPage.setUserame("TestUser");
        test.info("Entered username: TestUser");
        loginPage.setPassword("testpassword123");
        test.info("Entered password: testpassword123");
        loginPage.clickLoginButton();
        test.info("Login button pressed");

        boolean isUserLoginSuccessful = loginPage.isLoginSuccessful();
        assertTrue(isUserLoginSuccessful, "Login as new user failed!");
        if (isUserLoginSuccessful) {
            test.pass("Login as new user successful");
        } else {
            BaseDriver.takeScreenshot("testNewUserLogin");
            test.fail("Login as new user failed");
        }

        test.info("Validating user menu tabs");

        if (userPage.validateMenuTabs()) {
            test.pass("User menu tabs validated successfully");
        } else {
            BaseDriver.takeScreenshot("menuTabsMismatch");
            test.fail("User menu tabs do not match the expected ones! \n" +
                    "Expected: " + String.join(", ", UserPage.EXPECTED_TABS) + "\n" +
                    "Actual: " + String.join(", ", userPage.getMenuTabs()));
        }
        assertTrue(userPage.validateMenuTabs(), "User menu tabs do not match the expected ones!");


    }

    @AfterEach
    public void closeBrowser() {
        BaseDriver.closeDriver();
        test.info("Browser closed");
    }

    @AfterAll
    public static void closeReport() {
        ExtentReportManager.flushReports();
    }
}
