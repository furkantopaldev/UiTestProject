package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    private static final By SEARCH_INPUT = By.id("search_product");
    private static final By SEARCH_BUTTON = By.id("submit_search");

    // Iki baslik ayni class'i tasiyor, yalnizca metin farkli: bu yuzden metne gore XPath
    private static final By ALL_PRODUCTS_TITLE = By.xpath("//h2[normalize-space()='All Products']");
    private static final By SEARCHED_PRODUCTS_TITLE = By.xpath("//h2[normalize-space()='Searched Products']");

    private static final By PRODUCT_NAMES = By.cssSelector(".product-image-wrapper .productinfo p");

    // Sepete eklenince acilan "Added!" penceresi (menudeki Cart baglantisiyla karismasin diye kisitli)
    private static final By ADDED_MODAL_VIEW_CART = By.cssSelector(".modal-content a[href='/view_cart']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        waits.visible(SEARCH_INPUT);
    }

    public boolean isAllProductsTitleDisplayed() {
        return isDisplayed(ALL_PRODUCTS_TITLE);
    }

    public boolean isSearchedProductsTitleDisplayed() {
        return isDisplayed(SEARCHED_PRODUCTS_TITLE);
    }

    /** Arama yapar. Sonucun geldigini beklemek cagiranin isidir (isSearchedProductsTitleDisplayed). */
    public void search(String term) {
        type(SEARCH_INPUT, term);
        click(SEARCH_BUTTON);
    }

    public List<String> getProductNames() {
        return driver.findElements(PRODUCT_NAMES).stream()
                .map(element -> element.getText().trim())
                .collect(Collectors.toList());
    }

    /**
     * Urunu sepete ekler. Her urunde iki "Add to cart" butonu var: kartin kendisinde ve
     * uzerine gelince acilan katmanda. Kullanicinin yaptigi gibi once kartin ustune gelinir,
     * sonra katmandaki buton tiklanir.
     */
    public void addToCart(int productId) {
        By cardButton = By.cssSelector(".productinfo a.add-to-cart[data-product-id='" + productId + "']");
        By overlayButton = By.cssSelector(".product-overlay a.add-to-cart[data-product-id='" + productId + "']");
        hover(cardButton);
        click(overlayButton);
    }

    /** "Added!" penceresindeki View Cart'a tiklar ve sepet sayfasini dondurur. */
    public CartPage viewCartFromModal() {
        click(ADDED_MODAL_VIEW_CART);
        waits.urlContains("/view_cart");
        CartPage cartPage = new CartPage(driver);
        cartPage.waitUntilLoaded();
        return cartPage;
    }
}