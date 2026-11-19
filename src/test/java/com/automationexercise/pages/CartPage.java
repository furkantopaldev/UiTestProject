package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class CartPage extends BasePage {

    private static final By CART_TABLE = By.id("cart_info_table");
    private static final By CART_ROWS = By.cssSelector("#cart_info_table tbody tr");
    private static final By CHECKOUT_BUTTON = By.cssSelector(".check_out");

    // Misafir kullanici checkout'a basinca acilan uyari penceresi
    private static final By CHECKOUT_PROMPT_TEXT = By.xpath(
            "//div[contains(@class,'modal-body')]//p[contains(., 'Register / Login account to proceed on checkout')]");
    private static final By CHECKOUT_PROMPT_LOGIN_LINK = By.cssSelector(".modal-content a[href='/login']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        waits.visible(CART_TABLE);
    }

    public int getRowCount() {
        return driver.findElements(CART_ROWS).size();
    }

    public String getProductName(int productId) {
        return getText(rowCell(productId, ".cart_description h4 a"));
    }

    public String getCategory(int productId) {
        return getText(rowCell(productId, ".cart_description p"));
    }

    public String getPrice(int productId) {
        return getText(rowCell(productId, ".cart_price p"));
    }

    public String getQuantity(int productId) {
        return getText(rowCell(productId, ".cart_quantity button"));
    }

    public String getTotal(int productId) {
        return getText(rowCell(productId, ".cart_total_price"));
    }

    @Step("Proceed To Checkout tıklanır")
    public void proceedToCheckout() {
        click(CHECKOUT_BUTTON);
    }

    public boolean isCheckoutLoginPromptDisplayed() {
        return isDisplayed(CHECKOUT_PROMPT_TEXT);
    }

    public boolean isLoginLinkInPromptDisplayed() {
        return isDisplayed(CHECKOUT_PROMPT_LOGIN_LINK);
    }

    /** Sepet satirinin id'si urun numarasini tasir (ornegin product-1). */
    private By rowCell(int productId, String cellSelector) {
        return By.cssSelector("#product-" + productId + " " + cellSelector);
    }
}