package com.wiser.library.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Wiser
 * @version 版本
 */
public abstract class WISERHolder<T> extends RecyclerView.ViewHolder {

	private WISERRVAdapter	adapter;

	private Context			context;

	public WISERHolder(@NonNull View itemView) {
		super(itemView);
	}

	public void setAdapter(WISERRVAdapter adapter) {
		this.adapter = adapter;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public WISERRVAdapter adapter() {
		return adapter;
	}

	public abstract void bindData(T t, int position);

}
