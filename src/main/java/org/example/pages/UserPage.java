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

    // מציאת כל הלשוניות בתוך התפריט
    @FindBy(css = "ul.oxd-main-menu > li")
    private List<WebElement> menuItems;

    // הרשימה הצפויה של הלשוניות (נכתבת אופקית בקוד)
    public static final List<String> EXPECTED_TABS = List.of("Leave", "Time", "My Info",
            "Performance", "Dashboard", "Directory", "Claim", "Buzz");

    /**
     * מחזירה רשימה של שמות הלשוניות כפי שהן מופיעות בתפריט בפועל.
     */
    public List<String> getMenuTabs() {
        return menuItems.stream()
                .map(tab -> tab.getText().trim())
                .toList();
    }

    /**
     * בודקת האם רשימת הלשוניות בפועל תואמת את הרשימה הצפויה.
     */
    public boolean validateMenuTabs() {
        List<String> actualTabs = getMenuTabs();
        if (!actualTabs.equals(EXPECTED_TABS)) {
            System.out.println("Expected Tabs: " + EXPECTED_TABS);
            System.out.println("Actual Tabs: " + actualTabs);
            return false;
        }
        return true;
    }
}
