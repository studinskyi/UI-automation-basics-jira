package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WebDate {

    public static String getCurrentDateTimeString() {
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return formatDate.format(d);
    }

    public static String getCurrentDateTimeToNameFile() {
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        return formatDate.format(d);
    }
}
