package com.palsawa.news;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.palsawa.news.utilities.ArticleBuilder;
import com.palsawa.news.utilities.DateBuilder;
import com.palsawa.news.utilities.ToastContent;
import com.palsawa.news.utilities.URLBuilder;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private int id;
    private boolean isArticleFinishLoading = false;
    private String cat;
    private String title;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.id = Integer.parseInt(getIntent().getExtras().getString("ARTICLE_ID"));
        this.title = getIntent().getExtras().getString("ARTICLE_TITLE");

        ((TextView) findViewById(R.id.article_actionbar_title)).setText(title);

        // we going to send a request Here and Capture the result back when the process done !
        MainSystem.ARTICLE_DISPLAYED = this;
        MainSystem.fetchArticleOperation(id);

    }

    public void updateActivity() {
        Cursor c = MainSystem.fetchArticle(id);

        // cat
        this.cat = c.getString(2);

        // title
        WebView title = (WebView) findViewById(R.id.article_title);
        ArticleBuilder.buildWithStyle(title, c.getString(3), ArticleBuilder.HEADING_STYLE);
        this.title = c.getString(3);

        // date
        WebView date = (WebView) findViewById(R.id.article_date);
        String dateValue = "آخر تعديل: ";
        dateValue += DateBuilder.getDate(c.getString(7), c.getString(8), c.getString(9));
        dateValue += " الساعه: ";
        dateValue += DateBuilder.getTime(c.getString(10), c.getString(11));
        ArticleBuilder.buildWithStyle(date, dateValue, ArticleBuilder.CAPTION_STYLE);
        this.date = c.getString(7) + "-" + c.getString(8) + "-" + c.getString(9);

        // image
        ImageView imageView = (ImageView) findViewById(R.id.article_image);
        if (c.getString(4).equalsIgnoreCase("none")) {
            imageView.setVisibility(View.GONE);
        } else {
            Picasso.with(this)
                    .load(c.getString(4))
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.site_logo)
                    .into(imageView);
        }

        // content
        WebView content = (WebView) findViewById(R.id.article_content);
        ArticleBuilder.build(content, c.getString(5));

        // Remove Loading
        LinearLayout progressBar = (LinearLayout) findViewById(R.id.article_progress_bar);
        progressBar.setVisibility(View.GONE);

        // finish loading
        this.isArticleFinishLoading = true;


    }

    public void shareArticle(View v) {
        if (checkIfArticleIsLoaded()) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, this.title + URLBuilder.getArticleURL(this.cat, this.date, this.id));
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
    }

    public void back(View v) {
        finish();
    }

    private boolean checkIfArticleIsLoaded() {
        if (!isArticleFinishLoading) {
            Toast.makeText(this, ToastContent.WAIT_FOR_ARTICLE, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
