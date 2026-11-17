package com.automationexercise.pages;

import com.automationexercise.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** "Enter Account Information" sayfasi. */
public class SignupPage extends BasePage {

    private static final By GENDER_MR = By.cssSelector("#id_gender1");
    private static final By PASSWORD = By.cssSelector("[data-qa='password']");
    private static final By DAYS = By.cssSelector("[data-qa='days']");
    private static final By MONTHS = By.cssSelector("[data-qa='months']");
    private static final By YEARS = By.cssSelector("[data-qa='years']");
    private static final By FIRST_NAME = By.cssSelector("[data-qa='first_name']");
    private static final By LAST_NAME = By.cssSelector("[data-qa='last_name']");
    private static final By ADDRESS = By.cssSelector("[data-qa='address']");
    private static final By COUNTRY = By.cssSelector("[data-qa='country']");
    private static final By STATE = By.cssSelector("[data-qa='state']");
    private static final By CITY = By.cssSelector("[data-qa='city']");
    private static final By ZIPCODE = By.cssSelector("[data-qa='zipcode']");
    private static final By MOBILE = By.cssSelector("[data-qa='mobile_number']");
    private static final By CREATE_ACCOUNT = By.cssSelector("[data-qa='create-account']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    /** Sayfanin yuklendigini sifre alani gorunur olunca anlar. */
    public void waitUntilLoaded() {
        waits.visible(PASSWORD);
    }

    public AccountCreatedPage createAccount(User user) {
        click(GENDER_MR);
        type(PASSWORD, user.password());
        selectByText(DAYS, user.birthDay());
        selectByText(MONTHS, user.birthMonth());
        selectByText(YEARS, user.birthYear());
        type(FIRST_NAME, user.firstName());
        type(LAST_NAME, user.lastName());
        type(ADDRESS, user.address());
        selectByText(COUNTRY, user.country());
        type(STATE, user.state());
        type(CITY, user.city());
        type(ZIPCODE, user.zipcode());
        type(MOBILE, user.mobile());
        click(CREATE_ACCOUNT);
        return new AccountCreatedPage(driver);
    }
}