package com.palsawa.news.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bebound.common.exception.BeBoundException;
import com.bebound.sdk.BeBound;
import com.bebound.sdk.request.RequestBuilder;
import com.palsawa.news.components.NewsAdapter;

/**
 * Created by root on 6/6/16.
 */
public class Request
{

    private RequestBuilder requestBuilder;
    private Context context;

    private static final String REQUEST_ERROR = "We can't send the request! BeBound Error!";

    public Request(Context context,String operationName)
    {
        this.context = context;
        requestBuilder = BeBound.newRequest().withOperationName(operationName);
    }

    public RequestBuilder getRequestBuilder()
    {
        return this.requestBuilder;
    }

    public void send()
    {
        class SendRequest extends AsyncTask<Request,Void,NewsAdapter>
        {
            @Override
            protected NewsAdapter doInBackground(Request... params)
            {
                try
                {
                    params[0].getRequestBuilder().send();
                }
                catch (BeBoundException err)
                {
                    Log.d("ERROR_IN_TASK",err.getMessage());
                }
                return null;
            }
        }
        SendRequest sendRequest = new SendRequest();
        sendRequest.execute(this);


    }
}
