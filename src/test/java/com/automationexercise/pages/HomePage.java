package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By LOGO = By.cssSelector(".logo img");
    private static final By SIGNUP_LOGIN_LINK = By.cssSelector("a[href='/login']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return isDisplayed(LOGO);
    }

    /** Menüden Signup / Login'e tıklar, URL değişene kadar bekler ve LoginPage döndürür. */
    public LoginPage openLoginPage() {
        click(SIGNUP_LOGIN_LINK);
        waits.urlContains("/login");
        return new LoginPage(driver);
    }
}