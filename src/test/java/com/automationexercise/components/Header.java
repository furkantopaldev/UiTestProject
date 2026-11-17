package com.automationexercise.components;
import com.automationexercise.pages.AccountDeletedPage;
import com.automationexercise.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Giris yapilmis kullanicinin ust menusu. Tum sayfalarda ortaktir. */
public class Header {

    private static final By LOGGED_IN_USERNAME = By.xpath("//a[contains(., 'Logged in as')]/b");
    private static final By DELETE_ACCOUNT_LINK = By.cssSelector("a[href='/delete_account']");

    private final WebDriver driver;
    private final WaitHelper waits;

    public Header(WebDriver driver) {
        this.driver = driver;
        this.waits = new WaitHelper(driver);
    }

    /** Menude gorunen kullanici adini dondurur (gorunur olana kadar bekler). */
    public String getLoggedInUsername() {
        return waits.visible(LOGGED_IN_USERNAME).getText();
    }

    /** Beklemeden anlik kontrol: giris yapilmis mi? (temizlik adiminda kullanilir) */
    public boolean isLoggedInNow() {
        return !driver.findElements(DELETE_ACCOUNT_LINK).isEmpty();
    }

    public AccountDeletedPage deleteAccount() {
        waits.clickable(DELETE_ACCOUNT_LINK).click();
        return new AccountDeletedPage(driver);
    }
}