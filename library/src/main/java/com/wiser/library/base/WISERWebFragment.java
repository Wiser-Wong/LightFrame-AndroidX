package com.wiser.library.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;

import com.wiser.library.R;
import com.wiser.library.util.WISERApp;
import com.wiser.library.util.WISERWebChromeClient;

/**
 * @author Wiser
 * 
 *         WebView网页
 */
public abstract class WISERWebFragment<B extends IWISERBiz> extends WISERFragment<B> {

	private boolean			isHandleBack	= false;	// 是否处理返回

	private boolean			isHaveProgress	= false;	// 是否有进度条

	private WebView webView;

	private LinearLayout rootLayout;

	private ProgressBar progressView;

	protected abstract WISERBuilder buildWeb(WISERBuilder builder);

	protected abstract void initWebData(Bundle savedInstanceState);

	protected abstract WISERWebChromeClient setWebChromeClient();

	protected abstract WebViewClient setWebViewClient();

	protected abstract String loadUrl();

	@Override
    protected WISERBuilder build(WISERBuilder builder) {
		WISERBuilder wiserBuilder = buildWeb(builder);
		wiserBuilder.layoutView(createView());
		return wiserBuilder;
	}

	@Override
    protected void initData(Bundle savedInstanceState) {
		initWebData(savedInstanceState);
	}

	/**
	 * 创建布局
	 * 
	 * @return
	 */
	private View createView() {
		// 创建WebView
		createWebView();
		// 是否有进度条
		if (isHaveProgress) {
			// 创建父布局
			createLayout();
			// 创建ProgressBar进度条
			createProgressView();

			rootLayout.addView(progressView);

			rootLayout.addView(webView);

			return rootLayout;
		} else {
			return webView;
		}
	}

	/**
	 * 创建WebView rootLayout
	 *
	 * @return
	 */
	private void createLayout() {
		rootLayout = new LinearLayout(activity());
		rootLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		// 纵向
		rootLayout.setOrientation(LinearLayout.VERTICAL);
	}

	/**
	 * 创建ProgressBar
	 *
	 * @return
	 */
	private void createProgressView() {
		progressView = new ProgressBar(activity(), null, android.R.attr.progressBarStyleHorizontal);
		progressView.setIndeterminate(false);
		progressView.setMax(100);
		progressView.setProgressDrawable(ContextCompat.getDrawable(activity(), R.drawable.progress_line));
		progressView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, WISERApp.dip2px(2)));
	}

	// 创建WebView
	@SuppressLint({ "SetJavaScriptEnabled" }) private void createWebView() {
		webView = new WebView(activity());
		webView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		// 设置内核是原生的
		webView.getSettings().setUserAgentString(webView.getSettings().getUserAgentString() + " APP_Android");
		// 使用缓存WebSettings.LOAD_CACHE_ELSE_NETWORK（WebSettings.LOAD_NO_CACHE不是用缓存）
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		// 启用支持javascript
		webView.getSettings().setJavaScriptEnabled(true);
		// webView.addJavascriptInterface(new NativeClass(), "nativeClass");
		// 解决加载图片不显示
		webView.getSettings().setBlockNetworkImage(false);
		// 设置可以支持缩放
		webView.getSettings().setSupportZoom(true);
		// 防止页面加载不全或者空白页
		webView.getSettings().setDomStorageEnabled(true);
		// 设置出现缩放工具
		webView.getSettings().setBuiltInZoomControls(true);
		// 扩大比例的缩放
		webView.getSettings().setUseWideViewPort(true);
		// 设置支持多窗口
		webView.getSettings().setSupportMultipleWindows(true);
		// 允许读取本地文件
		webView.getSettings().setAllowFileAccess(true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			webView.getSettings().setAllowFileAccessFromFileURLs(true);
		}
		// 自适应屏幕
		webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webView.getSettings().setLoadWithOverviewMode(true);
		// 将图片调整到适合webview的大小
		webView.getSettings().setUseWideViewPort(false);
		// 支持通过JS打开新窗口
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		// 支持自动加载图片
		webView.getSettings().setLoadsImagesAutomatically(true);
		// 解决系统版本不同图片显示问题
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
		// 支持内容重新布局
		webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		// 取消缩放控制条
		webView.getSettings().setDisplayZoomControls(false);
		// 加载网络链接
		webView.loadUrl(loadUrl());

		webView.setWebChromeClient(new WISERWebChromeClient(activity()));

		webView.setWebChromeClient(setWebChromeClient());
		webView.setWebViewClient(setWebViewClient());

	}

	// 进度条显示隐藏
	public void isHideProgress(boolean isHide) {
		if (isHaveProgress) {
			if (isHide) {
				if (progressView.getVisibility() == View.VISIBLE) progressView.setVisibility(View.GONE);
			} else {
				if (progressView.getVisibility() == View.GONE) progressView.setVisibility(View.VISIBLE);
			}
		}
	}

	// 判断进度条进度显示 #(当子类WebActivity需要扩展WebChromeClient的时候
	// 如果需要加入默认进度条，那么需要在onProgressChanged（）方法中调用该方法)
	public void setWebProgress(int newProgress) {
		if (newProgress == 100) {
			isHideProgress(true);
		} else {
			isHideProgress(false);
			if (isHaveProgress) progressView.setProgress(newProgress);
		}
	}

	// WebView实例
	public WebView webView() {
		return webView;
	}

	/**
	 * 
	 * @param isHandleBack
	 *            是否处理返回
	 */
	public void isHandleBack(boolean isHandleBack) {
		this.isHandleBack = isHandleBack;
	}

	/**
	 * 
	 * @param isHaveProgress
	 *            是否有进度条
	 */
	public void isHaveProgress(boolean isHaveProgress) {
		this.isHaveProgress = isHaveProgress;
	}

	/**
	 * 返回处理
	 */
	private boolean backHandle() {
		if (isHandleBack) if (webView.canGoBack()) {
			webView.goBack();
			return true;
		} else {
			return false;
		}
		return false;
	}

	@Override
    public void onPause() {
		webView.onPause();
		webView.pauseTimers();
		super.onPause();
		if (activity().isFinishing()) {
			if (webView != null) {
				webView.destroy();
				webView = null;
			}
			rootLayout = null;
			progressView = null;
		}
	}

	@Override
    public void onResume() {
		webView.onResume();
		webView.resumeTimers();
		super.onResume();
	}

}
