package com.automationexercise.tests;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Kayıtsız e-posta ile giriş denemesi hata mesajı gösterir")
    public void loginWithUnregisteredEmailShouldShowError() {
        LoginPage loginPage = new HomePage(driver).openLoginPage();

        Assert.assertTrue(loginPage.isLoginFormDisplayed(),
                "Login formu görünür değil");
        Assert.assertTrue(loginPage.isErrorMessageHidden(),
                "Giriş denenmeden önce hata mesajı görünmemeli");

        loginPage.login(TestDataGenerator.uniqueEmail("nouser"), "WrongPass123");

        Assert.assertEquals(loginPage.getErrorMessage(),
                "Your email or password is incorrect!",
                "Hatalı girişte beklenen hata mesajı gösterilmedi");
    }
}