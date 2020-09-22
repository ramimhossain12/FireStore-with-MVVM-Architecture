package com.example.mvvmarchitecturewithfirestore.repository;

import android.net.Uri;

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

    //collect user info
    public  MutableLiveData<SignInUser> collectUserData(){

        MutableLiveData<SignInUser> colloectMutabaleLivaData = new MutableLiveData<>();

        FirebaseUser currentUser= firebaseAuth.getCurrentUser();

        if (currentUser !=null){
             String  uId = currentUser.getUid();
             String  name = currentUser.getDisplayName();
            String email= currentUser.getEmail();
            Uri getImageUrl= currentUser.getPhotoUrl();
            String imageUrl= getImageUrl.toString();
            SignInUser user= new SignInUser(uId,name,email,imageUrl);
            colloectMutabaleLivaData.setValue(user);
        }
        return  colloectMutabaleLivaData;
    }
}
