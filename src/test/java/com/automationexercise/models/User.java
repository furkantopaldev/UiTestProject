package com.automationexercise.models;

/** Kayit formundaki tum alanlari tasiyan degismez test verisi. */
public record User(
        String name,
        String email,
        String password,
        String birthDay,
        String birthMonth,
        String birthYear,
        String firstName,
        String lastName,
        String address,
        String country,
        String state,
        String city,
        String zipcode,
        String mobile) {
}