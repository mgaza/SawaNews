package com.palsawa.news.utilities;

import android.graphics.Color;
import android.webkit.WebView;

/**
 * Created by root on 6/27/16.
 */
public class ArticleBuilder
{
    public static String HEADING_STYLE = "body{direction:rtl;text-align:justify;line-height:26px;font-size:18px;font-weight:bold;color:rgba(0,0,0,0.87);}";
    public static String CAPTION_STYLE = "body{direction:rtl;text-align:right;line-height:15px;font-size:12px;color:rgba(0,0,0,0.54);}";
    public static String BODY_STYLE = "body{direction:rtl;text-align:justify;line-height:24px;font-size:15px;color:rgba(0,0,0,0.87);}";
    public static void build(WebView webView,String article)
    {
     buildArticle(webView,article,BODY_STYLE);
    }

    public static void buildWithStyle(WebView webView,String article,String style)
    {
        buildArticle(webView,article,style);
    }

    private static void buildArticle(WebView webView,String article,String style)
    {
        String data = "<html>";
        data += "<head>";
        data += "<meta charset='UTF-8'/>";
        data += "<style>";
        data += style;
        data += "img{width:100%;height:auto;border:1px solid rgb(221,221,221);} iframe{display:none;}";
        data += "video{width:100%;height:auto;}";
        data += "</style>";
        data += "</head>";
        data += "<body>";
        data += article;
        data += "</body>";
        data += "</html>";

        webView.loadData(data,"text/html; charset=UTF-8",null);

        webView.getSettings();
        webView.setBackgroundColor(Color.TRANSPARENT);
    }
}
