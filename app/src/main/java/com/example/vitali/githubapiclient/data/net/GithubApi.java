package com.example.vitali.githubapiclient.data.net;

import com.example.vitali.githubapiclient.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GithubApi {

    String ROOT = "https://api.github.com";

    @GET("/orgs/octokit/repos")
    Call<List<User>> getUser();
}