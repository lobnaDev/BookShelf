package com.app.bookshelf.model;

import com.google.gson.annotations.SerializedName;

public class MyIsbns {
    @SerializedName("isbn10")
    String isbn10;
    @SerializedName("isbn13")
    String isbn13;

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
}
