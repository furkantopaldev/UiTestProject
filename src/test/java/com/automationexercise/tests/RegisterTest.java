package com.automationexercise.tests;

import com.automationexercise.base.BaseTest;
import com.automationexercise.components.Header;
import com.automationexercise.models.User;
import com.automationexercise.pages.AccountCreatedPage;
import com.automationexercise.pages.AccountDeletedPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.SignupPage;
import com.automationexercise.utils.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    private Header header;

    @Test(description = "Yeni kullanici kaydi olusturulur, menude dogrulanir ve hesap silinir")
    public void registerNewUserThenDeleteAccount() {
        User user = TestDataGenerator.newUser();
        header = new Header(driver);

        SignupPage signupPage = new HomePage(driver)
                .openLoginPage()
                .startSignup(user.name(), user.email());
        AccountCreatedPage createdPage = signupPage.createAccount(user);

        Assert.assertTrue(createdPage.getTitleText().equalsIgnoreCase("Account Created!"),
                "Hesap olusturuldu basligi beklenenden farkli: " + createdPage.getTitleText());

        createdPage.continueToHome();

        Assert.assertEquals(header.getLoggedInUsername(), user.name(),
                "Menude gorunen kullanici adi kayit sirasinda girilen isimle ayni degil");

        AccountDeletedPage deletedPage = header.deleteAccount();

        Assert.assertTrue(deletedPage.getTitleText().equalsIgnoreCase("Account Deleted!"),
                "Hesap silindi basligi beklenenden farkli: " + deletedPage.getTitleText());
    }

    /** Test ortada kalirsa olusan hesap sitede cop olarak kalmasin. */
    @Override
    protected void cleanUp() {
        if (header != null && header.isLoggedInNow()) {
            header.deleteAccount();
        }
        header = null;
    }
}