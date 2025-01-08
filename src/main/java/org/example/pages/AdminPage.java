package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class AdminPage {

    private WebDriver driver;

    // Constructor to initialize the driver and elements
    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators

    @FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']")
    private WebElement pimTab; // Tab for the PIM module

    @FindBy(xpath = "//a[contains(@class, 'oxd-topbar-body-nav-tab-item') and text()='Add Employee']")
    private WebElement addEmployeeTab; // Tab to add a new employee

    @FindBy(xpath = "//input[@name='firstName' and contains(@class, 'oxd-input')]")
    private WebElement firstNameField; // Field for entering first name

    @FindBy(name = "lastName")
    private WebElement lastNameField; // Field for entering last name

    @FindBy(className = "oxd-switch-input")
    private WebElement createLoginDetailsSwitch; // Switch to enable login details creation

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement usernameField; // Field for entering username

    @FindBy(xpath = "//label[text()='Password']/following::input[1]")
    private WebElement passwordField; // Field for entering password

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[1]")
    private WebElement confirmPasswordField; // Field to confirm the password

    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'oxd-button oxd-button--medium oxd-button--secondary')]")
    private WebElement saveButton; // Button to save employee details

    @FindBy(className = "oxd-userdropdown-tab")
    private WebElement userDropdown; // User dropdown for logout

    @FindBy(xpath = "//a[@href='/web/index.php/auth/logout']")
    private WebElement logoutButton; // Logout button

    @FindBy(css = "ul.oxd-main-menu > li")
    private List<WebElement> menuItems; // List of menu items in the main menu

    // Method to navigate to the PIM module
    public void navigateToPim() {
        pimTab.click();
    }

    // Method to add a new employee
    public void addEmployee(String firstName, String lastName, String username, String password, String confirmPassword) {
        try {
            addEmployeeTab.click();
            Thread.sleep(500); // Wait for the add employee page to load
            firstNameField.sendKeys(firstName); // Enter the first name
            Thread.sleep(500);
            lastNameField.sendKeys(lastName); // Enter the last name
            Thread.sleep(500);
            createLoginDetailsSwitch.click(); // Enable login details creation
            Thread.sleep(500);
            usernameField.sendKeys(username); // Enter the username
            Thread.sleep(500);
            passwordField.sendKeys(password); // Enter the password
            Thread.sleep(500);
            confirmPasswordField.sendKeys(confirmPassword); // Confirm the password
            Thread.sleep(500);
            saveButton.click(); // Save the employee details
            Thread.sleep(6000); // Wait for the action to complete

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to log out the user
    public void logout() {
        userDropdown.click(); // Open the user dropdown
        logoutButton.click(); // Click the logout button
    }

    // Method to get the text of admin tabs in the main menu
    public List<String> getAdminTabs() {
        List<WebElement> tabs = driver.findElements(By.cssSelector(".oxd-main-menu-item-wrapper")); // Find all menu items
        return tabs.stream().map(WebElement::getText).collect(Collectors.toList()); // Return text of each menu item
    }
}
