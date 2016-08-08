package com.palsawa.news.components;

import android.support.v4.widget.SwipeRefreshLayout;

import com.palsawa.news.MainSystem;

/**
 * Created by root on 6/26/16.
 */
public class NewsSwipeRefreshLayout implements NewsComponent {
    private SwipeRefreshLayout swipeRefreshLayout;

    public NewsSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public void build() {
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainSystem.LIST_PAGE = 1;
                MainSystem.WE_ARE_FETCHING_NEW_NEWS = true;
                MainSystem.fetchNewsOperation();
            }
        });
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return this.swipeRefreshLayout;
    }

}
