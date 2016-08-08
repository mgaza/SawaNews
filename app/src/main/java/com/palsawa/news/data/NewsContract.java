package com.palsawa.news.data;

import android.provider.BaseColumns;

/**
 * Created by root on 5/15/16.
 */
public class NewsContract {
    public static String TABLE_ARTICLE = "articles";

    public static class Article implements BaseColumns {
        public static String COLUMN_ID = "_id";
        public static String COLUMN_ARTICLE_ID = "article_id";
        public static String COLUMN_CAT = "cat";
        public static String COLUMN_TITLE = "title";
        public static String COLUMN_IMAGE_URL = "image";
        public static String COLUMN_CONTENT = "content";
        public static String COLUMN_CONTENT_PART = "content_part";
        public static String COLUMN_YEAR = "year";
        public static String COLUMN_MONTH = "month";
        public static String COLUMN_DAY = "day";
        public static String COLUMN_HOUR = "hour";
        public static String COLUMN_MINUTE = "min";
        public static String COLUMN_TIME = "time";
    }
}
