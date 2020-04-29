package com.wiser.library.base;

import android.content.Intent;
import android.os.Bundle;

import com.wiser.library.helper.WISERHelper;
import com.wiser.library.loading.LoadingDialogFragment;

/**
 * @param <U>
 * @author Wiser
 * @version 版本
 */
@SuppressWarnings("unchecked")
public class WISERBiz<U> implements IWISERBiz {

	private U u;

	public U ui() {
		return u;
	}

	@Override public void initUi(Object object) {
		this.u = (U) object;
	}

	@Override public void initBiz(Intent intent) {

	}

	@Override public void initBiz(Bundle bundle) {

	}

	/**
	 * View 层是否存在
	 * 
	 * @return
	 */
	protected boolean isUi() {
		return u != null;
	}

	/**
	 * 显示loading
	 * 
	 * @param isClose
	 *            是否可以关闭弹窗
	 */
	protected void showLoading(boolean... isClose) {
		if (isClose.length > 0) {
			LoadingDialogFragment.showLoadingDialog(isClose[0]);
		} else {
			LoadingDialogFragment.showLoadingDialog(false);
		}
	}

	/**
	 * 隐藏loading
	 */
	protected void hideLoading() {
		LoadingDialogFragment loadingDialogFragment = WISERHelper.display().findFragment(LoadingDialogFragment.class.getName());
		if (loadingDialogFragment != null) loadingDialogFragment.dismiss();
	}

	/**
	 * 是否正在显示loading
	 */
	protected boolean isRunningLoading() {
		LoadingDialogFragment loadingDialogFragment = WISERHelper.display().findFragment(LoadingDialogFragment.class.getName());
		if (loadingDialogFragment != null) {
			return loadingDialogFragment.isShowing();
		}
		return false;
	}

	public void detach() {
		u = null;
		hideLoading();
	}
}
