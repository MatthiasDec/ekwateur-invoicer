package org.invoicer;

import java.math.BigDecimal;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String REFERENCE_FORMAT = "^EKW[0-9]{9}$";
    public static final String NUMBER_FORMAT = "\\d+(\\.\\d+)?";
    public static final BigDecimal individualElectricalRate = BigDecimal.valueOf(0.133);
    public static final BigDecimal individualGasRate = BigDecimal.valueOf(0.108);
    public static final BigDecimal smallBusinessElectricalRate = BigDecimal.valueOf(0.112);
    public static final BigDecimal smallBusinessGasRate = BigDecimal.valueOf(0.117);
    public static final BigDecimal bigBusinessElectricalRate = BigDecimal.valueOf(0.110);
    public static final BigDecimal bigBusinessGasRate = BigDecimal.valueOf(0.123);
    public static final BigDecimal bigBusinessSalesLimit = new BigDecimal(1000000);
}
