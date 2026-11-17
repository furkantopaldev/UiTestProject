package com.automationexercise.pages;

import com.automationexercise.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Tüm sayfa nesnelerinin ortak davranışları.
 * Her eylem önce uygun koşulu bekler, sonra işlemi yapar.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitHelper waits;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waits = new WaitHelper(driver);
    }

    protected void click(By locator) {
        waits.clickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = waits.visible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waits.visible(locator).getText();
    }

    /** Element görünürse true; zaman aşımında false döner (hata fırlatmaz). */
    protected boolean isDisplayed(By locator) {
        try {
            waits.visible(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /** Element görünmüyorsa true; zaman aşımında (hâlâ görünüyorsa) false döner. */
    protected boolean isNotDisplayed(By locator) {
        try {
            return waits.invisible(locator);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }
}