package com.example.mvvmarchitecturewithfirestore.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mvvmarchitecturewithfirestore.R;
import com.example.mvvmarchitecturewithfirestore.model.SignInUser;
import com.example.mvvmarchitecturewithfirestore.viewmodel.SignInViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment {

    private Button signOutButton;
    private CircleImageView profileImageView;
    private TextView nameTextView,emailTextView;
    private SignInViewModel signInViewModel;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            getUserInfo();
            intiGoogleSignClient();
        signOutButton = view.findViewById(R.id.signOutButtonId);
        profileImageView= view.findViewById(R.id.profileImageId);
        nameTextView= view.findViewById(R.id.profileNameId);
        emailTextView= view.findViewById(R.id.profileEmailId);


        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void intiGoogleSignClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        googleSignInClient = GoogleSignIn.getClient(getActivity(), googleSignInOptions);


    }

    private void signOut() {

         firebaseAuth.signOut();
        googleSignInClient.signOut();
        Intent intent= new Intent(getActivity(),SignInActivity.class);
        startActivity(intent);
        getActivity().onBackPressed();
    }

    private void getUserInfo() {
        signInViewModel= new ViewModelProvider(getActivity(),ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(SignInViewModel.class);
        signInViewModel.collectUserInfo();
        signInViewModel.collectUserInfoLiveData.observe(getViewLifecycleOwner(), new Observer<SignInUser>() {
            @Override
            public void onChanged(SignInUser signInUser) {
              setProfile(signInUser);
            }
        });

    }

    private void setProfile(SignInUser signInUser) {
        if(signInUser != null){
            Glide.with(getActivity()).load(signInUser.getImageUrl())
                    .centerCrop().placeholder(R.drawable.profile).into(profileImageView);
            nameTextView.setText(signInUser.getName());
            emailTextView.setText(signInUser.getEmail());
        }
    }
}