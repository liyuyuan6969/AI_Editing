package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;import android.content.Intent;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.ui.home.HomeFragment;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

public class TextInsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_insert);
        Button ret_bn = findViewById(R.id.return_home);
        ret_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openFragment();
                Intent intent = new Intent();
                intent.setClass(TextInsertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button text2video_bn = findViewById(R.id.text_to_video);

        OkHttpClient okHttpClient_post_kv = new OkHttpClient();
        // 执行okhttp
        String url = "http://10.0.2.2:8888/text";
        text2video_bn.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 EditText editText = findViewById(R.id.edit_text); // 获取 EditText 对象
                                                 String text = editText.getText().toString();
                                                 Log.d("editText", text);

                                                 OkHttpClient client = new OkHttpClient();
                                                 String paramName = "text";
                                                 String paramValue = text;
                                                 String urlWithParams = url + "?" + paramName + "=" + paramValue;
                                                 Request request = new Request.Builder()
                                                         .url(urlWithParams)
                                                         .build();
                                                 client.newCall(request).enqueue(new Callback() {
                                                     @Override
                                                     public void onFailure(Call call, IOException e) {
                                                         e.printStackTrace();
                                                     }

                                                     @Override
                                                     public void onResponse(Call call, Response response) throws IOException {
                                                         if (response.isSuccessful()) {
                                                             String responseBody = response.body().string();
                                                             System.out.println("Response: " + responseBody);
                                                         } else {
                                                             System.out.println("HTTP GET request failed with response code: " + response.code());
                                                         }
                                                         response.close();
                                                     }
                                                 });
                                             }
                                            });
    }
}


//            private void openFragment() {
//                HomeFragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.text_insert, fragment);
//                fragmentTransaction.addToBackStack(null); // 添加到返回堆栈，使返回键可用
//                fragmentTransaction.commit();
//            }
