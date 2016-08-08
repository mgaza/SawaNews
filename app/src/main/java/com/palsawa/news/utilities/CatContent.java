package com.palsawa.news.utilities;

import java.util.HashMap;

/**
 * Created by root on 7/3/16.
 */
public class CatContent {
    public static HashMap<Integer, String[]> cats;

    static {
        cats = new HashMap<>();
        cats.put(0, new String[]{"أهم الأخبار", "main", "1"});
        cats.put(1, new String[]{"أخبار محلية", "local", "2"});
        cats.put(2, new String[]{"أخبار المؤسسات", "instu", "3"});
        cats.put(3, new String[]{"مقابلات خاصة", "special", "4"});
        cats.put(4, new String[]{"تقارير", "reports", "5"});
        cats.put(5, new String[]{"عربي دولي", "inter", "6"});
        cats.put(6, new String[]{"أخبار إسرائيلية", "israel", "7"});
        cats.put(7, new String[]{"آراء و مقالات", "opinions", "8"});
        cats.put(8, new String[]{"منوعات", "stuff", "9"});
        cats.put(9, new String[]{"الصحة", "health", "10"});
        cats.put(10, new String[]{"أخبار رياضية", "sports", "11"});
        cats.put(11, new String[]{"أخبار اقتصادية", "economy", "12"});
        cats.put(12, new String[]{"علوم و تكنولوجيا", "tech", "13"});
        cats.put(13, new String[]{"شؤون المرأة", "women", "14"});
        cats.put(14, new String[]{"منح دراسية", "scholarships", "16"});
        cats.put(15, new String[]{"فرص عمل و وظائف", "jobs", "17"});
        cats.put(16, new String[]{"مشاريع و مناقصات", "projects", "18"});
        cats.put(17, new String[]{"دورات و مسابقات", "courses", "19"});


    }
}
