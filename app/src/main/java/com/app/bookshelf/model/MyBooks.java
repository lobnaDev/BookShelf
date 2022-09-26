package com.app.bookshelf.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Array;
import java.util.ArrayList;

public class MyBooks {
    @SerializedName("rank")
    int rank;
    @SerializedName("rank_last_week")
    int rank_last_week;
    @SerializedName("weeks_on_list")
    int weeks_on_list;
    @SerializedName("asterisk")
    int asterisk;
    @SerializedName("dagger")
    int dagger;
    @SerializedName("primary_isbn10")
    String primary_isbn10;
    @SerializedName("primary_isbn13")
    String primary_isbn13;
    @SerializedName("publisher")
    String publisher;
    @SerializedName("description")
    String description;
    @SerializedName("price")
    String price;
    @SerializedName("title")
    String title;
    @SerializedName("author")
    String author;
    @SerializedName("contributor")
    String contributor;
    @SerializedName("contributor_note")
    String contributor_note;
    @SerializedName("book_image")
    String book_image;
    @SerializedName("book_image_width")
    int book_width;
    @SerializedName("book_image_height")
    int book_height;
    @SerializedName("amazon_product_url")
    String amazon_product_url;
    @SerializedName("age_group")
    String age_group;
    @SerializedName("book_review_link")
    String book_review_link;
    @SerializedName("first_chapter_link")
    String first_chapter_link;
    @SerializedName("sunday_review_link")
    String sunday_review_link;
    @SerializedName("article_chapter_link")
    String article_chapter_link;
    @SerializedName("book_uri")
    String book_url;
    @SerializedName("isbns")
    ArrayList<MyIsbns> isbns;
    @SerializedName("buy_links")
    ArrayList<MyBuyLinks> buy_links;
    String list_name;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank_last_week() {
        return rank_last_week;
    }

    public void setRank_last_week(int rank_last_week) {
        this.rank_last_week = rank_last_week;
    }

    public int getWeeks_on_list() {
        return weeks_on_list;
    }

    public void setWeeks_on_list(int weeks_on_list) {
        this.weeks_on_list = weeks_on_list;
    }

    public int getAsterisk() {
        return asterisk;
    }

    public void setAsterisk(int asterisk) {
        this.asterisk = asterisk;
    }

    public int getDagger() {
        return dagger;
    }

    public void setDagger(int dagger) {
        this.dagger = dagger;
    }

    public String getPrimary_isbn10() {
        return primary_isbn10;
    }

    public void setPrimary_isbn10(String primary_isbn10) {
        this.primary_isbn10 = primary_isbn10;
    }

    public String getPrimary_isbn13() {
        return primary_isbn13;
    }

    public void setPrimary_isbn13(String primary_isbn13) {
        this.primary_isbn13 = primary_isbn13;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getContributor_note() {
        return contributor_note;
    }

    public void setContributor_note(String contributor_note) {
        this.contributor_note = contributor_note;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public int getBook_width() {
        return book_width;
    }

    public void setBook_width(int book_width) {
        this.book_width = book_width;
    }

    public int getBook_height() {
        return book_height;
    }

    public void setBook_height(int book_height) {
        this.book_height = book_height;
    }

    public String getAmazon_product_url() {
        return amazon_product_url;
    }

    public void setAmazon_product_url(String amazon_product_url) {
        this.amazon_product_url = amazon_product_url;
    }

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    public String getBook_review_link() {
        return book_review_link;
    }

    public void setBook_review_link(String book_review_link) {
        this.book_review_link = book_review_link;
    }

    public String getFirst_chapter_link() {
        return first_chapter_link;
    }

    public void setFirst_chapter_link(String first_chapter_link) {
        this.first_chapter_link = first_chapter_link;
    }

    public String getSunday_review_link() {
        return sunday_review_link;
    }

    public void setSunday_review_link(String sunday_review_link) {
        this.sunday_review_link = sunday_review_link;
    }

    public String getArticle_chapter_link() {
        return article_chapter_link;
    }

    public void setArticle_chapter_link(String article_chapter_link) {
        this.article_chapter_link = article_chapter_link;
    }

    public String getBook_url() {
        return book_url;
    }

    public void setBook_url(String book_url) {
        this.book_url = book_url;
    }

    public ArrayList<MyIsbns> getIsbns() {
        return isbns;
    }

    public void setIsbns(ArrayList<MyIsbns> isbns) {
        this.isbns = isbns;
    }

    public ArrayList<MyBuyLinks> getBuy_links() {
        return buy_links;
    }

    public void setBuy_links(ArrayList<MyBuyLinks> buy_links) {
        this.buy_links = buy_links;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }
}
