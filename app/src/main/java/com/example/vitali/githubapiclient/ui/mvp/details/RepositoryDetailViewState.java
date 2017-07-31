package com.example.vitali.githubapiclient.ui.mvp.details;

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;


class RepositoryDetailViewState implements ViewState<RepositoryDetailContract.View> {

    private final int STATE_SHOW_REPOSITORY = 0;

    private int state = STATE_SHOW_REPOSITORY;

    void setShowRepository() {
        state = STATE_SHOW_REPOSITORY;
    }

    @Override
    public void apply(RepositoryDetailContract.View view, boolean retained) {
        switch (state) {
            case STATE_SHOW_REPOSITORY:
                view.showRepository();
                break;
        }
    }
}