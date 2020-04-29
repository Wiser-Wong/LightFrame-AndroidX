package com.wiser.library.base;

import android.app.Application;

import com.wiser.library.manager.WISERManage;

/**
 * @author Wiser
 * @version 版本
 */
public interface IWISERBind {

	/**
	 * 初始化Application
	 *
	 * @param application
	 *            参数
	 * @return 返回值 返回值
	 */
	void initApplication(Application application);

	/**
	 * 获取配置管理器
	 *
	 * @return 返回值
	 */
	WISERManage getManage();

	/**
	 * 默认方法
	 */
	IWISERBind IWISER_BIND = new IWISERBind() {

		@Override
		public void initApplication(Application application) {

		}

		/**
		 * @return 返回值
		 */
		@Override public WISERManage getManage() {
			return new WISERManage();
		}

	};
}
