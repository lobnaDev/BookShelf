package com.app.bookshelf.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lobna Babker on 7/20/2022.
 */
public class MyStories {
    @SerializedName("status")
    String status;
    @SerializedName("copyright")
    String copyright;
    @SerializedName("section")
    String section;
    @SerializedName("last_updated")
    String last_updated;
    @SerializedName("num_results")
    String num_results;
   MyStoriesResult result;

   ArrayList<MyStoriesResult>results;

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getNum_results() {
        return num_results;
    }

    public void setNum_results(String num_results) {
        this.num_results = num_results;
    }

    public MyStoriesResult getResult() {
        return result;
    }

    public void setResult(MyStoriesResult result) {
        this.result= result;
    }

    public ArrayList<MyStoriesResult> getResultslist() {
        return results;
    }

    public void setResultslist(ArrayList<MyStoriesResult> resultslist) {
        this.results = resultslist;
    }
}
