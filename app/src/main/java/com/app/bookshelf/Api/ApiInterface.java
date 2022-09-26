package com.app.bookshelf.Api;

import com.app.bookshelf.model.MyBooks;
import com.app.bookshelf.model.MyList;
import com.app.bookshelf.model.MyResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("lists/current/hardcover-fiction.json")
    Call<MyList>getcurrentficList(@Query("api-key") String api_key);

//    @GET("lists/current/hardcover-nonfiction.json?api-key=pJuSGO8IgsreKOmIUIK87AQZ6cmfJeIr")
//            Call<MyList> getcurrentnonficList();

 @GET("lists/current/hardcover-nonfiction.json")
 Call<MyList> getcurrentnonficList(@Query("api-key") String api_key);

}
