package com.automationexercise.pages;

import com.automationexercise.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * Tum sayfa nesnelerinin ortak davranislari.
 * Her eylem once uygun kosulu bekler, sonra islemi yapar.
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

    protected void selectByText(By locator, String visibleText) {
        new Select(waits.visible(locator)).selectByVisibleText(visibleText);
    }

    /** Fareyi elementin ustune getirir (hover ile acilan katmanlar icin). */
    protected void hover(By locator) {
        new Actions(driver).moveToElement(waits.visible(locator)).perform();
    }

    /** Element gorunurse true; zaman asiminda false doner (hata firlatmaz). */
    protected boolean isDisplayed(By locator) {
        try {
            waits.visible(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /** Element gorunmuyorsa true; zaman asiminda (hala gorunuyorsa) false doner. */
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