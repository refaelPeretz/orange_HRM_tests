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
    public static void setupReport(){
        ExtentReportManager.getExtentReports();
    }

    @BeforeEach
    public void setUp(){
        BaseDriver.setupDriver("chrome");
        BaseDriver.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new LoginPage(BaseDriver.getDriver());
        test = ExtentReportManager.createTest("Invalid Login Test");
    }

    @Test
    public void testInvalidLogin(){
        test.info("Starting Invalid Login Test");
        // Entering invalid user credentials
        loginPage.setUserame("Kobiyonasi");
        test.info("Entered username: Kobiyonasi");
        loginPage.setPassword("1245");
        test.info("Entered password: 1245");
        loginPage.clickLoginButton();
        test.info("Login button pressed");

        boolean isErrorDisplayed = loginPage.isErrorDisplayed();
        if (isErrorDisplayed){
            test.pass("Error message displayed as expected");
        }
        else {
            BaseDriver.takeScreenshot("testInvalidLogin");
            test.fail("Error message not displayed");
        }
        assertTrue(isErrorDisplayed, "Error message not displayed when login failed!");
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
