package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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


    @FindBy(xpath = "//a[contains(text(), 'Employee List')]")
    private WebElement employeeListTab;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[contains(@class, 'oxd-button--secondary') and text()=' Search ']")
    private WebElement searchButton;

    @FindBy(xpath = "//i[contains(@class, 'bi-pencil-fill')]")
    private WebElement editButton;

    @FindBy(css = "input.oxd-input.oxd-input--active.orangehrm-lastname")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[contains(@class, 'oxd-button--secondary') and text()=' Save ']")
    private WebElement saveButton;

//    @FindBy(name = "lastName")
//    private WebElement lastNameFieldSecondTime;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameFieldSecondTime;

    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'oxd-button--secondary')]")
    private WebElement submitButton;




    // Navigate to Employee List
    public void navigateToEmployeeList() {
        employeeListTab.click();
    }

    // Search for an employee
    public void searchEmployee(String fullName) {
        searchBox.clear();
        searchBox.sendKeys(fullName);
        searchButton.click();
        try {
            Thread.sleep(2000); // המתנה לטעינת התוצאות
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Click Edit button
    public void clickEditButton() {
        try {
            Thread.sleep(1000); // המתנה לפני לחיצה על כפתור העריכה
            editButton.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String updateLastNameField(String newLastName) {
        lastNameFieldSecondTime.click();
        lastNameFieldSecondTime.sendKeys(Keys.CONTROL + "a");
        lastNameFieldSecondTime.sendKeys(Keys.DELETE);
        lastNameFieldSecondTime.clear();
        lastNameFieldSecondTime.sendKeys(newLastName);
        return newLastName;
    }

    // Update last name
    public void updateLastName(String newLastName) {
        lastNameFieldSecondTime.click();
        lastNameFieldSecondTime.sendKeys(Keys.CONTROL + "a");
        lastNameFieldSecondTime.sendKeys(Keys.DELETE);
        lastNameFieldSecondTime.clear();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
        wait.until(ExpectedConditions.elementToBeClickable(lastNameField));

        lastNameField.sendKeys(newLastName);
    }
    // Save changes
    public void clickSaveButton() {
        saveButton.click();
    }
    public boolean isEmployeeInList(String fullName) {
        try {
            WebElement employeeRow = driver.findElement(By.xpath("//div[text()='" + fullName + "']"));
            return employeeRow.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public String getLastName() {
        return lastNameFieldSecondTime.getAttribute("value");
    }
    public String getLastNameAgain() {
        return lastNameFieldSecondTime.getAttribute("value");
    }
    public void clickSubmitButton() {
        submitButton.click();
    }



}










//package org.example.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class PIMPage {
//
//    private WebDriver driver;
//    private AdminPage adminPage;
//
//    public PIMPage(WebDriver driver) {
//        this.driver = driver;
//        this.adminPage = new AdminPage(driver); // אתחול AdminPage
//
//        PageFactory.initElements(driver, this);
//    }
//
//    public void navigateToPim() {
//        adminPage.navigateToPim();
//    }
//
//    @FindBy(xpath = "//span[contains(@class, 'oxd-input-field-error-message') and text()='Required']")
//    private WebElement errorMessage;
//
//
//
//
//    public boolean isPasswordErrorDisplayed() {
//        try {
//            return errorMessage.isDisplayed();
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//}