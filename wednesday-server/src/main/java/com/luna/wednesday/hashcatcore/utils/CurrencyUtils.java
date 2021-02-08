package com.luna.wednesday.hashcatcore.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CurrencyUtils {
    private static final DecimalFormat BTC_DECIMAL_FORMAT = new DecimalFormat("0.00000000");

    public static final String formatBTC(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }
        return BTC_DECIMAL_FORMAT.format(bigDecimal);
    }

    private static final DecimalFormat CNY_DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static final String formatCNY(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }
        return CNY_DECIMAL_FORMAT.format(bigDecimal);
    }
}
