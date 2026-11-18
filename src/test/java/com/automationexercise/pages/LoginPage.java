package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

public class LoginPage extends BasePage {

    private static final By LOGIN_HEADING = By.cssSelector(".login-form h2");
    private static final By LOGIN_EMAIL = By.cssSelector("[data-qa='login-email']");
    private static final By LOGIN_PASSWORD = By.cssSelector("[data-qa='login-password']");
    private static final By LOGIN_BUTTON = By.cssSelector("[data-qa='login-button']");
    private static final By LOGIN_ERROR = By.cssSelector(".login-form form p");

    private static final By SIGNUP_NAME = By.cssSelector("[data-qa='signup-name']");
    private static final By SIGNUP_EMAIL = By.cssSelector("[data-qa='signup-email']");
    private static final By SIGNUP_BUTTON = By.cssSelector("[data-qa='signup-button']");
    private static final By SIGNUP_ERROR = By.cssSelector(".signup-form form p");

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

    /** Sag formu doldurup Signup'a basar ve hesap bilgileri sayfasini dondurur. */
    public SignupPage startSignup(String name, String email) {
        submitSignup(name, email);
        SignupPage signupPage = new SignupPage(driver);
        signupPage.waitUntilLoaded();
        return signupPage;
    }

    /** Sag formu doldurup Signup'a basar, sonraki sayfayi beklemez (hata senaryolari icin). */
    public void submitSignup(String name, String email) {
        type(SIGNUP_NAME, name);
        type(SIGNUP_EMAIL, email);
        click(SIGNUP_BUTTON);
    }

    public String getSignupErrorMessage() {
        return getText(SIGNUP_ERROR);
    }

    public String getErrorMessage() {
        return getText(LOGIN_ERROR);
    }

        /** Bos alanlari bos birakarak formu gondermeyi dener (HTML5 zorunlu alan kontrolu icin). */
    public void submitLoginForm(String email, String password) {
        if (!email.isEmpty()) {
            type(LOGIN_EMAIL, email);
        }
        if (!password.isEmpty()) {
            type(LOGIN_PASSWORD, password);
        }
        click(LOGIN_BUTTON);
    }

    public boolean isEmailFieldMissing() {
        return isValueMissing(LOGIN_EMAIL);
    }

    public boolean isPasswordFieldMissing() {
        return isValueMissing(LOGIN_PASSWORD);
    }

    /** Tarayicinin zorunlu alan kontrolu: alan bos ve required ise true doner. */
    private boolean isValueMissing(By locator) {
        WebElement element = waits.visible(locator);
        Object result = ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validity.valueMissing;", element);
        return Boolean.TRUE.equals(result);
    }

    public boolean isErrorMessageHidden() {
        return isNotDisplayed(LOGIN_ERROR);
    }

    /** Yalnizca bekleme karsilastirmasi icin: beklemeden anlik okur, element yoksa bos metin doner. */
    public String getErrorMessageNow() {
        List<WebElement> found = driver.findElements(LOGIN_ERROR);
        return found.isEmpty() ? "" : found.get(0).getText();
    }
}