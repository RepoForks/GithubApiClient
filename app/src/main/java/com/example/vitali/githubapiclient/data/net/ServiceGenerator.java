package com.example.vitali.githubapiclient.data.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ServiceGenerator {

    private final OkHttpClient.Builder httpClient;
    private final GithubApi githubService;

    private ServiceGenerator() {
        httpClient = new OkHttpClient.Builder();
        githubService = createService(GithubApi.class, GithubApi.ROOT);
    }

    public static ServiceGenerator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    public GithubApi getGithubService() {
        return githubService;
    }

    private <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(getGson()));
        return builder.build().create(serviceClass);
    }

    private static final class SingletonHolder {
        private static final ServiceGenerator INSTANCE = new ServiceGenerator();
        private SingletonHolder() {
        }
    }
}