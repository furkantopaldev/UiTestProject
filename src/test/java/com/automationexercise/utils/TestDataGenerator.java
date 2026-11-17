package com.automationexercise.utils;

import com.automationexercise.models.User;

public final class TestDataGenerator {

    private TestDataGenerator() {
    }

    /** Her cagrida benzersiz bir e-posta uretir (zaman damgasi tabanli). */
    public static String uniqueEmail(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + "@example.com";
    }

    /** Her cagrida benzersiz e-posta ve kullanici adiyla yeni bir test kullanicisi uretir. */
    public static User newUser() {
        String suffix = String.valueOf(System.currentTimeMillis());
        return new User(
                "tester" + suffix.substring(suffix.length() - 6),
                uniqueEmail("tester"),
                "Test1234!",
                "15", "May", "1995",
                "Test", "User",
                "Test Street 12",
                "Canada", "Ontario", "Toronto", "M5V 2T6",
                "5551234567");
    }
}