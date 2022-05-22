package com.store.core.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StoreUtil {
    private StoreUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Date getDate(String dateToConvert){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(dateToConvert);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
