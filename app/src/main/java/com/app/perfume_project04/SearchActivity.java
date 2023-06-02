package com.app.perfume_project04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = editText.getText().toString();

                // 키워드를 vscode로 전송하는 AsyncTask 실행
                new SendKeywordTask().execute(keyword);
            }
        });

    }

    private static class SendKeywordTask extends AsyncTask<String, Void, String> {
        private TextView textView;

        public SendKeywordTask() {
            this.textView = textView;
        }


        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            // 응답을 처리 (예: TextView에 표시)
            textView.setText(response);
        }

    }
}