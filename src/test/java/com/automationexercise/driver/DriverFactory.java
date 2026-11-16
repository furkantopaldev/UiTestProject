package com.automationexercise.driver;

import com.automationexercise.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Tarayıcıyı oluşturur ve her thread'e ayrı bir WebDriver verir (paralel çalışmaya hazır).
 * Sürücü indirme işini Selenium Manager otomatik yapar; ayrıca chromedriver kurmana gerek yok.
 */
public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    /** Automation Exercise'taki reklam/vignette pencereleri testleri rastgele kırdığı için engelliyoruz. */
    private static final List<String> BLOCKED_URLS = List.of(
            "*googlesyndication.com*",
            "*doubleclick.net*",
            "*googleadservices.com*",
            "*adservice.google.com*",
            "*fundingchoicesmessages.google.com*"
    );

    private DriverFactory() {
    }

    public static void initDriver() {
        String browser = ConfigReader.get("browser").toLowerCase();
        boolean headless = ConfigReader.getBoolean("headless");

        WebDriver driver = switch (browser) {
            case "chrome" -> createChrome(headless);
            case "edge" -> createEdge(headless);
            case "firefox" -> createFirefox(headless);
            default -> throw new IllegalArgumentException(
                    "Desteklenmeyen tarayıcı: '" + browser + "'. Geçerli değerler: chrome, edge, firefox");
        };

        // Implicit wait kapalı: explicit wait ile karışınca bekleme süreleri öngörülemez hale gelir.
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getInt("page.load.timeout")));
        if (!headless) {
            driver.manage().window().maximize();
        }
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver başlatılmamış. Önce DriverFactory.initDriver() çağrılmalı.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    private static WebDriver createChrome(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications", "--window-size=1920,1080");
        if (headless) {
            options.addArguments("--headless=new");
        }
        ChromeDriver driver = new ChromeDriver(options);
        blockAds(driver);
        return driver;
    }

    private static WebDriver createEdge(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications", "--window-size=1920,1080");
        if (headless) {
            options.addArguments("--headless=new");
        }
        EdgeDriver driver = new EdgeDriver(options);
        blockAds(driver);
        return driver;
    }

    private static WebDriver createFirefox(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }
        // Not: Firefox'ta CDP olmadığı için reklam engelleme uygulanmıyor.
        return new FirefoxDriver(options);
    }

    private static void blockAds(ChromiumDriver driver) {
        driver.executeCdpCommand("Network.enable", Map.of());
        driver.executeCdpCommand("Network.setBlockedURLs", Map.<String, Object>of("urls", BLOCKED_URLS));
    }
}
