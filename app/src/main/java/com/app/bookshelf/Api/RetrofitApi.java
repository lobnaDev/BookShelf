package com.app.bookshelf.Api;

import com.app.bookshelf.model.MyBooks;
import com.app.bookshelf.model.MyList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private static final String Base_url = "https://api.nytimes.com/svc/books/v3/";
    private static RetrofitApi retrofitApi;
    private static ApiInterface apiInterface;
    String api_key ="pJuSGO8IgsreKOmIUIK87AQZ6cmfJeIr";

    public RetrofitApi() {
      Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static RetrofitApi getInstance(){
       if(null == retrofitApi ){
           retrofitApi = new RetrofitApi();
       }
        return retrofitApi;
    }
    public  Call<MyList> getcurrentnonficList(){
       return apiInterface.getcurrentnonficList("tw84ALomLHEu6nFX0000uJyts3c3h0qu");
    }

    public  Call<MyList> getcurrentficList(){
        return apiInterface.getcurrentficList("tw84ALomLHEu6nFX0000uJyts3c3h0qu");
    }
}
