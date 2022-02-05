package com.dopay.framework.mvvm.ui.videoplayer;

import com.dopay.framework.mvvm.data.DataManager;
import com.dopay.framework.mvvm.ui.base.BaseViewModel;
import com.dopay.framework.mvvm.utils.rx.SchedulerProvider;

public class PlayerViewModel extends BaseViewModel<PlayerNavigator> {


    public PlayerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
