package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;


    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    private WebElement userNameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy( className= "oxd-button")
    private WebElement loginButton;

    @FindBy(className = "oxd-userdropdown-tab") // menu
    private WebElement userDropdown;

    @FindBy(xpath = "//a[text()='Logout']") //  Logout button
    private WebElement logoutOption;

    public void setUserame(String userame){
        userNameField.sendKeys(userame);
    }

    public void setPassword(String password){
        passwordField.sendKeys(password);
    }
    public void clickLoginButton(){
        loginButton.click();
    }
    public boolean isLoginSuccessful(){
        return driver.getCurrentUrl().contains("/dashboard");
    }
    public boolean isErrorDisplayed() {
        try {
            WebElement errorMessage = driver.findElement(By.xpath("//p[contains(@class, 'oxd-alert-content-text') and text()='Invalid credentials']"));
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public void logout() {
        userDropdown.click();
        logoutOption.click();
    }


}
