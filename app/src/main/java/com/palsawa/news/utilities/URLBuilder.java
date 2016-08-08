package com.palsawa.news.utilities;

/**
 * Created by root on 7/3/16.
 */
public class URLBuilder {
    public static String getArticleURL(String cat, String date, int id) {
        String url = "http://www.palsawa.com/news/";
        int c = Integer.parseInt(cat) - 1;
        for (String s : date.split("-")) {
            url += s + "/";
        }
        if (c >= 14) {
            c -= 1;
        }
        url += CatContent.cats.get(c)[1] + "/";
        url += id + ".html";

        return url;
    }
}
