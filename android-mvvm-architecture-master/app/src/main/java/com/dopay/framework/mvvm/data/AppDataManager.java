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

package com.dopay.framework.mvvm.data;

import android.content.Context;

import com.dopay.framework.mvvm.data.local.prefs.PreferencesHelper;
import com.dopay.framework.mvvm.data.model.api.VideoListResponsePojo;
import com.dopay.framework.mvvm.data.remote.ApiHelper;
import com.google.gson.Gson;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by vinay on 05/02/22.
 */
@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }


    @Override
    public Single<VideoListResponsePojo> getYoutubeVideListing(Map<String,String> query) {
        return mApiHelper.getYoutubeVideListing(query);
    }

    @Override
    public void writeAppStringPrefs(String key, String value) {
        mPreferencesHelper.writeAppStringPrefs(key,value);
    }

    @Override
    public String getAppStringPrefs(String key) {
        return mPreferencesHelper.getAppStringPrefs(key);
    }
}
