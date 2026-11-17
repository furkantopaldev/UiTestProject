package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-qa='account-created']");
    private static final By CONTINUE_BUTTON = By.cssSelector("[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return getText(TITLE);
    }

    public HomePage continueToHome() {
        click(CONTINUE_BUTTON);
        return new HomePage(driver);
    }
}