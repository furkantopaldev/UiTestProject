package com.automationexercise.tests;

import io.qameta.allure.*;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

@Epic("Automation Exercise UI Otomasyonu")
@Feature("Ürünler ve arama (Products)")
public class ProductsTest extends BaseTest {

    /** PROD-003 (kucuk harf) ve PROD-004 (buyuk/karisik harf) ayni adimlari izler. */
    @DataProvider(name = "existingSearchTerms")
    public Object[][] existingSearchTerms() {
        return new Object[][]{{"jeans"}, {"JEANS"}, {"JeAnS"}};
    }

    @Story("PROD-003/004 Ürün arama")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "existingSearchTerms",description = "PROD-003/004: Var olan urun aranir, sonuclarin hepsi arama terimini icerir")
    public void searchForExistingProductShouldListMatchingProducts(String term) {
        ProductsPage productsPage = new HomePage(driver).openProductsPage();

        Assert.assertTrue(productsPage.isAllProductsTitleDisplayed(),
                "Products sayfasinda 'All Products' basligi gorunmuyor");

        productsPage.search(term);
        productsPage.waitForSearchedProductsTitle();

        List<String> names = productsPage.getProductNames();
        Assert.assertFalse(names.isEmpty(),
                "'" + term + "' aramasi hic urun dondurmedi");

        List<String> unrelated = names.stream()
                .filter(name -> !name.toLowerCase().contains(term.toLowerCase()))
                .collect(Collectors.toList());
        Assert.assertTrue(unrelated.isEmpty(),
                "'" + term + "' terimini icermeyen urunler listelendi: " + unrelated);
    }
    @Story("PROD-006 Olmayan ürün")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "PROD-006: Var olmayan urun aranir, sonuc listesi bos olur")
    public void searchForNonExistingProductShouldListNothing() {
        ProductsPage productsPage = new HomePage(driver).openProductsPage();

        productsPage.search("asdasd");
        productsPage.waitForSearchedProductsTitle();

        Assert.assertTrue(productsPage.getProductNames().isEmpty(),
                "Anlamsiz arama urun listelememeliydi, listelenenler: " + productsPage.getProductNames());
    }

        @Story("PROD-001 Ürün Listesi")
        @Severity(SeverityLevel.CRITICAL)
        @Test(description = "PROD-001: Products sayfasi tum urunleri listeler")
    public void allProductsPageShouldListProducts() {
        ProductsPage productsPage = new HomePage(driver).openProductsPage();

        Assert.assertTrue(productsPage.isAllProductsTitleDisplayed(),
                "Products sayfasinda 'All Products' basligi gorunmuyor");

        List<String> names = productsPage.getProductNames();
        Assert.assertTrue(names.size() > 1,
                "Urun listesi birden fazla urun icermeli, listelenen sayisi: " + names.size());
        Assert.assertTrue(names.contains("Blue Top"),
                "Urun listesinde 'Blue Top' bulunmuyor, listelenenler: " + names);
    }
}