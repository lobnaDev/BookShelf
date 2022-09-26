package com.app.bookshelf.model;

import com.google.gson.annotations.SerializedName;

public class MyBuyLinks {
    @SerializedName("name")
    String name;
    @SerializedName("url")
    String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
