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
import com.example.vitali.githubapiclient.data.model.User;
import com.squareup.picasso.Picasso;


public class ClientDetailFragment extends BaseFragment {

    private TextView tvName, tvFullName, tvUrl, tvDescription, tvPrivate;
    private ImageView ivAvatar;
    private User user = new User();

    public ClientDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        }
    }

    private void setDataInWidgets() {
        tvName.setText(user.getName());
        tvFullName.setText(user.getFullName());
        tvUrl.setText(user.getUrl());
        tvDescription.setText(user.getDescription());
        tvPrivate.setText(user.getPrivate().toString());
        if (user.getOwner().getAvatarUrl() != null) {
            Picasso.with(getActivity()).
                    load(user.getOwner().getAvatarUrl().replace("https", "http"))
                    .into(ivAvatar);
        }
    }
}