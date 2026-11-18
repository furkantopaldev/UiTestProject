package com.automationexercise.tests;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private static final int BLUE_TOP_ID = 1;

    @Test(groups = "known-issue", description = "CART-001: Urun listesinden sepete ekleme")
    public void addProductFromListShouldAppearInCart() {
        ProductsPage productsPage = new HomePage(driver).openProductsPage();

        productsPage.addToCart(BLUE_TOP_ID);
        CartPage cartPage = productsPage.viewCartFromModal();

        Assert.assertEquals(cartPage.getRowCount(), 1,
                "Sepette tam olarak 1 urun satiri olmali");
        Assert.assertEquals(cartPage.getProductName(BLUE_TOP_ID), "Blue Top",
                "Sepetteki urun adi beklenenden farkli");
        Assert.assertEquals(cartPage.getCategory(BLUE_TOP_ID), "Women > Tops",
                "Sepetteki urun kategorisi beklenenden farkli");
        Assert.assertEquals(cartPage.getPrice(BLUE_TOP_ID), "Rs. 500",
                "Sepetteki birim fiyat beklenenden farkli");
        Assert.assertEquals(cartPage.getQuantity(BLUE_TOP_ID), "1",
                "Sepetteki adet beklenenden farkli");
        Assert.assertEquals(cartPage.getTotal(BLUE_TOP_ID), "Rs. 500",
                "Sepetteki satir toplami beklenenden farkli");
    }
}