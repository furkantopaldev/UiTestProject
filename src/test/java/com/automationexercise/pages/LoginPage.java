package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By LOGIN_HEADING = By.cssSelector(".login-form h2");
    private static final By LOGIN_EMAIL = By.cssSelector("[data-qa='login-email']");
    private static final By LOGIN_PASSWORD = By.cssSelector("[data-qa='login-password']");
    private static final By LOGIN_BUTTON = By.cssSelector("[data-qa='login-button']");
    private static final By LOGIN_ERROR = By.cssSelector(".login-form form p");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginFormDisplayed() {
        return isDisplayed(LOGIN_HEADING);
    }

    public void login(String email, String password) {
        type(LOGIN_EMAIL, email);
        type(LOGIN_PASSWORD, password);
        click(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return getText(LOGIN_ERROR);
    }

    public boolean isErrorMessageHidden() {
        return isNotDisplayed(LOGIN_ERROR);
    }
}