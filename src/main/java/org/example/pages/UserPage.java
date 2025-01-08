package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserPage {
    private WebDriver driver;

    public UserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Find all the tabs in the menu
    @FindBy(css = "ul.oxd-main-menu > li")
    private List<WebElement> menuItems;

    // The expected list of tabs (written horizontally in the code)
    public static final List<String> EXPECTED_TABS = List.of("Leave", "Time", "My Info",
            "Performance", "Dashboard", "Directory", "Claim", "Buzz");

    // Method to get the text of all menu tabs
    public List<String> getMenuTabs() {
        return menuItems.stream()
                .map(tab -> tab.getText().trim())
                .toList();
    }

    // Method to check if the actual tabs match the expected tabs
    public boolean validateMenuTabs() {
        List<String> actualTabs = getMenuTabs();
        if (!actualTabs.equals(EXPECTED_TABS)) {
            System.out.println("Expected Tabs: " + EXPECTED_TABS); // Print the expected tabs
            System.out.println("Actual Tabs: " + actualTabs); // Print the actual tabs
            return false;
        }
        return true;
    }
}
