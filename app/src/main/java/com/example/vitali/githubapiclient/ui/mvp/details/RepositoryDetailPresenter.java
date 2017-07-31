package com.example.vitali.githubapiclient.ui.mvp.details;

import android.content.Context;

import com.example.vitali.githubapiclient.ui.base.BasePresenter;

class RepositoryDetailPresenter extends BasePresenter<RepositoryDetailContract.View>
        implements RepositoryDetailContract.Presenter {

    RepositoryDetailPresenter(Context c) {
        Context context = c;
    }

    @Override
    public void getData() {

    }
}