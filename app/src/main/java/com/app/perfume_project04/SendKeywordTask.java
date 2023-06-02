package com.app.perfume_project04;
import android.os.AsyncTask;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.text.BreakIterator;

public class SendKeywordTask extends AsyncTask<String, Void, String> {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    protected String doInBackground(String... params) {
        String keyword = params[0];
        String responseString = null;
        try {
            OkHttpClient client = new OkHttpClient();

            // JSON 형식으로 데이터 전송
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("keyword", keyword);
            String jsonBody = jsonObject.toString();

            RequestBody body = RequestBody.create(jsonBody, JSON);
            Request request = new Request.Builder()
                    .url("http://172.30.1.57:5000/")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            responseString = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }


}