package com.dopay.framework.mvvm.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snippet {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose String description;
    @SerializedName("thumbnails")
    @Expose
    private ThumbnailsIcon thumbnailsIcon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThumbnailsIcon getThumbnailsIcon() {
        return thumbnailsIcon;
    }

    public void setThumbnailsIcon(ThumbnailsIcon thumbnailsIcon) {
        this.thumbnailsIcon = thumbnailsIcon;
    }
}
