package com.example.vitali.githubapiclient.ui.mvp.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vitali.githubapiclient.App;
import com.example.vitali.githubapiclient.R;
import com.example.vitali.githubapiclient.data.network.model.Repository;
import com.example.vitali.githubapiclient.navigation.Screens;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment;
import com.squareup.picasso.Picasso;


public class RepositoryDetailFragment extends MvpViewStateFragment<RepositoryDetailContract.View,
        RepositoryDetailPresenter, RepositoryDetailViewState>
        implements RepositoryDetailContract.View {

    private static final String BUNDLE_KEY = "BundleKey";
    private TextView tvName, tvFullName, tvUrl, tvDescription, tvPrivate;
    private ImageView ivAvatar;
    private Repository repository;

    public static RepositoryDetailFragment newInstance(int position, Bundle bundle) {
        RepositoryDetailFragment fragment = new RepositoryDetailFragment();
        Bundle b = new Bundle();
        b.putInt(Screens.KEY, position);
        b.putSerializable(BUNDLE_KEY, bundle.getSerializable("repository"));
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_detail, container, false);
        initToolbar(view);
        initView(view);
        onBind();
        return view;
    }

    @NonNull
    @Override
    public RepositoryDetailPresenter createPresenter() {
        return new RepositoryDetailPresenter(getContext());
    }

    @NonNull
    @Override
    public RepositoryDetailViewState createViewState() {
        return new RepositoryDetailViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        //    showRepository();
    }

    @Override
    public void showRepository() {
        RepositoryDetailViewState vs = viewState;
        vs.setShowRepository();
        presenter.getData();
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
            ivAvatar.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.identicon));
        }
    }

    private void initToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarDetailFragment);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.client_details_fragment);
            toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.white));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            toolbar.setNavigationOnClickListener(v -> App.INSTANCE.getRouter().backTo(Screens.REPOSITORY_LIST_FRAGMENT));
        }
    }

    private void initView(View view) {
        ivAvatar = (ImageView) view.findViewById(R.id.ivUserDetailAvatar);
        tvName = (TextView) view.findViewById(R.id.tvUserDetailName);
        tvFullName = (TextView) view.findViewById(R.id.tvUserDetailFullName);
        tvUrl = (TextView) view.findViewById(R.id.tvUserDetailUrl);
        tvDescription = (TextView) view.findViewById(R.id.tvUserDetailDescription);
        tvPrivate = (TextView) view.findViewById(R.id.tvUserDetailPrivate);
    }

    private void getData() {
        repository = (Repository) getArguments().getSerializable(BUNDLE_KEY);
    }
}