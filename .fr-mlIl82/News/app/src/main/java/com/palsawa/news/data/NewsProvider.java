package com.palsawa.news.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bebound.common.model.ValueMap;
import com.palsawa.news.MainSystem;
import com.palsawa.news.sync.PalsawaOperations;
import com.palsawa.news.sync.PalsawaOperationsContract;

import java.util.Calendar;

/**
 * Created by root on 5/15/16.
 */
public class NewsProvider
{
    private NewsDBHelper newsDBHelper;
    private Cursor currentCursor = null;
    private String LOG_NEWS_PROVIDER = "Log News Provider => ";

    public NewsProvider(Context context)
    {
        newsDBHelper = new NewsDBHelper(context);
    }

    private void insert(String table, String nullColumnHack, ContentValues values)
    {
        Log.d(LOG_NEWS_PROVIDER,"There is Data inserted in "+table);
        newsDBHelper.getWritableDatabase().insertWithOnConflict(table,nullColumnHack,values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    private void update(String table,ContentValues values,String whereClause,String[] whereArgs)
    {
        Log.d(LOG_NEWS_PROVIDER,"There is a Row updated in "+table);
        newsDBHelper.getWritableDatabase().update(table,values,whereClause,whereArgs);
    }

    private void delete(String table,String whereClause,String[] whereArgs)
    {
        Log.d(LOG_NEWS_PROVIDER,"There is a deletion of data "+table);
        int result = newsDBHelper.getWritableDatabase().delete(table,whereClause,whereArgs);
        Log.d(LOG_NEWS_PROVIDER,"We have deleted => "+result);
    }

    public Cursor query(String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy,String limit)
    {
        currentCursor = newsDBHelper.getReadableDatabase().query(table,columns,selection,selectionArgs,groupBy,having,orderBy,limit);
        Log.d(LOG_NEWS_PROVIDER,"We have returned Cursor with "+currentCursor.getCount()+" Rows!");
        return currentCursor;
    }

    public void insert_news(ValueMap values[],int cat)
    {
        // insert news to db
        ContentValues data;
        for(ValueMap value : values)
        {
            data = new ContentValues(9);
            data.put(NewsContract.Article.COLUMN_CAT,cat);
            data.put(NewsContract.Article.COLUMN_ARTICLE_ID,value.getInt(PalsawaOperationsContract.FETCH_NEWS_ID_PARAM));
            if(MainSystem.FETCH_ARTICLE_FOR_FUTURE_USE)
            {
                PalsawaOperations.fetch_article_for_future_use(value.getInt(PalsawaOperationsContract.FETCH_NEWS_ID_PARAM));
            }
            data.put(NewsContract.Article.COLUMN_TITLE,value.getString(PalsawaOperationsContract.FETCH_NEWS_TITLE_PARAM));
            data.put(NewsContract.Article.COLUMN_MINUTE,value.getString(PalsawaOperationsContract.FETCH_NEWS_DATE_PARAM).split(":")[0]);
            data.put(NewsContract.Article.COLUMN_HOUR,value.getString(PalsawaOperationsContract.FETCH_NEWS_DATE_PARAM).split(":")[1]);
            data.put(NewsContract.Article.COLUMN_DAY,value.getString(PalsawaOperationsContract.FETCH_NEWS_DATE_PARAM).split(":")[2]);
            data.put(NewsContract.Article.COLUMN_MONTH,value.getString(PalsawaOperationsContract.FETCH_NEWS_DATE_PARAM).split(":")[3]);
            data.put(NewsContract.Article.COLUMN_YEAR,value.getString(PalsawaOperationsContract.FETCH_NEWS_DATE_PARAM).split(":")[4]);
            data.put(NewsContract.Article.COLUMN_IMAGE_URL,value.getString(PalsawaOperationsContract.FETCH_NEWS_IMG_PARAM));
            data.put(NewsContract.Article.COLUMN_CONTENT,"لم يتم تحميل نص الخبر !");
            data.put(NewsContract.Article.COLUMN_CONTENT_PART,0);
            int unixTime = (int)System.currentTimeMillis();
            data.put(NewsContract.Article.COLUMN_TIME,unixTime);
            this.insert(NewsContract.TABLE_ARTICLE,null,data);
        }
    }

    public void insert_article(int id,String article,int part)
    {
        String whereClause = NewsContract.Article.COLUMN_ARTICLE_ID + "=?";
        String whereArgs[] = {Integer.toString(id)};
        ContentValues data = new ContentValues(2);
        data.put(NewsContract.Article.COLUMN_CONTENT,article);
        data.put(NewsContract.Article.COLUMN_CONTENT_PART,part);
        this.update(NewsContract.TABLE_ARTICLE,data,whereClause,whereArgs);
    }

    public Cursor fetchNewsForAdapter(int cat,int limit,int page) {
        String whereClause = NewsContract.Article.COLUMN_CAT + "=?";
        String whereValues[] = {Integer.toString(cat)};
        String limitClause = Integer.toString(limit*page);
        Cursor c = this.query(NewsContract.TABLE_ARTICLE,null,whereClause,whereValues,null,null,NewsContract.Article.COLUMN_ARTICLE_ID+" DESC",limitClause);
        return c;

    }

    public void wipeCache(int beforeTimeStamp)
    {
        String whereClause = NewsContract.Article.COLUMN_TIME + " <?";
        String whereValues[] = {Integer.toString(beforeTimeStamp)};
        this.delete(NewsContract.TABLE_ARTICLE,whereClause,whereValues);
    }

    public Cursor fetchArticleForActivity(int id)
    {
        String whereClause = NewsContract.Article.COLUMN_ARTICLE_ID+"="+id;
        return this.query(NewsContract.TABLE_ARTICLE,null,whereClause,null,null,null,null,"1");
    }


}
