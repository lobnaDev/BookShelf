package com.app.bookshelf.ui.auth;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.bookshelf.repository.AuthRepo;
import com.app.bookshelf.repository.TopStoriesRepo;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Lobna Babker on 9/5/2022.
 */
public class AuthViewModel extends ViewModel {
    AuthRepo repository;
    Application application;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> LoggedStatus;

    public AuthViewModel(){
        repository = new AuthRepo( application);
        userData = repository.getFirebaseUserMutableLiveData();
        LoggedStatus = repository.getUserLoggedMutableLiveData();

    }

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<Boolean> getLoggedStatus() {
        return LoggedStatus;
    }

    public void userRegister(String email, String password, String name , String gender , Uri imagePath){
        repository.register(email,password,name,gender,imagePath);
    }

    public void userLogin(String email,String password){
        repository.Login(email,password);
    }
    public void userLogout(){
        repository.Logout();
    }
}
