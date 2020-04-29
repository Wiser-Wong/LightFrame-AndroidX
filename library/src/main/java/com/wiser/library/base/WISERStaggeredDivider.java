package com.wiser.library.base;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wiser.library.util.WISERApp;

/**
 * @author Wiser
 * @version 版本
 */
public class WISERStaggeredDivider extends RecyclerView.ItemDecoration {

	private int	left;

	private int	top;

	private int	right;

	private int	bottom;

	public WISERStaggeredDivider(int left, int top, int right, int bottom) {
		this.left = WISERApp.dip2px(left);
		this.top = WISERApp.dip2px(top);
		this.right = WISERApp.dip2px(right);
		this.bottom = WISERApp.dip2px(bottom);
	}

	@Override public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
		outRect.set(left, top, right, bottom);
	}

}
