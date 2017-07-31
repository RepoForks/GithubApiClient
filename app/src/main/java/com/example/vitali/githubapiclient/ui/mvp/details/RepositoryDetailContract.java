package com.example.vitali.githubapiclient.ui.mvp.details;


import com.example.vitali.githubapiclient.ui.base.BaseView;

public interface RepositoryDetailContract {

    interface View extends BaseView {

        void onBind();

        void showRepository();
    }

    interface Presenter {

        void getData();
    }
}