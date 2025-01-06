package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMPage {

    private WebDriver driver;
    private AdminPage adminPage;

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.adminPage = new AdminPage(driver); // אתחול AdminPage

        PageFactory.initElements(driver, this);
    }

    public void navigateToPim() {
        adminPage.navigateToPim();
    }

    @FindBy(xpath = "//span[contains(@class, 'oxd-input-field-error-message') and text()='Required']")
    private WebElement errorMessage;




    public boolean isPasswordErrorDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}