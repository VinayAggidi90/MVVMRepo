package com.dopay.framework.mvvm.data.model.api;

import java.util.List;

public class VideoCategoryType {

    public List<VideoType> getVideoTypeList() {
        return videoTypeList;
    }

    public void setVideoTypeList(List<VideoType> videoTypeList) {
        this.videoTypeList = videoTypeList;
    }

    private List<VideoType> videoTypeList;


    public static class VideoType{

        private int icontype;
        private String videotype;
        private int videoId;

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public int getIcontype() {
            return icontype;
        }

        public void setIcontype(int icontype) {
            this.icontype = icontype;
        }

        public String getVideotype() {
            return videotype;
        }

        public void setVideotype(String videotype) {
            this.videotype = videotype;
        }

    }


}
