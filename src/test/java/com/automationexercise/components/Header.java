package com.automationexercise.components;

import io.qameta.allure.Step;
import com.automationexercise.pages.AccountDeletedPage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;


/** Ust menu. Tum sayfalarda ortaktir. */
public class Header {

    private static final By LOGGED_IN_USERNAME = By.xpath("//a[contains(., 'Logged in as')]/b");
    private static final By DELETE_ACCOUNT_LINK = By.cssSelector("a[href='/delete_account']");
    private static final By LOGOUT_LINK = By.cssSelector("a[href='/logout']");
    private static final By SIGNUP_LOGIN_LINK = By.cssSelector("a[href='/login']");

    private final WebDriver driver;
    private final WaitHelper waits;

    public Header(WebDriver driver) {
        this.driver = driver;
        this.waits = new WaitHelper(driver);
    }

    /** Menude gorunen kullanici adini dondurur (gorunur olana kadar bekler). */
    @Step("Menüdeki kullanıcı adı okunur")
    public String getLoggedInUsername() {
        return waits.visible(LOGGED_IN_USERNAME).getText();
    }

    /** "Logged in as" gorunmuyorsa true (zaten yoksa hemen doner). */
    public boolean isLoggedInUsernameHidden() {
        return waits.invisible(LOGGED_IN_USERNAME);
    }

    /** Beklemeden anlik kontrol: giris yapilmis mi? (temizlik adiminda kullanilir) */
    public boolean isLoggedInNow() {
        return !driver.findElements(DELETE_ACCOUNT_LINK).isEmpty();
    }

    public boolean isLogoutLinkVisible() {
        return isVisible(LOGOUT_LINK);
    }

    public boolean isSignupLoginLinkVisible() {
        return isVisible(SIGNUP_LOGIN_LINK);
    }

    /** Logout'a tiklar, login sayfasina yonlenene kadar bekler. */
    @Step("Çıkış yapılır")
    public LoginPage logout() {
        waits.clickable(LOGOUT_LINK).click();
        waits.urlContains("/login");
        return new LoginPage(driver);
    }

    @Step("Hesap silinir")
    public AccountDeletedPage deleteAccount() {
        waits.clickable(DELETE_ACCOUNT_LINK).click();
        return new AccountDeletedPage(driver);
    }

    private boolean isVisible(By locator) {
        try {
            waits.visible(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}