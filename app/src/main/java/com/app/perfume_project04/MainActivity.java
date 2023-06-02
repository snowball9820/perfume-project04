/*package com.app.perfume_project04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();

        // Button click or any event to initiate the request
        Button requestButton = findViewById(R.id.requestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });

    }
    private void makeRequest() {
        // Create the request
        Request request = new Request.Builder()
                .url("http://172.30.1.57:5000/")
                .build();

        // Execute the request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle network failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Process the successful response
                    final String responseData = response.body().string();

                    // Update UI on the main thread (if needed)
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Update UI with the response data
                            TextView responseTextView = findViewById(R.id.responseTextView);
                            responseTextView.setText(responseData);
                        }
                    });
                } else {
                    // Handle unsuccessful response
                    // You can check response code, error messages, etc.
                }
            }
        });
    }




    }
    */
