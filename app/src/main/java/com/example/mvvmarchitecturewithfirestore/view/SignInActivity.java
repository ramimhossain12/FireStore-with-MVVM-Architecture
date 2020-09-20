package com.example.mvvmarchitecturewithfirestore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvvmarchitecturewithfirestore.R;
import com.example.mvvmarchitecturewithfirestore.viewmodel.SignInViewModel;

public class SignInActivity extends AppCompatActivity {

    private Button button;

    private SignInViewModel signInViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        intiSignInViewModel();

        button = findViewById(R.id.signInButtonID);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              signIn();
            }
        });
    }

    private void intiSignInViewModel() {

        signInViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(SignInViewModel.class);
    }

    private void signIn() {


    }
}