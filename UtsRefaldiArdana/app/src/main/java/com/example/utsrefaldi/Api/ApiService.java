package com.example.utsrefaldi.Api;

import com.example.utsrefaldi.Models.GithubRes;
import com.example.utsrefaldi.Models.Items;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String TOKEN = "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";

    @GET("search/users")
    @Headers("Authorization: Bearer " + TOKEN)
    Call<GithubRes> getUsers(@Query("q") String username);

    @GET("users/{username}")
    Call<Items> getDetailUser(@Path("username") String username);


}
