package com.example.vitali.githubapiclient.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vitali.githubapiclient.R;
import com.example.vitali.githubapiclient.adapter.UserAdapter;
import com.example.vitali.githubapiclient.base.BaseFragment;
import com.example.vitali.githubapiclient.data.model.User;


public class ClientsListFragment extends BaseFragment {

    private UserAdapter adapter;
    private RecyclerView rwUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clients_list, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarListFragment);
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        }

        rwUser = (RecyclerView) view.findViewById(R.id.rwList);
        setupRecycler();

        return view;
    }

    private void setupRecycler() {
        rwUser.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rwUser.setLayoutManager(layoutManager);

        adapter = new UserAdapter(getContext());
        adapter.setCallback(new UserAdapter.OnItemClickCallback() {
            @Override
            public void onClick(User user) {
                itemClick(user);
            }
        });
        rwUser.setAdapter(adapter);
    }

    private void itemClick(User user) {
        ClientDetailFragment clientDetailFragment = new ClientDetailFragment();
        long l = user.getId();
        Bundle bundle = new Bundle();
        bundle.putLong("PersonID", l);
        clientDetailFragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, clientDetailFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}