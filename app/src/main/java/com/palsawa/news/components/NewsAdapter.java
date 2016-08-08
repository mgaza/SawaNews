package com.palsawa.news.components;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.palsawa.news.R;
import com.palsawa.news.utilities.DateBuilder;
import com.squareup.picasso.Picasso;


/**
 * News Adapter will get a Cursor From Database and push its data into the list
 */

public class NewsAdapter extends CursorAdapter {
    private Cursor cursor;

    public NewsAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
        this.cursor = cursor;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        if (cursor.getPosition() == 0) {
            return LayoutInflater.from(context).inflate(R.layout.news_list_main_item, parent, false);
        }
        return LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = (TextView) view.findViewById(R.id.item_title);
        title.setText(cursor.getString(3));

        TextView date = (TextView) view.findViewById(R.id.item_date);
        TextView time = (TextView) view.findViewById(R.id.item_time);

        date.setText(DateBuilder.getDate(cursor.getString(7), cursor.getString(8), cursor.getString(9)));
        time.setText(DateBuilder.getTime(cursor.getString(10), cursor.getString(11)));

        ImageView image = (ImageView) view.findViewById(R.id.item_image);
        Picasso.with(context)
                .load(cursor.getString(4))
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.site_logo)
                .into(image);
        TextView id = (TextView) view.findViewById(R.id.news_id);
        id.setText(cursor.getString(1));
        int currentTimeMillis = (int) System.currentTimeMillis();
        Log.d("TRACKTIME", "The Passed is => " + ((currentTimeMillis - Integer.parseInt(cursor.getString(12))) / 1000));
    }
}
