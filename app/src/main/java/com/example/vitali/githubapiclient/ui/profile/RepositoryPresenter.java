package com.example.vitali.githubapiclient.ui.profile;

import android.content.Context;

import com.example.vitali.githubapiclient.data.DataManager;
import com.example.vitali.githubapiclient.data.IDataManager;
import com.example.vitali.githubapiclient.data.network.model.Repository;
import com.example.vitali.githubapiclient.ui.base.BasePresenter;

import java.util.List;


class RepositoryPresenter extends BasePresenter<RepositoryContract.View> implements RepositoryContract.Presenter {

    private Context context;
    private LoadDataCallback loadDataCallback = new LoadDataCallback();

    RepositoryPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void loadData(Context context) {
        DataManager dataManager = new DataManager(context, loadDataCallback);
        dataManager.getRepositories();
    }

    private class LoadDataCallback implements IDataManager.DataManagerCallback {

        @Override
        public void onResult(Object result, int type) {
            switch (type) {
                case DataManager.TYPE_REPOSITORIES:
                    List<Repository> repositories = (List<Repository>) result;
                    if (isViewAttached()) {
                        getView().setData(repositories);
                    }
                    break;
                default:
                    if (isViewAttached()) {
                        getView().showError();
                    }
                    break;
            }
        }

        @Override
        public void onFailure(Throwable throwable) {
            if (isViewAttached()) {
                getView().showError();
            }
        }
    }
}