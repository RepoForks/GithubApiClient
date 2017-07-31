package com.example.vitali.githubapiclient.ui.mvp.profile;

import android.content.Context;

import com.example.vitali.githubapiclient.data.network.model.Repository;
import com.example.vitali.githubapiclient.ui.base.BaseView;

import java.util.List;


interface RepositoryContract {

    interface View extends BaseView {

        void setData(List<Repository> repositories);

        void showRepositories();

        void showProgressDialog();

        void hideProgressDialog();

        void showError();
    }

    interface Presenter {

        void loadData(Context context);
    }
}