package com.example.vitali.githubapiclient.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.vitali.githubapiclient.R;
import com.example.vitali.githubapiclient.ui.mvp.profile.RepositoryListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            RepositoryListFragment repositoryListFragment = new RepositoryListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, repositoryListFragment);
            ft.commit();
        }
    }
}