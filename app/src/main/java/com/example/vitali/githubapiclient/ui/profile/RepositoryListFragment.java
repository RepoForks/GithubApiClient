package com.example.vitali.githubapiclient.ui.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitali.githubapiclient.R;
import com.example.vitali.githubapiclient.data.network.model.Repository;
import com.example.vitali.githubapiclient.ui.details.RepositoryDetailFragment;
import com.example.vitali.githubapiclient.utils.NetworkUtils;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment;

import java.util.List;


public class RepositoryListFragment extends MvpViewStateFragment<RepositoryContract.View, RepositoryPresenter, RepositoryListViewState>
        implements RepositoryContract.View, RepositoryAdapter.RepositoryClickListener {

    private RepositoryAdapter adapter;
    private RecyclerView rwUser;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clients_list, container, false);setRetainInstance(true);
        initToolbar(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView(view);
    }

    @NonNull
    @Override
    public RepositoryPresenter createPresenter() {
        return new RepositoryPresenter(getContext());
    }

    @NonNull
    @Override
    public RepositoryListViewState createViewState() {
        return new RepositoryListViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showRepositories();
    }

    @Override
    public void setData(List<Repository> repositories) {
        adapter.setRepositories(repositories);
    }

    @Override
    public void showRepositories() {
        RepositoryListViewState vs = viewState;
        vs.setShowRepositories();
        checkAdapter(this);
    }

    @Override
    public void showProgressDialog() {
        RepositoryListViewState vs = viewState;
        vs.setShowLoading();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.loading_data));
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }

    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showError() {
        RepositoryListViewState vs = viewState;
        vs.setShowError();
        Toast toast = Toast.makeText(getContext(), R.string.data_download_error, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void onClick(Repository repository) {
        navigateToDetailFragment(repository);
    }

    private void initToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarListFragment);
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.white));
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
    }

    private void setupRecyclerView(View view) {
        rwUser = (RecyclerView) view.findViewById(R.id.rwList);
        rwUser.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rwUser.setLayoutManager(layoutManager);
    }

    private void checkAdapter(RepositoryAdapter.RepositoryClickListener listener) {
        if (adapter == null) {
            initAdapter(listener);
            presenter.loadData(getContext());
        } else {
            rwUser.setAdapter(adapter);
        }
    }

    private void initAdapter(RepositoryAdapter.RepositoryClickListener listener) {
        adapter = new RepositoryAdapter(listener);
        rwUser.setAdapter(adapter);
    }

    private void navigateToDetailFragment(Repository repository) {
        RepositoryDetailFragment repositoryDetailFragment = new RepositoryDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("repos", repository);
        repositoryDetailFragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, repositoryDetailFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private boolean getNetworkAvailability() {
        return NetworkUtils.isNetworkAvailable(getContext());
    }
}