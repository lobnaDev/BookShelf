package com.app.bookshelf.ui.TopStories;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.bookshelf.Api.RetrofitApi;
import com.app.bookshelf.Api.TopStoriesApi.TopStoriesRetrofitApi;
import com.app.bookshelf.model.MyBooks;
import com.app.bookshelf.model.MyList;
import com.app.bookshelf.model.MyStories;
import com.app.bookshelf.model.MyStoriesResult;
import com.app.bookshelf.repository.AuthRepo;
import com.app.bookshelf.repository.DatabaseRepo;
import com.app.bookshelf.repository.TopStoriesRepo;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lobna Babker on 7/20/2022.
 */
public class TopStoriesViewModel extends ViewModel {
    private ArrayList<MyStoriesResult> mList;
    private TopStoriesRepo mRepo ;
    public MutableLiveData<List<MyStoriesResult>> listMutableLiveData ;
    Application application;
    //AuthRepo
    AuthRepo repository;
    DatabaseRepo dbRepo;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> LoggedStatus;



    public TopStoriesViewModel() {
        mRepo = new TopStoriesRepo(application);
        listMutableLiveData = mRepo.getListMutableLiveData();
        //AuthRepo
        repository = new AuthRepo(application);
        userData = repository.getFirebaseUserMutableLiveData();
        LoggedStatus = repository.getUserLoggedMutableLiveData();

        dbRepo = new DatabaseRepo();



    }

    public void getTopStorieslist(String section){
        mRepo.getToplist(section);
    }
    public void Filter(String sectionName,String title){
        mRepo.applySearch(sectionName,title);

    }
    //AuthRepo
    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<Boolean> getLoggedStatus() {
        return LoggedStatus;
    }


    public void addMyFavoriteToDatabase(String sectionName , String url ,String title ,String author ,String date,String image ){
        dbRepo.addMyFavoriteToDatabase(sectionName,url,title,author,date,image);
    }



}
