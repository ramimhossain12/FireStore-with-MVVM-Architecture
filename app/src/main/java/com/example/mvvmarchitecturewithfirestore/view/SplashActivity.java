package com.example.mvvmarchitecturewithfirestore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmarchitecturewithfirestore.model.SignInUser;
import com.example.mvvmarchitecturewithfirestore.viewmodel.SignInViewModel;

public class SplashActivity extends AppCompatActivity {

    private SignInViewModel signInViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initSplashViewModel();
        checkIfUserAuthenticate();
    }

    private void checkIfUserAuthenticate() {
        signInViewModel.checkAuthenticate();
        signInViewModel.checkAuthenticateLiveData.observe(this, new Observer<SignInUser>() {
            @Override
            public void onChanged(SignInUser signInUser) {

                if (!signInUser.isAuth) {
                    goToSignInActivity();
                } else {
                    goToMainActivity();
                }
            }
        });

    }

    private void goToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToSignInActivity() {


        Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    private void initSplashViewModel() {


        signInViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(SignInViewModel.class);
    }
}