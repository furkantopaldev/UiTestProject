package com.automationexercise.base;

import com.automationexercise.config.ConfigReader;
import com.automationexercise.driver.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Tüm test sınıflarının atası. Her test kendi temiz tarayıcısıyla başlar ve kapanır,
 * böylece testler birbirinin durumundan etkilenmez.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

       @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                captureFailureEvidence(result.getName());
            }
            cleanUp();
        } catch (Exception e) {
            System.err.println("Temizlik adimi basarisiz: " + e.getMessage());
        } finally {
            DriverFactory.quitDriver();
        }
    }

    /** Alt siniflar test sonrasi temizlik icin ezebilir (ornegin olusturulan hesabi silme). */
    protected void cleanUp() {
    }

    /** Başarısız testte ekran görüntüsünü ve URL'i kaydeder. Tarayıcı kapanmadan önce çağrılmalı. */
    private void captureFailureEvidence(String testName) {
        try {
            System.err.println("[BAŞARISIZ] " + testName + " | URL: " + driver.getCurrentUrl());

            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Hata anı ekran görüntüsü", "image/png",
                    new ByteArrayInputStream(png), "png");

            Path dir = Path.of("target", "screenshots");
            Files.createDirectories(dir);
            Files.write(dir.resolve(testName + "_" + System.currentTimeMillis() + ".png"), png);
        } catch (Exception e) {
            System.err.println("Ekran görüntüsü alınamadı: " + e.getMessage());
        }
    }
}