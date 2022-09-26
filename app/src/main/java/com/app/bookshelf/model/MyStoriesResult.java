package com.app.bookshelf.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lobna Babker on 7/20/2022.
 */
public class MyStoriesResult {
    @SerializedName("section")
    String section;
    @SerializedName("subsection")
    String subsection;
    @SerializedName("title")
    String title;
    @SerializedName("abstract")
    String abstract_;
    @SerializedName("url")
    String url;
    @SerializedName("uri")
    String uri;
    @SerializedName("byline")
    String byline;
    @SerializedName("item_type")
    String item_type;
    @SerializedName("updated_date")
    String updated_date;
    @SerializedName("created_date")
    String created_date;
    @SerializedName("published_date")
    String published_date;
    @SerializedName("material_type_facet")
    String material_type_facet;
    @SerializedName("kicker")
    String kicker;
    ArrayList<String> des_facet;
    ArrayList<String> org_facet;
    ArrayList<String> per_facet;
    ArrayList<String> geo_facet;
    ArrayList<multimedia> multimedia;
     String media;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract_() {
        return abstract_;
    }

    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getMaterial_type_facet() {
        return material_type_facet;
    }

    public void setMaterial_type_facet(String material_type_facet) {
        this.material_type_facet = material_type_facet;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public ArrayList<String> getDes_facet() {
        return des_facet;
    }

    public void setDes_facet(ArrayList<String> des_facet) {
        this.des_facet = des_facet;
    }

    public ArrayList<String> getOrg_facet() {
        return org_facet;
    }

    public void setOrg_facet(ArrayList<String> org_facet) {
        this.org_facet = org_facet;
    }

    public ArrayList<String> getPer_facet() {
        return per_facet;
    }

    public void setPer_facet(ArrayList<String> per_facet) {
        this.per_facet = per_facet;
    }

    public ArrayList<String> getGeo_facet() {
        return geo_facet;
    }

    public void setGeo_facet(ArrayList<String> geo_facet) {
        this.geo_facet = geo_facet;
    }

    public ArrayList<multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(ArrayList<multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}

