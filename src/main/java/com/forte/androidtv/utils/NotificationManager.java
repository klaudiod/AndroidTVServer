package com.forte.androidtv.utils;


import com.forte.androidtv.utils.Data;
import com.forte.androidtv.utils.PostMessage;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

public class NotificationManager {
    private static final String SERVICE_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String API_KEY = "AIzaSyDWqvs2PJdepmDgqS-QXne9qaMjgdp6jBo";
    static final Logger logger = Logger.getLogger(NotificationManager.class);

    public static void post(String screenId) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost("https://fcm.googleapis.com/fcm/send");
        httpost.setHeader("Content-Type", "application/json");
        httpost.setHeader("Authorization", "key=AIzaSyDWqvs2PJdepmDgqS-QXne9qaMjgdp6jBo");
        try {
            StringEntity stringEntity = NotificationManager.constructMessage(screenId);
            if (stringEntity != null) {
                httpost.setEntity((HttpEntity) stringEntity);
                HttpResponse response = httpclient.execute((HttpUriRequest) httpost);
                logger.debug((Object) ("Response status from GCM: " + (Object) response.getStatusLine()));
            }
        } catch (IOException e) {
            logger.error((Object) "IOException on sending post message to GCM", (Throwable) e);
        }
    }

    private static StringEntity constructMessage(String screenId) throws UnsupportedEncodingException {
        StringEntity stringEntity = null;
        PostMessage postMessage = new PostMessage();
        Data data = new Data();
        data.setScreenId(screenId);
        postMessage.setData(data);
        postMessage.setRegistration_ids(new String[]{screenId});
        stringEntity = new StringEntity(new Gson().toJson((Object) postMessage));
        return stringEntity;
    }
}