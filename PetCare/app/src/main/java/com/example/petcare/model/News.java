package com.example.petcare.model;

import android.net.Uri;

public class News {
      private String imageNews;

    private String titleNews;
    private String descriptionNews;

    public News() {
    }

    public News(String imageNews, String titleNews, String descriptionNews) {
        this.imageNews = imageNews;
        this.titleNews = titleNews;
        this.descriptionNews = descriptionNews;
    }

    public String getImageNews() {
        return imageNews;
    }

    public void setImageNews(String imageNews) {
        this.imageNews = imageNews;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public String getDescriptionNews() {
        return descriptionNews;
    }

    public void setDescriptionNews(String descriptionNews) {
        this.descriptionNews = descriptionNews;
    }
}
