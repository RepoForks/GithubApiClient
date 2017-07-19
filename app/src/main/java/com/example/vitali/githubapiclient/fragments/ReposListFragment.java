package com.example.vitali.githubapiclient.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitali.githubapiclient.R;
import com.example.vitali.githubapiclient.adapter.UserAdapter;
import com.example.vitali.githubapiclient.base.BaseFragment;
import com.example.vitali.githubapiclient.data.model.Repos;
import com.example.vitali.githubapiclient.data.net.GithubApi;
import com.example.vitali.githubapiclient.data.net.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReposListFragment extends BaseFragment {

    private UserAdapter adapter;
    private RecyclerView rwUser;
    List<Repos> reposes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clients_list, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarListFragment);
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rwUser = (RecyclerView) view.findViewById(R.id.rwList);
        setupRecycler();
        if (adapter == null) {
            initAdapter();
        } else {
            rwUser.setAdapter(adapter);
        }
        getData();
    }

    private void setupRecycler() {
        rwUser.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rwUser.setLayoutManager(layoutManager);
    }

    private void initAdapter() {
        adapter = new UserAdapter(getContext(), reposes);
        adapter.setCallback(new UserAdapter.OnItemClickCallback() {
            @Override
            public void onClick(Repos user) {
                itemClick(user);
            }
        });
        rwUser.setAdapter(adapter);
    }

    private void itemClick(Repos repos) {
        ReposDetailFragment reposDetailFragment = new ReposDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("ReposId", repos);
        reposDetailFragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, reposDetailFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void getData() {
        if (reposes != null) {
            GithubApi api = ServiceGenerator.getInstance().getGithubService();
            Call<List<Repos>> call = api.getUserRepos("VitaliBov");
            call.enqueue(new Callback<List<Repos>>() {
                @Override
                public void onResponse(@NonNull Call<List<Repos>> call, @NonNull Response<List<Repos>> response) {
                    if (response.body() != null) {
                        reposes.addAll(response.body());
                    } else {
                        Toast.makeText(getActivity(), "No repositories", Toast.LENGTH_SHORT).show();
                    }
                    rwUser.getAdapter().notifyDataSetChanged();
                }
                @Override
                public void onFailure(@NonNull Call<List<Repos>> call, @NonNull Throwable t) {
                    Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}