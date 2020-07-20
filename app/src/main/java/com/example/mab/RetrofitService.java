package com.example.mab;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    // dinh nghia cac request
    @GET("/wp-json/")
    Call<MyModel> getData();

    @POST("/posts")
    @FormUrlEncoded
    Call<PostModel> getPostData(@Field("title") String title,
                                @Field("userId") String userId,
                                @Field("body") String body);


}
