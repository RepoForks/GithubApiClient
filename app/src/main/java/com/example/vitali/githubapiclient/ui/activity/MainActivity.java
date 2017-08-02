package com.example.vitali.githubapiclient.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.vitali.githubapiclient.App;
import com.example.vitali.githubapiclient.R;
import com.example.vitali.githubapiclient.navigation.Screens;
import com.example.vitali.githubapiclient.ui.mvp.details.RepositoryDetailFragment;
import com.example.vitali.githubapiclient.ui.mvp.profile.RepositoryListFragment;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            App.INSTANCE.getRouter().replaceScreen(Screens.REPOSITORY_LIST_FRAGMENT);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.INSTANCE.getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.INSTANCE.getNavigatorHolder().removeNavigator();
    }

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.container) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey){
                case Screens.REPOSITORY_LIST_FRAGMENT:
                    return RepositoryListFragment.newInstance(1);
                case Screens.REPOSITORY_DETAIL_FRAGMENT:
                    return RepositoryDetailFragment.newInstance(2, (Bundle) data);
                default:
                    throw new RuntimeException(getString(R.string.unknown_screen));
            }
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }
    };
}