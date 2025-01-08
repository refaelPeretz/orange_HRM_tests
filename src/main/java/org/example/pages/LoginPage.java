package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    // Constructor to initialize the driver and elements
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize elements with PageFactory
    }

    // Locators
    @FindBy(name = "username")
    private WebElement userNameField; // Field for entering the username

    @FindBy(name = "password")
    private WebElement passwordField; // Field for entering the password

    @FindBy(className = "oxd-button")
    private WebElement loginButton; // Button to perform login

    @FindBy(className = "oxd-userdropdown-tab")
    private WebElement userDropdown; // Dropdown menu for user options

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutOption; // Button to log out

    // Method to enter the username
    public void setUserame(String userame){
        userNameField.sendKeys(userame); // Type the username into the username field
    }

    // Method to enter the password
    public void setPassword(String password){
        passwordField.sendKeys(password); // Type the password into the password field
    }

    // Method to click the login button
    public void clickLoginButton(){
        loginButton.click(); // Click the login button
    }

    // Method to check if login was successful
    public boolean isLoginSuccessful(){
        return driver.getCurrentUrl().contains("/dashboard"); // Check if the current URL contains '/dashboard'
    }

    // Method to check if an error message is displayed
    public boolean isErrorDisplayed() {
        try {
            WebElement errorMessage = driver.findElement(By.xpath("//p[contains(@class, 'oxd-alert-content-text') and text()='Invalid credentials']")); // Locate the error message
            return errorMessage.isDisplayed(); // Return true if the error message is displayed
        } catch (NoSuchElementException e) {
            return false; // Return false if no error message is found
        }
    }

    // Method to log out the user
    public void logout() {
        userDropdown.click(); // Open the user dropdown menu
        logoutOption.click(); // Click the logout option
    }

    // Method to check if the user is at the login page
    public boolean isAtLoginPage() {
        return driver.getCurrentUrl().contains("/auth/login"); // Check if the current URL contains '/auth/login'
    }

}
