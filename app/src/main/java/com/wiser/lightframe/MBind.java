package com.wiser.lightframe;

import android.app.Application;

import com.wiser.library.base.IWISERBind;
import com.wiser.library.manager.WISERManage;

public class MBind implements IWISERBind {

    @Override
    public void initApplication(Application application) {
        System.out.println("初始化");
    }

    @Override
    public WISERManage getManage() {
        return new MManage();
    }
}
