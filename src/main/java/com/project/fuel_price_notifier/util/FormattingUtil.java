 package com.project.fuel_price_notifier.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class FormattingUtil {
    public static Float parseLocalFloat(String localFloat) throws ParseException {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator('.');
        dfs.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(dfs);
        return df.parse(localFloat).floatValue();
    }
}
