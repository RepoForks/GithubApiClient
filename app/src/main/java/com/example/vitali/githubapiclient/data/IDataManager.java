package com.example.vitali.githubapiclient.data;


public interface IDataManager {

    interface DataManagerCallback {
        void onResult(Object result, int type);
        void onFailure(Throwable throwable);
    }

    void getRepositories();

    void getRepositories(Boolean isPrivate);
}