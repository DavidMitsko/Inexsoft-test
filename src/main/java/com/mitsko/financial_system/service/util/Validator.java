package com.mitsko.financial_system.service.util;

import java.math.BigDecimal;

public class Validator {

    public static boolean validatePercent(int value) {
        return value >= 0 && value <= 100;
    }

    public static boolean validateAge(int age) {
        return age <= 0;
    }

    public static boolean validateAmount(BigDecimal amount) {
        return amount.compareTo(new BigDecimal(0)) < 0;
    }

}
