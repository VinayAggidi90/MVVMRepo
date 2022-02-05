package com.dopay.framework.mvvm.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoListResponsePojo {

    @SerializedName("items")
    @Expose
    private Items[] items;

    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;

    @SerializedName("prevPageToken")
    @Expose
    private String prevPageToken;

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public void setPrevPageToken(String prevPageToken) {
        this.prevPageToken = prevPageToken;
    }

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }


}
