package com.example.mvvmarchitecturewithfirestore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmarchitecturewithfirestore.model.SignInUser;
import com.example.mvvmarchitecturewithfirestore.repository.SignInRepository;

public class SignInViewModel extends AndroidViewModel {


    private SignInRepository repository;
    public LiveData<SignInUser> checkAuthenticateLiveData;

    public SignInViewModel(@NonNull Application application) {
        super(application);
       repository = new SignInRepository();

    }

    public  void checkAuthenticate(){

        checkAuthenticateLiveData = repository.checkAuthenticationInFirebase();
    }



}
