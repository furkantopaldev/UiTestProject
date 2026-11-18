package com.automationexercise.tests;

import com.automationexercise.base.UserTest;
import com.automationexercise.components.Header;
import com.automationexercise.models.User;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends UserTest {

    private static final String LOGIN_ERROR = "Your email or password is incorrect!";

    @Test(description = "AUTH-013: Dogru e-posta ve sifre ile giris yapilir")
    public void loginWithValidCredentialsShouldSucceed() {
        User user = registerUser();
        LoginPage loginPage = new Header(driver).logout();

        loginPage.login(user.email(), user.password());

        Header header = new Header(driver);
        Assert.assertEquals(header.getLoggedInUsername(), user.name(),
                "Giris sonrasi menude gorunen kullanici adi kayitli isimle ayni degil");
        Assert.assertTrue(header.isLogoutLinkVisible(),
                "Giris sonrasi menude Logout baglantisi gorunmuyor");
    }

    @Test(description = "AUTH-014: Kayitli e-posta ve yanlis sifre ile giris reddedilir")
    public void loginWithWrongPasswordShouldShowError() {
        User user = registerUser();
        LoginPage loginPage = new Header(driver).logout();

        loginPage.login(user.email(), "YanlisSifre!1");

        Assert.assertEquals(loginPage.getErrorMessage(), LOGIN_ERROR,
                "Yanlis sifrede beklenen hata mesaji gosterilmedi");
        Assert.assertFalse(new Header(driver).isLoggedInNow(),
                "Yanlis sifreyle kullanici oturum acmamali");
    }

    @Test(description = "AUTH-015: Kayitsiz e-posta ile giris denemesi hata mesaji gosterir")
    public void loginWithUnregisteredEmailShouldShowError() {
        LoginPage loginPage = new HomePage(driver).openLoginPage();

        Assert.assertTrue(loginPage.isLoginFormDisplayed(),
                "Login formu gorunur degil");
        Assert.assertTrue(loginPage.isErrorMessageHidden(),
                "Giris denenmeden once hata mesaji gorunmemeli");

        loginPage.login(TestDataGenerator.uniqueEmail("nouser"), "WrongPass123");

        Assert.assertEquals(loginPage.getErrorMessage(), LOGIN_ERROR,
                "Kayitsiz e-postada beklenen hata mesaji gosterilmedi");
    }
}