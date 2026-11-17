package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-qa='account-deleted']");

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return getText(TITLE);
    }
}