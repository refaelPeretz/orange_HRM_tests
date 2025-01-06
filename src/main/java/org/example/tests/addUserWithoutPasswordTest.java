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
        ExtentReportManager.getExtentReports();
    }
    @BeforeEach
    public void setUp() {
        BaseDriver.setupDriver("chrome");
        BaseDriver.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new LoginPage(BaseDriver.getDriver());
        adminPage = new AdminPage(BaseDriver.getDriver());
        pimPage = new PIMPage(BaseDriver.getDriver());


        test = ExtentReportManager.createTest("Add User Without Password Test");

    }
    @Test
    public void testAddUserNoPasswordError() {
        test.info("Starting Add User Without Password Test");

        // Step 1: Login as Admin
        loginPage.setUserame("Admin");
        test.info("Entered username: Admin");
        loginPage.setPassword("admin123");
        test.info("Entered password: admin123");
        loginPage.clickLoginButton();
        test.info("Login button pressed");

        test.info("Navigating to PIM section to add a new employee");
        adminPage.navigateToPim();
        adminPage.addEmployee("TestFirstName", "TestLastName", "TestUser", "", "testpassword123");

        boolean isErrorDisplayed = pimPage.isPasswordErrorDisplayed();
        test.info("Validating error message for missing password");
        assertTrue(isErrorDisplayed, "Error message for missing password was not displayed!");

        if (isErrorDisplayed) {
            test.pass("Error message for missing password displayed successfully");
        } else {
            test.fail("Error message for missing password not displayed");
        }
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
