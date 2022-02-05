package com.dopay.framework.mvvm.di.module;


import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.dopay.framework.mvvm.data.DataManager;
import com.dopay.framework.mvvm.ui.base.BaseActivity;
import com.dopay.framework.mvvm.ui.videolisting.VideoListingViewModel;
import com.dopay.framework.mvvm.ui.videoplayer.PlayerViewModel;
import com.dopay.framework.mvvm.utils.rx.SchedulerProvider;
import com.dopay.framework.mvvm.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/*
 * Created by vinay on 05/02/22.
 */

@Module
public class ActivityModule {
    private BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }


    @Provides
    VideoListingViewModel provideSplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<VideoListingViewModel> supplier = () -> new VideoListingViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<VideoListingViewModel> factory = new ViewModelProviderFactory<>(VideoListingViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(VideoListingViewModel.class);
    }

}
