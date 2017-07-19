package com.example.vitali.githubapiclient.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vitali.githubapiclient.R;
import com.example.vitali.githubapiclient.base.BaseFragment;
import com.example.vitali.githubapiclient.data.model.Repos;
import com.squareup.picasso.Picasso;


public class ReposDetailFragment extends BaseFragment {

    private TextView tvName, tvFullName, tvUrl, tvDescription, tvPrivate;
    private ImageView ivAvatar;
    private Repos repos;

    public ReposDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_detail, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarDetailFragment);
        if (toolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.client_details_fragment);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }

        ivAvatar = (ImageView) view.findViewById(R.id.ivUserDetailAvatar);
        tvName = (TextView) view.findViewById(R.id.tvUserDetailName);
        tvFullName = (TextView) view.findViewById(R.id.tvUserDetailFullName);
        tvUrl = (TextView) view.findViewById(R.id.tvUserDetailUrl);
        tvDescription = (TextView) view.findViewById(R.id.tvUserDetailDescription);
        tvPrivate = (TextView) view.findViewById(R.id.tvUserDetailPrivate);

        getData();
        setDataInWidgets();
        return view;
    }

    private void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            repos = (Repos) bundle.getSerializable("ReposId");
        }
    }

    private void setDataInWidgets() {
        if (repos.getName() != null) {
            tvName.setText(repos.getName());
        } else {
            tvName.setText(getResources().getString(R.string.absence_name));
        }

        if (repos.getFullName() != null) {
            tvFullName.setText(repos.getFullName());
        } else {
            tvFullName.setText(getResources().getString(R.string.absence_full_name));
        }

        if (repos.getUrl() != null) {
            tvUrl.setText(repos.getUrl());
        } else {
            tvUrl.setText(getResources().getString(R.string.absence_url));
        }

        if (repos.getDescription() != null) {
            tvDescription.setText(repos.getDescription());
        } else {
            tvDescription.setText(getResources().getString(R.string.absence_description));
        }

        if (repos.getPrivate()) {
            tvPrivate.setText(getResources().getText(R.string.repos_private));
        } else {
            tvPrivate.setText(getResources().getText(R.string.repos_public));
        }

        if (repos.getOwner().getAvatarUrl() != null) {
            Picasso.with(getActivity())
                    .load(repos.getOwner().getAvatarUrl())
                    .into(ivAvatar);
        }
    }
}