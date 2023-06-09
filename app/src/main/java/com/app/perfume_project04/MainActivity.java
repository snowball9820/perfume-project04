package com.app.perfume_project04;

import static com.google.android.gms.common.internal.ImagesContract.URL;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;



public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText editText;
    private Button searchButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editText);
        searchButton = findViewById(R.id.searchButton);
        textView = findViewById(R.id.textView);

        // 서버 응답을 파싱하여 ParsedData 객체 생성
        ParsedData parsedData = parseServerResponse();

        // 이미지 URL 가져오기
        String imageUrl = parsedData.getUrl();

        // Glide를 사용하여 이미지 로드 및 표시
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL); // 디스크 캐시 사용
        Glide.with(this)
                .load(imageUrl)
                .apply(requestOptions)
                .into(imageView);

        // 검색 버튼 클릭 이벤트 리스너 설정
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = editText.getText().toString();

                OkHttpClient client = new OkHttpClient();

                // JSON 형식으로 데이터 생성
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("keyword", keyword);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
                String jsonBody = jsonObject.toString();

                // 요청 생성
                MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(jsonBody, mediaType);
                Request request = new Request.Builder()
                        .url("http://172.30.1.3:5000/")
                        .post(requestBody)
                        .build();

                // 비동기적으로 요청 보내기
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        // 요청 실패 처리
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String serverResponse = response.body().string();

                            // 서버에서 받은 결과 처리 (문자열로 전달된 단어)
                            final String receivedWord = serverResponse;

                            // TODO: 결과 값을 앱에서 처리 (예: TextView에 표시)
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(receivedWord);
                                }
                            });
                        } else {
                            // 응답 실패 처리
                        }
                    }
                });
            }
        });
    }

    private ParsedData parseServerResponse() {
        // 서버 응답 파싱 로직 구현
        // ...

        // 예시로 ParsedData 객체 생성하여 반환
        ParsedData parsedData = new ParsedData();
        parsedData.setUrl("https://static.luckyscent.com/images/products/37401.jpg?width=400&404=product.png\n");
        // ...

        return parsedData;
    }

}