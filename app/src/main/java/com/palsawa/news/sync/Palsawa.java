package com.palsawa.news.sync;

import android.app.Application;

import com.bebound.common.model.BeAppConfig;
import com.bebound.sdk.BeBound;
import com.palsawa.news.R;

public class Palsawa extends Application {
    private static final String AUTH_FAILED = "Sorry, We Have Problem with Authentication!";
    private static final String REQUEST_FAILED = "Sorry, request faild to be sent!";

    @Override
    public void onCreate() {
        super.onCreate();
        BeBound.init(this, new BeAppConfig() {
            @Override
            public String getBeAppKey() {
                return "HyOPLbaysFdBnHUqhz6Z2xJclQTyoxlBldnalIoLTGMiT6PmVmTnylc=";
            }

            @Override
            public long getBeAppId() {
                return 109; // app id
            }

            @Override
            public int getBeAppManifestResId() {
                return R.xml.palsawa;
            }
        });
    }

}
