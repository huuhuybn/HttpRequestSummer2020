
package com.example.mab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetActivity extends AppCompatActivity {

    TextView tvText;
    String link = "http://dotplays.com/wp-json/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        tvText = findViewById(R.id.tvData);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit restRetrofit = RestRetrofit.getInstance("http://dotplays.com");
                RetrofitService retrofitService = restRetrofit.create(RetrofitService.class);

                retrofitService.getData().
                        enqueue(new Callback<MyModel>() {
                    @Override
                    public void onResponse
                            (Call<MyModel> call,
                             Response<MyModel> response) {
                        MyModel myModel = response.body();
                        Toast.makeText(GetActivity.this,
                                myModel.home,
                                Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<MyModel> call, Throwable t) {

                    }
                });


            }
        });

    }
}