package com.example.vitali.githubapiclient.ui.mvp.profile;

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;


class RepositoryListViewState implements ViewState<RepositoryContract.View> {

    private final int STATE_SHOW_REPOSITORIES = 0;
    private final int STATE_SHOW_LOADING = 1;
    private final int STATE_SHOW_ERROR = 2;

    private int state = STATE_SHOW_REPOSITORIES;

    void setShowRepositories() {
        state = STATE_SHOW_REPOSITORIES;
    }

    void setShowLoading() {
        state = STATE_SHOW_LOADING;
    }

    void setShowError() {
        state = STATE_SHOW_ERROR;
    }

    @Override
    public void apply(RepositoryContract.View view, boolean retained) {
        switch (state) {
            case STATE_SHOW_REPOSITORIES:
                view.showRepositories();
                break;
            case STATE_SHOW_LOADING:
                view.showProgressDialog();
                break;
            case STATE_SHOW_ERROR:
                view.showError();
                break;
        }
    }
}