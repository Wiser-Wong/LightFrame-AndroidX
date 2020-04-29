package com.wiser.lightframe;

import com.wiser.library.base.WISERBiz;

import java.util.ArrayList;
import java.util.List;

public class MainBiz extends WISERBiz<MainActivity> {

    public List<String> getData(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("第" + i +"条");
        }
        return list;
    }

}
