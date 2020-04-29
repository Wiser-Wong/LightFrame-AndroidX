package com.wiser.lightframe;

import android.content.Intent;
import android.graphics.Color;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wiser.library.base.WISERActivity;
import com.wiser.library.base.WISERBuilder;

public class MainActivity extends WISERActivity<MainBiz> {

    @Override
    protected WISERBuilder build(WISERBuilder builder) {
        builder.layoutId(R.layout.activity_main);
        builder.tintStateBarColor(Color.RED);
        builder.recycleView().recycleViewId(R.id.rlv);
        builder.recycleView().recycleViewLinearManager(LinearLayoutManager.VERTICAL,null);
        builder.recycleView().recycleAdapter(new MAdapter(this));
        builder.isRootLayoutRefresh(true,true);
        builder.recycleView().isFooter(true);
        return builder;
    }

    @Override
    protected void initData(Intent intent) {
        setItems(biz().getData());
    }
}
