package com.example.vitali.githubapiclient.ui.details;


import com.example.vitali.githubapiclient.ui.base.BaseView;

public interface RepositoryDetailContract {

    interface View extends BaseView {

        void onBind();
    }

    interface Presenter {

        void getData();
    }
}