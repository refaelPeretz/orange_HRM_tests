package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {

    private WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']")
    private WebElement pimTab;

    @FindBy(xpath = "//a[contains(@class, 'oxd-topbar-body-nav-tab-item') and text()='Add Employee']")
    private WebElement addEmployeeTab;

    @FindBy(xpath = "//input[@name='firstName' and contains(@class, 'oxd-input')]")
    private WebElement firstNameField;


    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(className = "oxd-switch-input")
    private WebElement createLoginDetailsSwitch;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement usernameField;

    @FindBy(xpath = "//label[text()='Password']/following::input[1]")
    private WebElement passwordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[1]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'oxd-button oxd-button--medium oxd-button--secondary')]")
    private WebElement saveButton;

    @FindBy(className = "oxd-userdropdown-tab")
    private WebElement userDropdown;

    @FindBy(xpath = "//a[@href='/web/index.php/auth/logout']")
    private WebElement logoutButton;

    public void navigateToPim() {
        pimTab.click();
    }

    public void addEmployee(String firstName, String lastName, String username, String password, String confirmPassword) {
        try {
        addEmployeeTab.click();
            Thread.sleep(500);
            firstNameField.sendKeys(firstName);
            Thread.sleep(500);
            lastNameField.sendKeys(lastName);
            Thread.sleep(500);
            createLoginDetailsSwitch.click();
            Thread.sleep(500);
            usernameField.sendKeys(username);
            Thread.sleep(500);
            passwordField.sendKeys(password);
            Thread.sleep(500);
            confirmPasswordField.sendKeys(confirmPassword);
            Thread.sleep(500);
            saveButton.click();
            Thread.sleep(6000);

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        userDropdown.click();
        logoutButton.click();
    }
}
