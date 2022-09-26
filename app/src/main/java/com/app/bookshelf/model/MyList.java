package com.app.bookshelf.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyList {
    @SerializedName("status")
    String status;
    @SerializedName("copyright")
    String copyright ;
    @SerializedName("num_results")
    int num_results ;
    @SerializedName("last_modified")
    String last_modified ;
    MyResults results;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public MyResults getResults() {
        return results;
    }

    public void setResults(MyResults results) {
        this.results = results;
    }
}


