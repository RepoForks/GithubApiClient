package com.example.vitali.githubapiclient.ui.details;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vitali.githubapiclient.R;
import com.example.vitali.githubapiclient.data.network.model.Repository;
import com.example.vitali.githubapiclient.ui.base.BaseFragment;
import com.squareup.picasso.Picasso;


public class RepositoryDetailFragment extends BaseFragment implements RepositoryDetailContract.View {

    private TextView tvName, tvFullName, tvUrl, tvDescription, tvPrivate;
    private ImageView ivAvatar;
    private Repository repository;

    public RepositoryDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_detail, container, false);

        initToolbar(view);

        ivAvatar = (ImageView) view.findViewById(R.id.ivUserDetailAvatar);
        tvName = (TextView) view.findViewById(R.id.tvUserDetailName);
        tvFullName = (TextView) view.findViewById(R.id.tvUserDetailFullName);
        tvUrl = (TextView) view.findViewById(R.id.tvUserDetailUrl);
        tvDescription = (TextView) view.findViewById(R.id.tvUserDetailDescription);
        tvPrivate = (TextView) view.findViewById(R.id.tvUserDetailPrivate);

        getData();
        onBind();
        return view;
    }

    public void onBind() {
        if (repository.getName() != null) {
            tvName.setText(repository.getName());
        } else {
            tvName.setText(getResources().getString(R.string.absence_name));
        }

        if (repository.getFullName() != null) {
            tvFullName.setText(repository.getFullName());
        } else {
            tvFullName.setText(getResources().getString(R.string.absence_full_name));
        }

        if (repository.getUrl() != null) {
            tvUrl.setText(repository.getUrl());
        } else {
            tvUrl.setText(getResources().getString(R.string.absence_url));
        }

        if (repository.getDescription() != null) {
            tvDescription.setText(repository.getDescription());
        } else {
            tvDescription.setText(getResources().getString(R.string.absence_description));
        }

        if (repository.getPrivate()) {
            tvPrivate.setText(getResources().getText(R.string.repos_private));
        } else {
            tvPrivate.setText(getResources().getText(R.string.repos_public));
        }

        try {
            Picasso.with(getActivity())
                    .load(repository.getOwner().getAvatarUrl())
                    .into(ivAvatar);
        } catch (NullPointerException e) {
            Log.d("Log", "onBind: AvatarUrl is null");
        }
    }

    private void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            repository = (Repository) bundle.getSerializable("repos");
        }
    }

    private void initToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarDetailFragment);
        if (toolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.client_details_fragment);
            toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.white));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        }
    }
}