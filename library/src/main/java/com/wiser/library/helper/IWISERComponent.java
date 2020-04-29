package com.wiser.library.helper;

import com.wiser.library.manager.WISERManage;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface IWISERComponent {

    void inject(WISERManage manage);

}
