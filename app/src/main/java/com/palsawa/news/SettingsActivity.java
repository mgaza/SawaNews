package com.palsawa.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.palsawa.news.settings.CachingSettings;
import com.rey.material.widget.RadioButton;
import com.rey.material.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    private final String SETTINGS_LOG = "WATANYIA_SETTINGS";
    private com.rey.material.widget.Switch watanyiaSettings;
    private RadioButton cachBtns[] = new RadioButton[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.watanyiaSettings = (com.rey.material.widget.Switch) findViewById(R.id.wataniyaSettings);
        this.cachBtns[0] = (RadioButton) findViewById(R.id.weekCachBtn);
        this.cachBtns[1] = (RadioButton) findViewById(R.id.twoWeekCachBtn);
        this.cachBtns[2] = (RadioButton) findViewById(R.id.MonthCachBtn);
        this.cachBtns[0].setOnClickListener(new RadioButtonsListener(CachingSettings.ONE_WEEK_MODE));
        this.cachBtns[1].setOnClickListener(new RadioButtonsListener(CachingSettings.TWO_WEEKS_MODE));
        this.cachBtns[2].setOnClickListener(new RadioButtonsListener(CachingSettings.ONE_MONTH_MODE));
        setDefaultMode();


        this.watanyiaSettings.setChecked(MainSystem.watanyiaSettings.getWatanyiaConnectMode());
        setSwitchChangeListener(watanyiaSettings);
    }

    public void finishActivity(View v) {
        finish();
    }

    private void setSwitchChangeListener(com.rey.material.widget.Switch watanyiaSwitch) {
        watanyiaSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(Switch view, boolean checked) {
                if (checked) {
                    MainSystem.watanyiaSettings.setWatanyiaConnectMode(true);
                } else {
                    MainSystem.watanyiaSettings.setWatanyiaConnectMode(false);
                }
            }
        });
    }

    private void setDefaultMode() {

        for (int i = 0; i < 3; i++) {
            cachBtns[i].setChecked(false);
        }
        cachBtns[(MainSystem.cachingSettings.getCachingMode()) - 1].setChecked(true);
    }

    private class RadioButtonsListener implements RadioButton.OnClickListener {
        private int num;

        public RadioButtonsListener(int num) {
            this.num = num;
        }

        @Override
        public void onClick(View v) {
            for (int i = 0; i < 3; i++) {
                cachBtns[i].setChecked(false);
            }
            Log.d("CACHING", "The num is " + num);
            MainSystem.cachingSettings.setCachingMode(num);
            cachBtns[num - 1].setChecked(true);
        }
    }
}
