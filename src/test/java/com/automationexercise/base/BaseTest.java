package com.automationexercise.base;

import com.automationexercise.config.ConfigReader;
import com.automationexercise.driver.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Tum test siniflarinin atasi. Her test kendi temiz tarayicisiyla baslar ve kapanir,
 * boylece testler birbirinin durumundan etkilenmez.
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

    /** Basarisiz testte ekran goruntusunu, URL'i ve tarayici konsol hatalarini kaydeder. */
    private void captureFailureEvidence(String testName) {
        try {
            System.err.println("[BASARISIZ] " + testName + " | URL: " + driver.getCurrentUrl());

            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Hata ani ekran goruntusu", "image/png",
                    new ByteArrayInputStream(png), "png");

            Path dir = Path.of("target", "screenshots");
            Files.createDirectories(dir);
            Files.write(dir.resolve(testName + "_" + System.currentTimeMillis() + ".png"), png);
        } catch (Exception e) {
            System.err.println("Ekran goruntusu alinamadi: " + e.getMessage());
        }
        captureBrowserConsole(testName);
    }

    /** Tarayici konsolundaki uyari ve hatalari yazdirir (basarisiz ag istekleri, JavaScript hatalari). */
    private void captureBrowserConsole(String testName) {
        try {
            List<LogEntry> entries = driver.manage().logs().get(LogType.BROWSER).getAll();
            String relevant = entries.stream()
                    .filter(entry -> entry.getLevel().intValue() >= Level.WARNING.intValue())
                    .limit(15)
                    .map(entry -> "[" + entry.getLevel() + "] " + entry.getMessage())
                    .collect(Collectors.joining("\n"));
            if (relevant.isEmpty()) {
                System.err.println("[KONSOL] " + testName + " | uyari ya da hata kaydi yok");
                return;
            }
            System.err.println("[KONSOL] " + testName + "\n" + relevant);
            Allure.addAttachment("Tarayici konsolu", relevant);
        } catch (Exception e) {
            System.err.println("[KONSOL] okunamadi: " + e.getMessage());
        }
    }
}