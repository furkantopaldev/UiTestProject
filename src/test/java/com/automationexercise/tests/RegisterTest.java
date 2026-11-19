package com.automationexercise.tests;

import io.qameta.allure.*;

import com.automationexercise.base.UserTest;
import com.automationexercise.components.Header;
import com.automationexercise.models.User;
import com.automationexercise.pages.AccountCreatedPage;
import com.automationexercise.pages.AccountDeletedPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.pages.SignupPage;
import com.automationexercise.utils.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Automation Exercise UI Otomasyonu")
@Feature("Kimlik doğrulama (Register / Login)")
public class RegisterTest extends UserTest {
    
    @Story("AUTH-001 Yeni kayıt")
    @Severity(SeverityLevel.CRITICAL)    
    @Test(description = "AUTH-001: Yeni kullanici kaydi olusturulur, menude dogrulanir ve hesap silinir")
    public void registerNewUserThenDeleteAccount() {
        User user = TestDataGenerator.newUser();
        Header header = new Header(driver);

        SignupPage signupPage = new HomePage(driver)
                .openLoginPage()
                .startSignup(user.name(), user.email());
        AccountCreatedPage createdPage = signupPage.createAccount(user);
        registeredUser = user;   // hesap olustu: test ortada kalirsa temizlik kancasi siler

        Assert.assertTrue(createdPage.getTitleText().equalsIgnoreCase("Account Created!"),
                "Hesap olusturuldu basligi beklenenden farkli: " + createdPage.getTitleText());

        createdPage.continueToHome();

        Assert.assertEquals(header.getLoggedInUsername(), user.name(),
                "Menude gorunen kullanici adi kayit sirasinda girilen isimle ayni degil");

        AccountDeletedPage deletedPage = header.deleteAccount();
        registeredUser = null;   // hesap silindi, temizlenecek bir sey kalmadi

        Assert.assertTrue(deletedPage.getTitleText().equalsIgnoreCase("Account Deleted!"),
                "Hesap silindi basligi beklenenden farkli: " + deletedPage.getTitleText());
    }

    @Story("AUTH-002 Kayıtlı e-postayla tekrar kayıt")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "AUTH-002: Kayitli e-posta ile tekrar kayit denemesi hata verir")
    public void registerWithExistingEmailShouldShowError() {
        User user = registerUser();
        LoginPage loginPage = new Header(driver).logout();

        loginPage.submitSignup(user.name(), user.email());

        Assert.assertEquals(loginPage.getSignupErrorMessage(), "Email Address already exist!",
                "Kayitli e-postayla kayitta beklenen hata mesaji gosterilmedi");
        Assert.assertTrue(loginPage.isLoginFormDisplayed(),
                "Kayit formuna gecilmemeli, login sayfasinda kalinmali");
    }
}