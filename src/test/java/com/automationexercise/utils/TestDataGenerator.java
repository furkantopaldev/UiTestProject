package com.automationexercise.utils;

public final class TestDataGenerator {

    private TestDataGenerator() {
    }

    /** Her çağrıda benzersiz bir e-posta üretir (zaman damgası tabanlı). */
    public static String uniqueEmail(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + "@example.com";
    }
}