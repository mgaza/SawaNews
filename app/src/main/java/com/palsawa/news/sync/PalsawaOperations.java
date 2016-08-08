package com.palsawa.news.sync;

import android.content.Context;
import android.util.Log;

import com.bebound.common.listener.request.OnFailedListener;
import com.bebound.common.listener.request.OnSuccessListener;
import com.bebound.common.model.ValueMap;
import com.bebound.common.model.request.Response;
import com.palsawa.news.MainSystem;
import com.palsawa.news.R;
import com.palsawa.news.data.NewsProvider;

public class PalsawaOperations {
    private static String STATUS_LOG_TITLE = "Request Status => ";
    private static String ERROR_LOG_TITLE = "Error Occurred!";
    private static String SUCCESS_LOG_TITLE = "Succeeded ";


    public static void fetch_news_by_web(Context context, NewsProvider newsProvider) {
        fetch_news(context, newsProvider, PalsawaOperationsContract.OPERATION_FETCH_NEWS_BY_WEB);
    }

    public static void fetch_news_by_sms(Context context, NewsProvider newsProvider) {
        fetch_news(context, newsProvider, PalsawaOperationsContract.OPERATION_FETCH_NEWS_BY_SMS);
    }

    private static void fetch_news(Context context, final NewsProvider newsProvider, String operation) {
        Log.d(STATUS_LOG_TITLE, "We will Establish Request Now!");
        // Let's make a new requesst
        Request request = new Request(context, operation);
        request.getRequestBuilder().put(PalsawaOperationsContract.OPERATION_CAT_PARAM, MainSystem.LIST_CAT);
        request.getRequestBuilder().put(PalsawaOperationsContract.OPERATION_PAGE_PARAM, MainSystem.LIST_PAGE);
        Log.d(STATUS_LOG_TITLE, "Cat is => " + MainSystem.LIST_CAT + " & Page is => " + MainSystem.LIST_PAGE);
        request.getRequestBuilder().onSuccess(new OnSuccessListener() {
            @Override

            public boolean onSuccess(Context context, Response response, com.bebound.common.model.request.Request request) {
                Log.d(SUCCESS_LOG_TITLE, "Request succeeded! We have received response!");
                ValueMap values[] = response.getResponseParameters().getArray(PalsawaOperationsContract.FETCH_NEWS_ARRAY_PARAM);
                Log.d(SUCCESS_LOG_TITLE, "We Got => " + values.length);
                newsProvider.insert_news(values, MainSystem.LIST_CAT);
                MainSystem.refreshNewsAdapter();
                MainSystem.trackNewNewsComing();
                return true;
            }
        });

        request.getRequestBuilder().onFailed(new OnFailedListener() {
            @Override
            public boolean onRequestFailed(Context context, com.bebound.common.model.request.Request request, int requestStatusCode, String requestStatusMessage) {
                Log.e(ERROR_LOG_TITLE, "Oh shit, Request failed! The issue was " + requestStatusMessage);
                MainSystem.displayToastMessage(R.string.request_failed);
                MainSystem.refreshNewsAdapter();
                return true;
            }
        });
        Log.d(STATUS_LOG_TITLE, "We send the request!");
        request.send();
    }

    public static void fetch_article_by_web(Context context, NewsProvider newsProvider, int id) {
        fetch_article(context, newsProvider, PalsawaOperationsContract.OPERATION_FETCH_ARTICLE_BY_WEB, id, -1);
    }

    public static void fetch_article_by_sms(Context context, NewsProvider newsProvider, int id, int part) {
        fetch_article(context, newsProvider, PalsawaOperationsContract.OPERATION_FETCH_ARTICLE_BY_SMS, id, part);
    }

    private static void fetch_article(Context context, final NewsProvider newsProvider,
                                      String operation, final int id, final int part) {
        Log.d(STATUS_LOG_TITLE, "We will fetch Article Right Now");
        // Let's make a new request
        Request request = new Request(context, operation);
        if (operation.equals(PalsawaOperationsContract.OPERATION_FETCH_ARTICLE_BY_SMS)) {
            request.getRequestBuilder().put(PalsawaOperationsContract.OPERATION_PART_PARAM, part);
        }
        request.getRequestBuilder().put(PalsawaOperationsContract.OPERATION_ID_PARAM, id);
        Log.d(STATUS_LOG_TITLE, "Operation => " + operation + " | ID => " + id + " | Part => " + part);
        request.getRequestBuilder().onSuccess(new OnSuccessListener() {
            @Override

            public boolean onSuccess(Context context, Response response, com.bebound.common.model.request.Request request) {
                Log.d(SUCCESS_LOG_TITLE, "Great! Article has been received! ");
                String article = response.getResponseParameters().getString(PalsawaOperationsContract.FETCH_ARTICLE_PARAM);
                Log.d(SUCCESS_LOG_TITLE, "Our Article is => " + article);
                newsProvider.insert_article(id, article, part);
                MainSystem.refreshArticle();
                return true;
            }
        });

        request.getRequestBuilder().onFailed(new OnFailedListener() {
            @Override
            public boolean onRequestFailed(Context context, com.bebound.common.model.request.Request request, int requestStatusCode, String requestStatusMessage) {
                Log.e(ERROR_LOG_TITLE, "Oh shit, We Failed to fetch Article! The issue was " + requestStatusMessage);
                MainSystem.displayToastMessage(R.string.request_failed);
                MainSystem.refreshArticle();
                return true;
            }
        });
        Log.d(STATUS_LOG_TITLE, "We send the request to fetch article!");
        request.send();
    }

    public static void fetch_article_for_future_use(final int id) {
        final String operation = PalsawaOperationsContract.OPERATION_FETCH_ARTICLE_BY_WEB;
        final Context context = MainSystem.getNewsActivity();
        final NewsProvider newsProvider = MainSystem.newsProvider;
        Log.d(STATUS_LOG_TITLE, "We will fetch Article Right Now");
        // Let's make a new request
        Request request = new Request(context, operation);
        request.getRequestBuilder().put(PalsawaOperationsContract.OPERATION_ID_PARAM, id);
        Log.d(STATUS_LOG_TITLE, "Operation => " + operation + " | ID => " + id);
        request.getRequestBuilder().onSuccess(new OnSuccessListener() {
            @Override

            public boolean onSuccess(Context context, Response response, com.bebound.common.model.request.Request request) {
                Log.d(SUCCESS_LOG_TITLE, "Great! Article has been received! ");
                String article = response.getResponseParameters().getString(PalsawaOperationsContract.FETCH_ARTICLE_PARAM);
                Log.d(SUCCESS_LOG_TITLE, "Our Article is => " + article);
                newsProvider.insert_article(id, article, -1);
                return true;
            }
        });
        Log.d(STATUS_LOG_TITLE, "We send the request to fetch article!");
        request.send();
    }
}
