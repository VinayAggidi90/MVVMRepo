package com.dopay.framework.mvvm.di.component;

import com.dopay.framework.mvvm.ui.videolisting.VideoListing;
import com.dopay.framework.mvvm.di.module.ActivityModule;
import com.dopay.framework.mvvm.di.scope.ActivityScope;
import com.dopay.framework.mvvm.ui.videoplayer.PlayerActivity;

import dagger.Component;

/*
 * Created by vinay on 05/02/22.
 */

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {


    void inject(VideoListing videoListing);
}
