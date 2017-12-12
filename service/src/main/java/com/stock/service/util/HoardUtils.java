package com.stock.service.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HoardUtils {
    public static Double roundDouble(Double d, int scale) {
        return new BigDecimal(d).setScale(scale, RoundingMode.UP).doubleValue();
    }
}
