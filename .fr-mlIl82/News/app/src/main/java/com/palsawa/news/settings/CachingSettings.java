package com.palsawa.news.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.palsawa.news.MainSystem;

/**
 * Created by root on 7/9/16.
 */
public class CachingSettings
{
    public static final int ONE_WEEK_MODE = 1;
    public static final int TWO_WEEKS_MODE = 2;
    public static final int ONE_MONTH_MODE = 3;
    public static final String CACHING_PREF = "cachingpref";

    private Context context;
    private int cachingMode;

    public CachingSettings(Context context)
    {
        this.context = context;
        setDefaultCachingMode();
    }

    private void setDefaultCachingMode()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.palsawa.news",Context.MODE_PRIVATE);
        this.cachingMode = sharedPreferences.getInt(CACHING_PREF,1);
        wipeCached();
    }

    public void setCachingMode(int value)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.palsawa.news",Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(CACHING_PREF,value).apply();
        this.cachingMode = value;
    }

    public int getCachingMode()
    {
        return this.cachingMode;
    }

    public void wipeCached()
    {
        if(cachingMode == ONE_WEEK_MODE)
        {
            int targetTime = ((int) System.currentTimeMillis()) - (1000*60*60*24*7);
            MainSystem.wipeNewsCache(targetTime);
        }
        else if(cachingMode == TWO_WEEKS_MODE)
        {
            int targetTime = ((int) System.currentTimeMillis()) - (1000*60*60*24*14);
            MainSystem.wipeNewsCache(targetTime);
        }
        else if(cachingMode == ONE_MONTH_MODE)
        {
            int targetTime = ((int) System.currentTimeMillis()) - (1000*60*60*24*30);
            MainSystem.wipeNewsCache(targetTime);
        }
    }
}
