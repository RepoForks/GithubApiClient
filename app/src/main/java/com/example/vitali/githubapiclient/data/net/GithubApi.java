package com.example.vitali.githubapiclient.data.net;

import com.example.vitali.githubapiclient.data.model.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GithubApi {

    String ROOT = "https://api.github.com";

    @GET("/users/{user}/repos")
    Call<List<Repos>> getUserRepos(@Path("user") String userName);
}