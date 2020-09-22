package com.example.mvvmarchitecturewithfirestore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmarchitecturewithfirestore.model.SignInUser;
import com.example.mvvmarchitecturewithfirestore.repository.SignInRepository;
import com.google.firebase.auth.AuthCredential;

public class SignInViewModel extends AndroidViewModel {


    private SignInRepository repository;
    public LiveData<SignInUser> checkAuthenticateLiveData;
    public  LiveData<String > auticationLiveData;
    public LiveData<SignInUser> collectUserInfoLiveData;

    public SignInViewModel(@NonNull Application application) {
        super(application);
       repository = new SignInRepository();

    }

    public  void  signInWithGoogle(AuthCredential authCredential){

        auticationLiveData = repository.firebaseSignInWithGoogle(authCredential);
    }

    public  void checkAuthenticate(){

        checkAuthenticateLiveData = repository.checkAuthenticationInFirebase();
    }

    public void collectUserInfo(){
        collectUserInfoLiveData= repository.collectUserData();
    }


    public  void  cololectUserInfo(){
        collectUserInfoLiveData = repository.collectUserData();
    }
}
