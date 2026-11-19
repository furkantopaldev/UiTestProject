package com.automationexercise.tests;

import io.qameta.allure.*;

import com.automationexercise.base.UserTest;
import com.automationexercise.components.Header;
import com.automationexercise.config.ConfigReader;
import com.automationexercise.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Automation Exercise UI Otomasyonu")
@Feature("Kimlik doğrulama (Register / Login)")
public class LogoutTest extends UserTest {

    @Story("AUTH-019 Çıkış")
    @Severity(SeverityLevel.CRITICAL)    
    @Test(description = "AUTH-019: Oturum kapatilir ve login sayfasina yonlenilir")
    public void logoutShouldEndSession() {
        registerUser();
        Header header = new Header(driver);

        LoginPage loginPage = header.logout();

        Assert.assertTrue(loginPage.isLoginFormDisplayed(),
                "Logout sonrasi login sayfasi gorunmuyor");
        Assert.assertTrue(header.isSignupLoginLinkVisible(),
                "Logout sonrasi menude Signup / Login baglantisi gorunmuyor");
        Assert.assertTrue(header.isLoggedInUsernameHidden(),
                "Logout sonrasi menude 'Logged in as' hala gorunuyor");

        // Oturumun gercekten bittigini ana sayfaya giderek de dogrula
        driver.get(ConfigReader.get("base.url"));
        Assert.assertTrue(header.isSignupLoginLinkVisible(),
                "Ana sayfada Signup / Login baglantisi gorunmuyor, oturum kapanmamis olabilir");
        Assert.assertFalse(header.isLoggedInNow(),
                "Logout sonrasi ana sayfada kullanici hala giris yapmis gorunuyor");
    }
}