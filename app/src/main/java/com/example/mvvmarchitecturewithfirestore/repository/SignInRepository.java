package com.example.mvvmarchitecturewithfirestore.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmarchitecturewithfirestore.model.SignInUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInRepository {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private SignInUser user = new SignInUser();

    public MutableLiveData<SignInUser> checkAuthenticationInFirebase(){

        MutableLiveData<SignInUser> isAuthenticateLiveData = new MutableLiveData<>();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser== null){
            user.isAuth = false;
            isAuthenticateLiveData.setValue(user);
        }
        else {
            user.uId=currentUser.getUid();
            user.isAuth = true;
            isAuthenticateLiveData.setValue(user);
        }


        return  isAuthenticateLiveData;
    }





    // for google connected

    public  MutableLiveData<String > firebaseSignInWithGoogle(AuthCredential authCredential){
        final MutableLiveData<String> authMutableLiveData = new MutableLiveData<>();


        firebaseAuth.signInWithCredential(authCredential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {


                FirebaseUser currenUser = firebaseAuth.getCurrentUser();
                String  uId = currenUser.getUid();
                authMutableLiveData.setValue(uId);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                authMutableLiveData.setValue(e.toString());

            }
        });

        return authMutableLiveData;

    }
}
