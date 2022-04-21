package net.lanternmc.AllUtils.JavaUtils;

import java.text.DecimalFormat;

public class NumberUtils {
    private static DecimalFormat df = (DecimalFormat)DecimalFormat.getInstance();
    
    public static String formatDouble2(double paramDouble) {
        df.setMaximumFractionDigits(2);
        return df.format(paramDouble);
    }
    
    public static String formatDouble8(double paramDouble) {
        df.setMaximumFractionDigits(8);
        return df.format(paramDouble);
    }
    
    public static String formatDouble4(double paramDouble) {
        df.setMaximumFractionDigits(4);
        return df.format(paramDouble);
    }
}