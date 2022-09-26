package com.app.bookshelf.Api.TopStoriesApi;

import com.app.bookshelf.model.MyList;
import com.app.bookshelf.model.MyStories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lobna Babker on 7/20/2022.
 */
public interface TopStoriesApiInterface {
    @GET("v2/{section}.json")
    Call<MyStories> getTopStories(@Path("section") String Section_name ,@Query("api-key") String api_key );
}
