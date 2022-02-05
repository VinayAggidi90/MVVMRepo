package com.dopay.framework.mvvm.di.module;

import com.dopay.framework.mvvm.ui.base.BaseDialog;

import dagger.Module;

/*
 * Created by vinay on 05/02/22.
 */

@Module
public class DialogModule {

    private BaseDialog dialog;

    public DialogModule(BaseDialog dialog){
        this.dialog = dialog;
    }


}
