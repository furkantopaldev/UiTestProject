package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By LOGO = By.cssSelector(".logo img");
    private static final By SIGNUP_LOGIN_LINK = By.cssSelector("a[href='/login']");
    private static final By PRODUCTS_LINK = By.cssSelector("a[href='/products']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return isDisplayed(LOGO);
    }

    /** Menuden Signup / Login'e tiklar, URL degisene kadar bekler ve LoginPage dondurur. */
    public LoginPage openLoginPage() {
        click(SIGNUP_LOGIN_LINK);
        waits.urlContains("/login");
        return new LoginPage(driver);
    }

    /** Menuden Products'a tiklar, sayfa yuklenene kadar bekler ve ProductsPage dondurur. */
    public ProductsPage openProductsPage() {
        click(PRODUCTS_LINK);
        waits.urlContains("/products");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.waitUntilLoaded();
        return productsPage;
    }
}