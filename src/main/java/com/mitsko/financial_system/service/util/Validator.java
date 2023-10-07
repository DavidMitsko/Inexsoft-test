package com.mitsko.financial_system.service.util;

public class Validator {

    public static boolean validatePercent(int value) {
        return value >= 0 && value <= 100;
    }

}
