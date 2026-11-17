package com.automationexercise.base;

import com.automationexercise.components.Header;
import com.automationexercise.config.ConfigReader;
import com.automationexercise.models.User;
import com.automationexercise.pages.AccountCreatedPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.TestDataGenerator;

/**
 * Kayitli kullanici gerektiren testlerin ust sinifi.
 * Her test kendi kullanicisini olusturur; test nasil biterse bitsin hesap silinmeye calisilir.
 */
public abstract class UserTest extends BaseTest {

    /** Sitede olusturulan kullanici. null ise temizlenecek hesap yoktur. */
    protected User registeredUser;

    /** Yeni kullaniciyi arayuzden kaydeder; kayit sonunda kullanici giris yapmis halde ana sayfadadir. */
    protected User registerUser() {
        User user = TestDataGenerator.newUser();
        AccountCreatedPage createdPage = new HomePage(driver)
                .openLoginPage()
                .startSignup(user.name(), user.email())
                .createAccount(user);
        registeredUser = user;   // hesap olusturuldu: bundan sonra bir hata olsa bile temizlenecek
        createdPage.continueToHome();
        return user;
    }

    @Override
    protected void cleanUp() {
        // Referansi hemen sifirla: temizlik hata verse bile sonraki test eski kullaniciyi devralmasin.
        User user = registeredUser;
        registeredUser = null;
        if (user == null) {
            return;
        }

        Header header = new Header(driver);
        if (!header.isLoggedInNow()) {
            driver.get(ConfigReader.get("base.url") + "/login");
            new LoginPage(driver).login(user.email(), user.password());
        }
        if (header.isLoggedInNow()) {
            header.deleteAccount();
        }
    }
}