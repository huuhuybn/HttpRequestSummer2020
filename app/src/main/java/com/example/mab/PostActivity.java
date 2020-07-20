package com.example.mab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
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

//                Retrofit retrofit =
//                        RestRetrofit.getInstance("https://jsonplaceholder.typicode.com");
//
//                RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//
//                retrofitService.getPostData(title, id, body).enqueue(new Callback<PostModel>() {
//                    @Override
//                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
//                        PostModel postModel = response.body();
//                        textView.setText(postModel.userId + ":" + postModel.title + ":"
//                        + postModel.body);
//                    }
//
//                    @Override
//                    public void onFailure(Call<PostModel> call, Throwable t) {
//
//                    }
//                });

//                AndroidNetworking.post("https://jsonplaceholder.typicode.com/posts")
//                        .addBodyParameter("title",title).addBodyParameter("body",body)
//                        .addBodyParameter("userId",id).build()
//                        .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        PostModel postModel =
//                                new Gson().fromJson(response.toString(),PostModel.class);
//                        textView.setText(postModel.body);
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//
//                    }
//                });


                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(PostActivity.this);
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, link,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                PostModel myModel = new Gson().fromJson(response, PostModel.class);
                                textView.setText(myModel.body);

                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("title", title);
                        params.put("body", body);
                        params.put("userId", id);
                        return params;
                    }
                };
                queue.add(stringRequest);


            }
        });

    }


}