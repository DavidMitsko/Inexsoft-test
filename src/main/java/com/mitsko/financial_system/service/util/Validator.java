package com.mitsko.financial_system.service.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern UUID_REGEX =
            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public static boolean validatePercent(int value) {
        return value >= 0 && value <= 100;
    }

    public static boolean validateAge(int age) {
        return age <= 0;
    }

    public static boolean validateAmount(BigDecimal amount) {
        return amount.compareTo(new BigDecimal(0)) < 0;
    }

    public static boolean validateUuid(String uuid) {
        return UUID_REGEX.matcher(uuid).matches();
    }

}
