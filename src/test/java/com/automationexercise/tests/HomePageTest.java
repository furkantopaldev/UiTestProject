package com.automationexercise.tests;

import io.qameta.allure.*;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Automation Exercise UI Otomasyonu")
@Feature("Altyapı ve ölçüm")
public class HomePageTest extends BaseTest {

    @Story("Smoke: ana sayfa açılır")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Ana sayfa açılır, başlık doğru ve logo görünür")
    public void homePageShouldLoadSuccessfully() {
        HomePage homePage = new HomePage(driver);

        Assert.assertEquals(homePage.getTitle(), "Automation Exercise",
                "Ana sayfa başlığı beklenenden farklı");
        Assert.assertTrue(homePage.isLogoDisplayed(),
                "Ana sayfa logosu görünür değil");
    }
}
