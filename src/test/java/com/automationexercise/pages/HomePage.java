package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    // Locator'lar sayfa sınıfında tek yerde tutulur; testlerin içinde locator yazılmaz.
    private static final By LOGO = By.cssSelector(".logo img");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return isDisplayed(LOGO);
    }
}
