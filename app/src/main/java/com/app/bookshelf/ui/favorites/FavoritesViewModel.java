package com.app.bookshelf.ui.favorites;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.bookshelf.model.MyBooks;
import com.app.bookshelf.model.MyStoriesResult;
import com.app.bookshelf.repository.AuthRepo;
import com.app.bookshelf.repository.DatabaseRepo;
import com.app.bookshelf.repository.TopStoriesRepo;
import com.app.bookshelf.utils.ViewUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lobna Babker on 9/14/2022.
 */
public class FavoritesViewModel  extends ViewModel {
    public MutableLiveData<List<MyStoriesResult>> listMutableLiveData;
    DatabaseRepo mRepo;
    Application application;
    //AuthRepo
    AuthRepo repository;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> LoggedStatus;
    String userId;
    FirebaseAuth auth;

    public FavoritesViewModel() {
        mRepo = new DatabaseRepo();
        repository = new AuthRepo(application);
        listMutableLiveData = mRepo.getListMutableLiveData();
        userData = repository.getFirebaseUserMutableLiveData();
        LoggedStatus = repository.getUserLoggedMutableLiveData();

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){
            userId = auth.getCurrentUser().getUid();

        }

    }
    

    public void getFavorites(){
        if (userId != null) {
            mRepo.getFavoritesArticle(userId);
        }else{
            ViewUtils.showToast(application.getApplicationContext(), "Please Login");
        }

    }


}
