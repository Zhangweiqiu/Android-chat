package com.example.nit1107.nit1107.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by qiuzhangwi on 2018/5/17.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
