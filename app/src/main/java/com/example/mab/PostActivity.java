package com.example.mab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostActivity extends AppCompatActivity {


    EditText edtBody, edtTitle, edtId;
    String link = "https://jsonplaceholder.typicode.com/posts";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        edtBody = findViewById(R.id.edtBody);
        edtTitle = findViewById(R.id.edtTitle);
        edtId = findViewById(R.id.edtId);
        textView = findViewById(R.id.textView);

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                String body = edtBody.getText().toString().trim();
                String title = edtTitle.getText().toString().trim();

                Retrofit retrofit =
                        RestRetrofit.getInstance("https://jsonplaceholder.typicode.com");

                RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                retrofitService.getPostData(title, id, body).enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        PostModel postModel = response.body();
                        textView.setText(postModel.userId + ":" + postModel.title + ":"
                        + postModel.body);
                    }

                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {

                    }
                });


            }
        });

    }


}