package com.automationexercise.utils;

import com.automationexercise.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Koşula bağlı (dinamik) bekleme yardımcıları.
 * Hiçbir yerde Thread.sleep kullanılmaz; koşul sağlanır sağlanmaz devam edilir,
 * süre dolarsa neyin beklendiğini ve tarayıcının o an nerede olduğunu söyleyen
 * anlaşılır bir hata fırlatılır.
 */
public final class WaitHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final int timeoutSeconds;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        this.timeoutSeconds = ConfigReader.getInt("explicit.wait.seconds");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    /** Element DOM'da var ve görünür olana kadar bekler. */
    public WebElement visible(By locator) {
        return until(ExpectedConditions.visibilityOfElementLocated(locator),
                "görünür olmadı", locator);
    }

    /** Element görünür ve etkileşime (tıklamaya) hazır olana kadar bekler. */
    public WebElement clickable(By locator) {
        return until(ExpectedConditions.elementToBeClickable(locator),
                "tıklanabilir olmadı", locator);
    }

    /** Element görünmez olana ya da DOM'dan kalkana kadar bekler. */
    public boolean invisible(By locator) {
        return until(ExpectedConditions.invisibilityOfElementLocated(locator),
                "kaybolmadı", locator);
    }

    /** URL belirtilen parçayı içerene kadar bekler (ör. girişten sonra yönlenme). */
    public boolean urlContains(String fragment) {
        return until(ExpectedConditions.urlContains(fragment),
                "URL '" + fragment + "' içermedi", null);
    }

    private <T> T until(ExpectedCondition<T> condition, String reason, By locator) {
        try {
            return wait.until(condition);
        } catch (TimeoutException e) {
            String target = (locator == null) ? "" : " | element: " + locator;
            throw new TimeoutException(
                    timeoutSeconds + " saniye içinde koşul sağlanmadı: " + reason + target
                            + " | URL: " + currentUrl()
                            + " | Sayfa başlığı: " + currentTitle(), e);
        }
    }

    private String currentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            return "okunamadı";
        }
    }

    private String currentTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            return "okunamadı";
        }
    }
}