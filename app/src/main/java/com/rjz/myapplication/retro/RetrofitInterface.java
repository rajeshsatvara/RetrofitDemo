package com.rjz.myapplication.retro;

import com.rjz.myapplication.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("posts")
    public Call<List<PostModel>> getAllPost();
}
