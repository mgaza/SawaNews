package com.palsawa.news;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bebound.sdk.BeBound;
import com.palsawa.news.utilities.CatContent;


public class NewsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // building the Activity Layout
        /*
            Direction of the display from Right to Left
         */
        this.buildActivityLayout();

        // starts the main system

        if (!BeBound.areBeBoundServicesFound()) {
            Toast.makeText(this, "No STORE", Toast.LENGTH_LONG).show();
            return;
        }

        MainSystem.run(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
        }

        if (id == R.id.action_about) {
            Intent settings = new Intent(this, AboutActivity.class);
            startActivity(settings);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id >= 0 && id <= 17) {
            MainSystem.getNewsProgressBar().showFullProgressBar();
            MainSystem.LIST_CHANGED = true;
            MainSystem.getNewsActivity().setToolbarTitle(CatContent.cats.get(id)[0]);
            MainSystem.LIST_CAT = Integer.parseInt(CatContent.cats.get(id)[2]);
            MainSystem.LIST_PAGE = 1;
            MainSystem.refreshNewsAdapterWithoutRequests();
            MainSystem.WE_ARE_FETCHING_NEW_NEWS = true;
            MainSystem.fetchNewsOperation();
            MainSystem.ITEM_MENU_CHECKED.setChecked(false);
            item.setChecked(true);
            MainSystem.ITEM_MENU_CHECKED = item;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void buildActivityLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        setContentView(R.layout.activity_news);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("آخر الأخبار");
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.closeDrawer(Gravity.RIGHT);
                } else {
                    drawer.openDrawer(Gravity.RIGHT);
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        MainSystem.setMenu(navigationView.getMenu());
    }

    public void setToolbarTitle(String title) {
        this.toolbar.setTitle(title);
    }
}
