package com.app.bookshelf.ui.profile;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.bookshelf.model.MyStoriesResult;
import com.app.bookshelf.model.MyUsers;
import com.app.bookshelf.repository.AuthRepo;
import com.app.bookshelf.repository.DatabaseRepo;
import com.app.bookshelf.utils.ViewUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by Lobna Babker on 9/17/2022.
 */
public class profileViewModel extends ViewModel {
    DatabaseRepo mRepo;
    AuthRepo repository;
    Application application;
    String userId;
    FirebaseAuth auth;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> LoggedStatus;
    public MutableLiveData<List<MyUsers>> listMutableLiveData;

    public profileViewModel() {
        mRepo = new DatabaseRepo();
        repository = new AuthRepo(application);
        listMutableLiveData = mRepo.getUserlistMutableLiveData();
        userData = repository.getFirebaseUserMutableLiveData();
        LoggedStatus = repository.getUserLoggedMutableLiveData();

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){
            userId = auth.getCurrentUser().getUid();
        }
    }

    public void getProfileInfo() {
        if (userId != null) {
            mRepo.getUserInfo(userId);
        }else{
            ViewUtils.showToast(application.getApplicationContext(), "Please Login");
        }
    }
}
