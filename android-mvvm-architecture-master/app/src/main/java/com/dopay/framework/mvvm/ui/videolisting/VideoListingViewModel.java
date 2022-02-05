/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.dopay.framework.mvvm.ui.videolisting;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.dopay.framework.mvvm.data.model.api.Items;
import com.dopay.framework.mvvm.data.model.api.VideoListResponsePojo;
import com.dopay.framework.mvvm.utils.AppConstants;
import com.dopay.framework.mvvm.utils.rx.SchedulerProvider;
import com.dopay.framework.mvvm.data.DataManager;
import com.dopay.framework.mvvm.ui.base.BaseViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amitshekhar on 08/07/17.
 */

public class VideoListingViewModel extends BaseViewModel<VideoListingNavigator> {


    private Map<String,String> queryParams = new HashMap<>();
    private MutableLiveData<VideoListResponsePojo> videoListResponsePojoMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<VideoListResponsePojo> getVideoListResponsePojoMutableLiveData() {
        return videoListResponsePojoMutableLiveData;
    }


    public VideoListingViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getVideoListing(String pageToken,String category) {

        getNavigator().startLoading();
        queryParams.put("part","snippet");
        queryParams.put("chart","mostPopular");
        queryParams.put("maxResults","10");
        queryParams.put("pageToken",pageToken);
        queryParams.put("regionCode","US");
        queryParams.put("key", AppConstants.API_KEY);
        queryParams.put("type","video");
        queryParams.put("videoCategoryId",category);

        getCompositeDisposable().add(getDataManager().getYoutubeVideListing(queryParams).subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().ui()).subscribe(videoListResponsePojo -> {

            Log.d("Response", ": "+videoListResponsePojo.getItems());
            videoListResponsePojoMutableLiveData.setValue(videoListResponsePojo);
            getNavigator().stopLoading();

        },throwable -> {
            getNavigator().stopLoading();
            getNavigator().showErrorMessage(throwable.getMessage());
            Log.d("TAG", ": "+throwable.getMessage());
        }));

    }

}
