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


public class LoginTest {
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
        test = ExtentReportManager.createTest("Login Test");
    }



    @Test
    public  void testLoginOk(){
        test.info("Starting Login Test");
        //Navigating the site and entering user information
        loginPage.setUserame("Admin");
        test.info("Entered username: Admin");
        loginPage.setPassword("admin123");
        test.info("Entered password: Admin123");
        loginPage.clickLoginButton();
        test.info("Login button pressed");

        boolean isSuccessful = loginPage.isLoginSuccessful();
        if (isSuccessful){
            test.pass("Login successful");
        }
        else {
            BaseDriver.takeScreenshot("testSuccessfulLogin");
        }
        assertTrue(isSuccessful, "Login failed!");

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
