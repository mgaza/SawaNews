package com.palsawa.news.settings;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root on 7/9/16.
 */
public class WatanyiaSettings
{
    private boolean isWatanyiaConnectMode;
    public final static String WATNIYA_CONNECT_MODE = "com.palsawa.news.watanyiaconnectsettings";
    private Context context;

    public WatanyiaSettings(Context context)
    {
        this.context = context;
        setWataniyaConnectDefault();
    }

    private void setWataniyaConnectDefault()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.palsawa.news",Context.MODE_PRIVATE);
        this.isWatanyiaConnectMode = sharedPreferences.getBoolean(WATNIYA_CONNECT_MODE,true);
    }

    public void setWatanyiaConnectMode(boolean status)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.palsawa.news",Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(WATNIYA_CONNECT_MODE,status).apply();
        this.isWatanyiaConnectMode = status;
    }

    public boolean getWatanyiaConnectMode()
    {
        return this.isWatanyiaConnectMode;
    }
}
