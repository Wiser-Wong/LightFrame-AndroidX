package com.wiser.library.model;

import androidx.annotation.LayoutRes;

import com.wiser.library.adapter.WISERRVAdapter;

/**
 * @author Wiser
 * @version 版本
 */
public class WISERFooterModel {

	@LayoutRes public int							footerLayoutId;

	public WISERRVAdapter.OnFooterCustomListener	onFooterCustomListener;

}
