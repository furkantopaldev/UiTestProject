package com.automationexercise.tests;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Statik (Thread.sleep) ve dinamik (WaitHelper) beklemeyi ayni adimda karsilastirir.
 * Thread.sleep projede yalnizca bu sinifta, ornek amacli kullanilir.
 */
public class WaitComparisonTest extends BaseTest {

    private static final String EXPECTED_ERROR = "Your email or password is incorrect!";

    @Test(description = "Statik bekleme (100 ms): sonuc sansa bagli, yalnizca olculur")
    public void staticShortWait() {
        LoginPage loginPage = submitWrongLogin();

        long start = System.nanoTime();
        sleep(100);
        String text = loginPage.getErrorMessageNow();

        report("STATIK 100 ms", start, text.isEmpty() ? "MESAJ BULUNAMADI" : "mesaj bulundu");
    }

    @Test(description = "Statik bekleme (5000 ms): mesaj bulunur ama gereksiz beklenir")
    public void staticLongWait() {
        LoginPage loginPage = submitWrongLogin();

        long start = System.nanoTime();
        sleep(5000);
        String text = loginPage.getErrorMessageNow();

        report("STATIK 5000 ms", start, text.isEmpty() ? "MESAJ BULUNAMADI" : "mesaj bulundu");
        Assert.assertEquals(text, EXPECTED_ERROR,
                "5 sn statik bekleme sonrasinda beklenen hata mesaji okunamadi");
    }

    @Test(description = "Dinamik bekleme: mesaj gorunur olur olmaz devam eder")
    public void dynamicWait() {
        LoginPage loginPage = submitWrongLogin();

        long start = System.nanoTime();
        String text = loginPage.getErrorMessage();

        report("DINAMIK", start, "mesaj bulundu");
        Assert.assertEquals(text, EXPECTED_ERROR,
                "Dinamik bekleme sonrasinda beklenen hata mesaji okunamadi");
    }

    private LoginPage submitWrongLogin() {
        LoginPage loginPage = new HomePage(driver).openLoginPage();
        loginPage.login(TestDataGenerator.uniqueEmail("nouser"), "WrongPass123");
        return loginPage;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void report(String method, long startNanos, String result) {
        long elapsedMs = (System.nanoTime() - startNanos) / 1_000_000;
        System.out.println("[BEKLEME] " + method + " | " + elapsedMs + " ms | " + result);
    }
}