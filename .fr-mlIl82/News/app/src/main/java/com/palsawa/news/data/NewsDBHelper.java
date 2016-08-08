package com.palsawa.news.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 5/14/16.
 */
public class NewsDBHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "news.db";
    private static final int DATABASE_VERSION = 1;

    public NewsDBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE "+NewsContract.TABLE_ARTICLE;
        query += "(";
        query += NewsContract.Article.COLUMN_ID+" INTEGER PRIMARY KEY,";
        query += NewsContract.Article.COLUMN_ARTICLE_ID+" INTEGER UNIQUE,";
        query += NewsContract.Article.COLUMN_CAT+" INTEGER,";
        query += NewsContract.Article.COLUMN_TITLE+" VARCHAR(150) NOT NULL,";
        query += NewsContract.Article.COLUMN_IMAGE_URL+" VARCHAR(80) NOT NULL,";
        query += NewsContract.Article.COLUMN_CONTENT+" TEXT NOT NULL,";
        query += NewsContract.Article.COLUMN_CONTENT_PART+" INTEGER,";
        query += NewsContract.Article.COLUMN_YEAR+" VARCHAR(4) NOT NULL,";
        query += NewsContract.Article.COLUMN_MONTH+" VARCHAR(2) NOT NULL,";
        query += NewsContract.Article.COLUMN_DAY+" VARCHAR(2) NOT NULL,";
        query += NewsContract.Article.COLUMN_HOUR+" VARCHAR(2) NOT NULL,";
        query += NewsContract.Article.COLUMN_MINUTE+" VARCHAR(2) NOT NULL,";
        query += NewsContract.Article.COLUMN_TIME+" INTEGER NOT NULL";
        query += ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE "+NewsContract.TABLE_ARTICLE);
        onCreate(db);
    }
}
