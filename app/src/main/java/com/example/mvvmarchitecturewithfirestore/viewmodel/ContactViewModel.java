package com.example.mvvmarchitecturewithfirestore.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmarchitecturewithfirestore.model.ContactUser;
import com.example.mvvmarchitecturewithfirestore.repository.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository repository;
    public LiveData<String> insertResultLiveData;
    public LiveData<List<ContactUser>> getContactLiveData;
    public LiveData<List<ContactUser>> searchLiveData;
    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository();
    }

    public  void  insert(ContactUser user, Uri uri){
        insertResultLiveData = repository.insertContactFireStore(user,uri);
    }
}
