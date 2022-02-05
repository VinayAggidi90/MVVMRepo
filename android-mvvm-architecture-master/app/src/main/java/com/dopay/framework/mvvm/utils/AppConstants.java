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

package com.dopay.framework.mvvm.utils;

/**
 * Created by vinay on 05/02/22.
 */

public final class AppConstants {

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "mindorks_mvvm.db";

    public static final long NULL_INDEX = -1L;

    public static final String PREF_NAME = "mindorks_pref";


    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static final String API_KEY = "AIzaSyCU04tfQlEYvdEpTW-XWBjV0oXEIb9v3qM";
//    public static final String CLIENT_ID = "610186820442-gihrqv58b703se8o9dbsu8j7fs2bp14l.apps.googleusercontent.com";
    public static final String CATEGORY_ID = "VIDEO_CATEGORY";
    public static final String ACTION_LAUNCH_PLAYER = "com.dopay.framework.mvvm.ACTION_PLAY";
    public static final String VIDEO_ID = "VIDEO_ID";
    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
