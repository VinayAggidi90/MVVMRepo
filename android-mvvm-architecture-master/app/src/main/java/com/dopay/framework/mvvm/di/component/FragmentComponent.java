package com.dopay.framework.mvvm.di.component;

import com.dopay.framework.mvvm.di.module.FragmentModule;
import com.dopay.framework.mvvm.di.scope.FragmentScope;

import dagger.Component;

/*
 * Created by vinay on 05/02/22.
 */

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
}
