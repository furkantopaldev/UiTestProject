package com.automationexercise.base;

import com.automationexercise.config.ConfigReader;
import com.automationexercise.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Tüm test sınıflarının atası. Her test kendi temiz tarayıcısıyla başlar ve kapanır,
 * böylece testler birbirinin durumundan etkilenmez.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
