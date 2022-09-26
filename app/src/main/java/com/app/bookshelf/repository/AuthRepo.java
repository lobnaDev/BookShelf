package com.app.bookshelf.repository;

import android.app.Application;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.app.bookshelf.utils.ViewUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Lobna Babker on 9/5/2022.
 */
public class AuthRepo {
    private Application application;
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> userLoggedMutableLiveData;
    FirebaseAuth auth;
    DatabaseRepo databaseRepo = new DatabaseRepo();

    public AuthRepo(Application application){
        this.application = application;
        firebaseUserMutableLiveData = new MutableLiveData<>();
        userLoggedMutableLiveData  = new MutableLiveData<>();
        //extra
        userLoggedMutableLiveData.postValue(true);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
          userLoggedMutableLiveData.postValue(false);

        }
    }

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public MutableLiveData<Boolean> getUserLoggedMutableLiveData() {
        return userLoggedMutableLiveData;
    }


    public void register(String email, String password,String name ,String gender,Uri imagePath){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String id = auth.getUid();
                    firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                    //add to database
                    databaseRepo.addUserToDatabase(email , name ,id,gender,imagePath);


                }else{
                    //show toast
                    ViewUtils.showToast(application.getApplicationContext(), task.getException().getMessage());
                }
            }
        });
    }

    public void Login(String email,String password){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                }else{
                    //show toast
                }
            }
        });
    }

    public void Logout(){
        auth.signOut();
        userLoggedMutableLiveData.postValue(true);
    }
}
