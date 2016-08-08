package com.palsawa.news.utilities;

/**
 * Created by root on 6/21/16.
 */
public class DateBuilder {
    public static String getDate(String y, String m, String d) {
        return y + "-" + m + "-" + d;
    }

    public static String getTime(String h, String min) {
        return h + ":" + min;
    }
}
