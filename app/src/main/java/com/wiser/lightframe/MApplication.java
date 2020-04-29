package com.wiser.lightframe;

import android.app.Application;

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MHelper.newBind().setWiserBind(new MBind()).inject(this,BuildConfig.DEBUG);
    }
}
