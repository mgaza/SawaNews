package com.palsawa.news.components;

import android.view.Menu;
import android.view.MenuItem;

import com.palsawa.news.MainSystem;
import com.palsawa.news.R;

/**
 * Created by root on 6/26/16.
 */
public class NewsMenu implements NewsComponent
{
    private Menu menu;
    private String cats[] = {"أهم الأخبار","أخبار محلية","أخبار المؤسسات","مقابلات خاصة",
            "تقارير","عربي و دولي","أخبار إسرائيلية","آراء و مقالات",
            "منوعات","الصحة","أخبار رياضية","أخبار اقتصادية","علوم و تكنولوجيا","شؤون المرأة",
            "منح دراسية","فرص عمل و وظائف","مشاريع و مناقصات","دورات و مسابقات"};
    private int icons[] = {R.drawable.main,R.drawable.local,R.drawable.instu,R.drawable.meetings,
        R.drawable.default_article,R.drawable.international,R.drawable.default_article,
        R.drawable.opinions,R.drawable.default_article,R.drawable.default_article,
        R.drawable.sport,R.drawable.default_article,R.drawable.default_article,
        R.drawable.default_article,R.drawable.scholarship,R.drawable.scholarship,
        R.drawable.scholarship,R.drawable.scholarship};

    public NewsMenu(Menu menu)
    {
        this.menu = menu;
    }

    public void build()
    {
        MenuItem items[] = new MenuItem[cats.length];
        for(int i=0,n=cats.length;i<n;i++)
        {
            items[i] = menu.add(Menu.NONE,i,Menu.NONE,cats[i]);
            items[i].setIcon(icons[i]);
        }
        MainSystem.ITEM_MENU_CHECKED = items[0];
        items[0].setChecked(true);
    }

    public String getItem(int pos)
    {
        return this.cats[pos];
    }

}
