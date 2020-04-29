package com.wiser.library.pager;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * @author Wiser
 * pager动画
 */
public class NonPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        page.setScaleX(0.999f);//hack
    }

    public static final ViewPager.PageTransformer INSTANCE = new NonPageTransformer();
}
