package com.automationexercise.tests;

import io.qameta.allure.*;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Automation Exercise UI Otomasyonu")
@Feature("Ödeme(Checkout)")
public class CheckoutTest extends BaseTest {

    private static final int BLUE_TOP_ID = 1;


    
        @Story("CHK-002 Misafir ödeme engeli")
        @Severity(SeverityLevel.CRITICAL)
        @Test(groups = "known-issue", description = "CART-001: Urun listesinden sepete ekleme")    public void guestCannotProceedToCheckout() {
        ProductsPage productsPage = new HomePage(driver).openProductsPage();
        productsPage.addToCart(BLUE_TOP_ID);
        CartPage cartPage = productsPage.viewCartFromModal();

        cartPage.proceedToCheckout();

        Assert.assertTrue(cartPage.isCheckoutLoginPromptDisplayed(),
                "Misafir kullanici icin 'Register / Login account to proceed on checkout' uyarisi gorunmuyor");
        Assert.assertTrue(cartPage.isLoginLinkInPromptDisplayed(),
                "Uyari penceresinde Register / Login baglantisi gorunmuyor");
        Assert.assertTrue(driver.getCurrentUrl().contains("/view_cart"),
                "Misafir kullanici sepet sayfasinda kalmali, URL: " + driver.getCurrentUrl());
    }
}