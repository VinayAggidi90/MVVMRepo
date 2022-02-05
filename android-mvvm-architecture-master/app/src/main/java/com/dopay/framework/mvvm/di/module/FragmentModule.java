package com.dopay.framework.mvvm.di.module;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dopay.framework.mvvm.ui.base.BaseFragment;

import dagger.Module;
import dagger.Provides;

/*
 * Created by vinay on 05/02/22.
 */

@Module
public class FragmentModule {

    private BaseFragment<?, ?> fragment;

    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(fragment.getActivity());
    }


}
