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

package com.dopay.framework.mvvm.data.remote;

import com.dopay.framework.mvvm.data.model.api.VideoListResponsePojo;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by vinay on 05/02/22.
 */

@Singleton
public class AppApiHelper implements ApiHelper {


    @Inject
    public AppApiHelper() {
    }


    @Override
    public Single<VideoListResponsePojo> getYoutubeVideListing(Map<String,String> value) {
        return Rx2AndroidNetworking.get(ApiEndPoint.BASEURL)
                .addHeaders("Accept","application/json")
                .addQueryParameter(value)
                .build()
                .getObjectSingle(VideoListResponsePojo.class);
    }

}
