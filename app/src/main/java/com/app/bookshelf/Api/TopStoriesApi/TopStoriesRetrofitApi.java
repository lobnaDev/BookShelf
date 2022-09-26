package com.app.bookshelf.Api.TopStoriesApi;

import com.app.bookshelf.Api.ApiInterface;
import com.app.bookshelf.Api.RetrofitApi;
import com.app.bookshelf.model.MyList;
import com.app.bookshelf.model.MyStories;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lobna Babker on 7/20/2022.
 */
public class TopStoriesRetrofitApi {
    private static final String Base_url = "https://api.nytimes.com/svc/topstories/";
    private static TopStoriesRetrofitApi topStoriesRetrofitApi;
    private static TopStoriesApiInterface apiInterface;
    String api_key ="pJuSGO8IgsreKOmIUIK87AQZ6cmfJeIr";

    public TopStoriesRetrofitApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(TopStoriesApiInterface.class);
    }

    public static TopStoriesRetrofitApi getInstance(){
        if(null == topStoriesRetrofitApi ){
            topStoriesRetrofitApi = new TopStoriesRetrofitApi();
        }
        return topStoriesRetrofitApi;
    }
    public Call<MyStories> getTopStories(String section){
        return apiInterface.getTopStories(section,"tw84ALomLHEu6nFX0000uJyts3c3h0qu");

//        arts, automobiles, books, business, fashion, food, health, home, insider, magazine, movies, nyregion, obituaries, opinion,
//                politics, realestate, science, sports, sundayreview, technology, theater, t-magazine, travel, upshot, us, world
    }
}
