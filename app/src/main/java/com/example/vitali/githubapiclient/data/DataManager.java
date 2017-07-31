package com.example.vitali.githubapiclient.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.vitali.githubapiclient.data.database.DbManager;
import com.example.vitali.githubapiclient.data.database.IRepositoriesDbManager;
import com.example.vitali.githubapiclient.data.network.GithubApi;
import com.example.vitali.githubapiclient.data.network.ServiceGenerator;
import com.example.vitali.githubapiclient.data.network.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataManager implements IDataManager {

    public static final int TYPE_REPOSITORIES = 101;

    private IRepositoriesDbManager repositoryManager;
    private DataManagerCallback callback;

    public DataManager(Context context, DataManagerCallback callback) {
        repositoryManager = new DbManager(context);
        this.callback = callback;
    }

    public void getRepositories() {
        callback.onResult(getDataFromDatabase(), TYPE_REPOSITORIES);
        getDataFromApi();
    }

    @Override
    public void getRepositories(Boolean isPrivate) {

    }

    private List<Repository> getDataFromDatabase() {
        repositoryManager.openConnection();
        List<Repository> repositories = repositoryManager.getAll();
        repositoryManager.closeConnection();
        return repositories;
    }

    private void getDataFromApi() {
        GithubApi api = ServiceGenerator.getInstance().getGithubService();
        Call<List<Repository>> call = api.getUserRepositories("VitaliBov");
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repository>> call, @NonNull Response<List<Repository>> response) {
                if (response.isSuccessful()) {
                    List<Repository> repositoryList = response.body();

                    callback.onResult(repositoryList, TYPE_REPOSITORIES);

                    repositoryManager.openConnection();
                    repositoryManager.saveAll(repositoryList);
                    repositoryManager.closeConnection();
                } else {
                    Log.e("Error", "Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Repository>> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}