package com.app.bookshelf.ui.Books;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.bookshelf.Api.RetrofitApi;
import com.app.bookshelf.model.MyBooks;
import com.app.bookshelf.model.MyList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Lobna Babker on 7/8/2022.
 */
public class BookListViewModel extends ViewModel {
   // MutableLiveData<List<MyList>> listMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<MyBooks>> listMutableLiveData = new MutableLiveData<>();
    private ArrayList<MyBooks> mList;

    public void getCurrentFictionBooklist(){
        mList = new ArrayList();
        RetrofitApi.getInstance().getcurrentficList().enqueue(new Callback<MyList>() {
            @Override
            public void onResponse(Call<MyList> call, Response<MyList> response) {
                for (int i = 0; i < response.body().getResults().getBooks().size(); i++) {
                    MyBooks book = new MyBooks();
                    book.setList_name(response.body().getResults().getList_name());
                    book.setTitle(response.body().getResults().getBooks().get(i).getTitle());
                    book.setAuthor(response.body().getResults().getBooks().get(i).getAuthor());
                    book.setPrice(response.body().getResults().getBooks().get(i).getPrice());
                    book.setBook_url(response.body().getResults().getBooks().get(i).getBook_url());
                    book.setBook_image(response.body().getResults().getBooks().get(i).getBook_image());
                    mList.add(book);
                    Log.i("Activity", "Response f " + '\n' + response.body().getResults().getBooks().get(i).getTitle() + '\n');
                    listMutableLiveData.setValue(mList);
                }


            }

            @Override
            public void onFailure(Call<MyList> call, Throwable t) {

            }
        });
    }

    public void getCurrentnonficBooklist(){
        mList = new ArrayList();
        RetrofitApi.getInstance().getcurrentnonficList().enqueue(new Callback<MyList>() {
            @Override
            public void onResponse(Call<MyList> call, Response<MyList> response) {
                for (int i = 0; i < response.body().getResults().getBooks().size(); i++) {
                    MyBooks book = new MyBooks();
                    book.setList_name(response.body().getResults().getList_name());
                    book.setTitle(response.body().getResults().getBooks().get(i).getTitle());
                    book.setAuthor(response.body().getResults().getBooks().get(i).getAuthor());
                    book.setPrice(response.body().getResults().getBooks().get(i).getPrice());
                    book.setBook_url(response.body().getResults().getBooks().get(i).getBook_url());
                    book.setBook_image(response.body().getResults().getBooks().get(i).getBook_image());
                    mList.add(book);
                    Log.i("Activity", "Response n f " + '\n' + response.body().getResults().getBooks().get(i).getTitle() + '\n');
                    listMutableLiveData.setValue(mList);
                }


            }

            @Override
            public void onFailure(Call<MyList> call, Throwable t) {

            }
        });
    }

    public void getBookinfo(String name,String listname){
        mList = new ArrayList();
        if(listname.equals("Hardcover Fiction")) {
            RetrofitApi.getInstance().getcurrentficList().enqueue(new Callback<MyList>() {
                @Override
                public void onResponse(Call<MyList> call, Response<MyList> response) {
                    for (int i = 0; i < response.body().getResults().getBooks().size(); i++) {
                        if (response.body().getResults().getBooks().get(i).getTitle().equals(name)) {
                            MyBooks book = new MyBooks();
                            book.setTitle(response.body().getResults().getBooks().get(i).getTitle());
                            book.setAuthor(response.body().getResults().getBooks().get(i).getAuthor());
                            book.setPrice(response.body().getResults().getBooks().get(i).getPrice());
                            book.setBook_url(response.body().getResults().getBooks().get(i).getBook_url());
                            book.setBook_image(response.body().getResults().getBooks().get(i).getBook_image());
                            mList.add(book);
                            Log.i("Activity", " getBookinfo" + '\n' + response.body().getResults().getBooks().get(i).getTitle() + '\n'+listname);
                            listMutableLiveData.setValue(mList);
                        }
                    }


                }

                @Override
                public void onFailure(Call<MyList> call, Throwable t) {

                }
            });
        }else{
            getBookinfoNonFic(name,listname);
        }


    }
    //Hardcover Nonfiction
    public void getBookinfoNonFic(String name,String listname){
        mList = new ArrayList();
        RetrofitApi.getInstance(). getcurrentnonficList().enqueue(new Callback<MyList>() {
            @Override
            public void onResponse(Call<MyList> call, Response<MyList> response) {
                for (int i = 0; i < response.body().getResults().getBooks().size(); i++) {
                    if(response.body().getResults().getBooks().get(i).getTitle().equals(name) ) {
                        MyBooks book = new MyBooks();
                        book.setTitle(response.body().getResults().getBooks().get(i).getTitle());
                        book.setAuthor(response.body().getResults().getBooks().get(i).getAuthor());
                        book.setPrice(response.body().getResults().getBooks().get(i).getPrice());
                        book.setBook_url(response.body().getResults().getBooks().get(i).getBook_url());
                        book.setBook_image(response.body().getResults().getBooks().get(i).getBook_image());
                        mList.add(book);
                        Log.i("Activity", " getBookinfo" + '\n' + response.body().getResults().getBooks().get(i).getTitle() + '\n'+listname);
                        listMutableLiveData.setValue(mList);
                    }
           }


            }

            @Override
            public void onFailure(Call<MyList> call, Throwable t) {

            }
        });


    }


}
