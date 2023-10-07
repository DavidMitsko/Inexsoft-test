package com.mitsko.financial_system.service.util;

public class Validator {

    public static boolean validatePercent(int value) {
        return value >= 0 && value <= 100;
    }

    public static boolean validateAge(int age) {
        return age <= 0;
    }

}
