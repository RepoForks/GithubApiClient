package com.example.vitali.githubapiclient.data.network;

import com.example.vitali.githubapiclient.data.network.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GithubApi {

    String BASE_URL = "https://api.github.com";

    @GET("/users/{user}/repos")
    Call<List<Repository>> getUserRepositories(@Path("user") String userName);
}