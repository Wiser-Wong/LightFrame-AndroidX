package com.wiser.lightframe;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.wiser.library.adapter.WISERHolder;
import com.wiser.library.adapter.WISERRVAdapter;
import com.wiser.library.base.WISERActivity;

public class MAdapter extends WISERRVAdapter<String, MAdapter.MHolder> {

    public MAdapter(WISERActivity mWiserActivity) {
        super(mWiserActivity);
    }

    @Override
    public MHolder newViewHolder(ViewGroup viewGroup, int type) {
        return new MHolder(inflate(viewGroup,R.layout.item));
    }

    static class MHolder extends WISERHolder<String>{

        public MHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(String s, int position) {

        }
    }

}
