package com.dopay.framework.mvvm.di.component;

import com.dopay.framework.mvvm.di.module.DialogModule;
import com.dopay.framework.mvvm.di.scope.DialogScope;

import dagger.Component;

/*
 * Created by vinay on 05/02/22.
 */

@DialogScope
@Component(modules = DialogModule.class, dependencies = AppComponent.class)
public interface DialogComponent {

   // void inject(RateUsDialog dialog);

}
