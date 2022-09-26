package com.app.bookshelf.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.app.bookshelf.Api.TopStoriesApi.TopStoriesRetrofitApi;
import com.app.bookshelf.model.MyStories;
import com.app.bookshelf.model.MyStoriesResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lobna Babker on 8/14/2022.
 */
public class TopStoriesRepo {
    MutableLiveData<List<MyStoriesResult>> listMutableLiveData ;
    private ArrayList<MyStoriesResult> mList;
    private Application application;

    public TopStoriesRepo(Application application) {
        this.application = application;
        listMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<MyStoriesResult>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void getToplist(String sec) {
        mList = new ArrayList();
        TopStoriesRetrofitApi.getInstance().getTopStories(sec).enqueue(new Callback<MyStories>() {
            @Override
            public void onResponse(Call<MyStories> call, Response<MyStories> response) {
                for (int i = 0; i < response.body().getResultslist().size(); i++) {
                    MyStoriesResult result = new MyStoriesResult();
                    result.setTitle(response.body().getResultslist().get(i).getTitle());
                    result.setByline(response.body().getResultslist().get(i).getByline());
                    result.setPublished_date(response.body().getResultslist().get(i).getPublished_date());
                    result.setUrl(response.body().getResultslist().get(i).getUrl());
                    result.setMultimedia(response.body().getResultslist().get(i).getMultimedia());
                    mList.add(result);
                    Log.i("Activity", "Response Topestries " + '\n' + response.body().getResultslist().get(i).getTitle() + '\n');
                    listMutableLiveData.setValue(mList);
                }
            }

            @Override
            public void onFailure(Call<MyStories> call, Throwable t) {

            }
        });
    }
    public void applySearch(String sectionName,String searchStr) {
        mList = new ArrayList();
        ArrayList<MyStoriesResult> mSearchList = new ArrayList<>();
        TopStoriesRetrofitApi.getInstance().getTopStories(sectionName).enqueue(new Callback<MyStories>() {
            @Override
            public void onResponse(Call<MyStories> call, Response<MyStories> response) {
                for (int i = 0; i < response.body().getResultslist().size(); i++) {
                    if(response.body().getResultslist().get(i).getTitle().toLowerCase().contains(searchStr.toLowerCase())) {
                        MyStoriesResult result = new MyStoriesResult();
                        result.setTitle(response.body().getResultslist().get(i).getTitle());
                        result.setByline(response.body().getResultslist().get(i).getByline());
                        result.setPublished_date(response.body().getResultslist().get(i).getPublished_date());
                        result.setUrl(response.body().getResultslist().get(i).getUrl());
                        result.setMultimedia(response.body().getResultslist().get(i).getMultimedia());
                        mSearchList.add(result);
                        listMutableLiveData.setValue(mSearchList);
                    }
                }
            }

            @Override
            public void onFailure(Call<MyStories> call, Throwable t) {

            }
        });



        // set the adapter hear

    }
}
