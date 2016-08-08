package com.palsawa.news.components;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by root on 6/26/16.
 */
public class NewsProgressBar implements NewsComponent
{
    private LinearLayout fullProgressBar;

    public NewsProgressBar(LinearLayout fullProgressBar)
    {
        this.fullProgressBar = fullProgressBar;
    }

    public void build()
    {

    }

    public void showFullProgressBar()
    {
        fullProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideFullProgressBar()
    {
        fullProgressBar.setVisibility(View.GONE);
    }
}
