package com.palsawa.news.components;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.palsawa.news.DetailActivity;
import com.palsawa.news.MainSystem;
import com.palsawa.news.R;

public class NewsList implements NewsComponent {
    private final String LOG_LIST = "NewsList";
    private ListView list;

    public NewsList(ListView list) {
        this.list = list;
    }

    public void build() {
        LinearLayout view = (LinearLayout) ((Activity) list.getContext()).getLayoutInflater().inflate(R.layout.list_footer_view, null);
        list.addFooterView(view);
        LinearLayout emptyView = (LinearLayout) ((Activity) list.getContext()).getLayoutInflater().inflate(R.layout.empty_list_layout, null);
        list.setEmptyView(emptyView);
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((firstVisibleItem + visibleItemCount) == (totalItemCount - 1)) {
                    int page = ((int) Math.ceil(((double) totalItemCount) / MainSystem.LIST_LIMIT)) + 1;
                    Log.d("STATUS_SCROLL", Integer.toString(totalItemCount) + " || " + page);
                    if (MainSystem.LIST_PAGE < page) {
                        MainSystem.LIST_PAGE = page;
                        if (MainSystem.WE_ARE_FETCHING_NEW_NEWS)
                            MainSystem.refreshNewsAdapterWithoutRequests();
                        else
                            MainSystem.fetchNewsOperation();
                    }
                }

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animation listItemAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_clicked);
                view.startAnimation(listItemAnimation);

                String article_id = ((TextView) view.findViewById(R.id.news_id)).getText().toString();
                String article_title = ((TextView) view.findViewById(R.id.item_title)).getText().toString();
                Log.d(LOG_LIST, "We Got an ID after click which is => " + article_id);
                Intent details = new Intent(view.getContext(), DetailActivity.class);
                details.putExtra("ARTICLE_ID", article_id);
                details.putExtra("ARTICLE_TITLE", article_title);
                view.getContext().startActivity(details);
                ((Activity) view.getContext()).overridePendingTransition(R.anim.activity_push_up_in, R.anim.push_up_out);
            }
        });
    }

    public ListView getList() {
        return this.list;
    }
}
