package com.airlocksoftware.hackernews.fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.airlocksoftware.hackernews.R;
import com.airlocksoftware.hackernews.activity.MainActivity;
import com.airlocksoftware.holo.actionbar.ActionBarButton;
import com.airlocksoftware.holo.actionbar.ActionBarButton.Priority;
import com.airlocksoftware.holo.actionbar.interfaces.ActionBarClient;
import com.airlocksoftware.holo.actionbar.interfaces.ActionBarController;
import com.airlocksoftware.holo.webview.WebViewPagerCompat;

public class translateFragment extends Fragment implements ActionBarClient {

	private String mUrl;

	ViewGroup mRootView;
	private WebView mWebView, myBrowser;
	private ProgressBar mProgress;
	private ActionBarButton mRefreshButton;
	private ActionBarButton mBrowserButton, mTranslate;

	// Listeners
	private OnClickListener mRefreshListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mRefreshButton.showProgress(true);
			mWebView.reload();
		}
	};

	private OnClickListener mBrowserListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (StringUtils.isNotBlank(mUrl)) {
				Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(mUrl));
				getActivity().startActivity(intent);
			}
		}
	};
	
	private View.OnClickListener mTranslateListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			
			translatefunction();
			
			/*String fixcontentfront="http://www.worldlingo.com/SYls3jUpdI3LW6nuue6iRuAc_bgfnn7hv3roROoWsdI0-/banner?wl_url=";
			String fixcontenttail="&wl_srclang=EN&wl_trglang=ZH_TW";
			//String content=mStory.url;
			String out;
			try {
				out = URLEncoder.encode( mUrl, "ASCII" ).toString();
				//showdialog(out);
				Uri uri=Uri.parse(fixcontentfront+out+fixcontenttail);
				Intent intent = new Intent(Intent.ACTION_VIEW).setData(uri);
				getActivity().startActivity(intent);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/	
			
		}
	};
	public void translatefunction(){
		this.getActivity().setContentView(R.layout.translatetest);
		String fixcontentfront="http://www.worldlingo.com/SYls3jUpdI3LW6nuue6iRuAc_bgfnn7hv3roROoWsdI0-/banner?wl_url=";
		String fixcontenttail="&wl_srclang=EN&wl_trglang=ZH_TW";
		String out;
        //WebView myBrowser=(WebView)getActivity().findViewById(R.id.webview1);  
  
        WebSettings websettings = myBrowser.getSettings();  
        websettings.setSupportZoom(true);  
        websettings.setBuiltInZoomControls(true);   
        websettings.setJavaScriptEnabled(true);  
        
        myBrowser.setWebViewClient(new WebViewClient());  
        try {
			out = URLEncoder.encode( mUrl, "ASCII" ).toString();
			String url=fixcontentfront+out+fixcontenttail;
	        myBrowser.loadUrl(url); 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		
	}

	// Constants
	public static final String URL = WebFragment.class.getSimpleName() + ".url";
	public static final String TAG = WebFragment.class.getSimpleName();

	// CONSTRUCTORS
	/*public WebFragment() {
		// default no-arg constructor
	}*/

	@Override
	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		Bundle args = getArguments();
		if (args != null) {
			mUrl = args.getString(URL);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootView = (ViewGroup) inflater.inflate(R.layout.frg_webview, container, false);

		findViews(inflater, mRootView);
		setupWebSettings();
		setupScrollingOptions();

		mWebView.setWebChromeClient(mWebChromeClient);
		mWebView.setWebViewClient(mWebViewClient);

		if (mUrl != null) {
			mWebView.loadUrl(mUrl);
		}

		return mRootView;
	}

	WebChromeClient mWebChromeClient = new WebChromeClient() {
		public void onProgressChanged(WebView view, int progress) {
			super.onProgressChanged(view, progress);
			mProgress.setProgress(progress);

			// hide the progress bar if the loading is complete
			if (progress == 100) {
				mProgress.setVisibility(View.GONE);
				if (mRefreshButton != null) mRefreshButton.showProgress(false);
			} else {
				mProgress.setVisibility(View.VISIBLE);
			}
		}
	};

	WebViewClient mWebViewClient = new WebViewClient() {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	};

	@SuppressLint("NewApi")
	private void setupScrollingOptions() {
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.setScrollbarFadingEnabled(true);
		if (android.os.Build.VERSION.SDK_INT >= 9) mWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void setupWebSettings() {
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setSupportZoom(true);
		settings.setBuiltInZoomControls(true);
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);
		settings.setRenderPriority(RenderPriority.LOW); // helps fix performance issues in CommentsActivity
	}

	private void findViews(LayoutInflater inflater, ViewGroup root) {
		// have to add webview programatically with an ApplicationContext
		// as per http://stackoverflow.com/questions/3130654/memory-leak-in-webview
		mWebView = new WebViewPagerCompat(inflater.getContext());
		root.addView(mWebView);
		mProgress = (ProgressBar) root.findViewById(R.id.progressbar);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			mUrl = savedInstanceState.getString(URL);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString(URL, mUrl);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mRootView.removeAllViews();
		mWebView.destroy();
	}

	@Override
	public void setupActionBar(Context context, ActionBarController ab) {
		// refresh button
		if (mRefreshButton == null) {
			mRefreshButton = new ActionBarButton(context);
			mRefreshButton.text(context.getString(R.string.refresh_webpage))
										.icon(R.drawable.ic_action_refresh)
										.priority(Priority.HIGH)
										.onClick(mRefreshListener);
		}
		ab.addButton(mRefreshButton);

		// open in browser button
		if (mBrowserButton == null) {
			mBrowserButton = new ActionBarButton(context);
			mBrowserButton.text(context.getString(R.string.open_in_browser))
										.icon(R.drawable.ic_action_web)
										.onClick(mBrowserListener)
										.id(R.id.btn_browser)
										.priority(Priority.LOW);
		}
		ab.addButton(mBrowserButton);
		if (mTranslate == null) {
			mTranslate = new ActionBarButton(context);
			mTranslate.text(context.getString(R.string.translate))
										.icon(R.drawable.translate_button)
										.priority(Priority.HIGH)
										.onClick(mTranslateListener);
		}
		ab.addButton(mTranslate);
	}

	@Override
	public void cleanupActionBar(Context context, ActionBarController ab) {
		ab.removeButton(mRefreshButton);
		ab.removeButton(mBrowserButton);
		ab.removeButton(mTranslate);
	}

	/** As part of a tablet layout, load a new URL. **/
	public void loadNewUrl(String url) {
		mUrl = url;
		mWebView.clearView();
		mWebView.loadUrl(mUrl);
	}

	/** Set the initial url to be loaded. **/
	public void setUrl(String url) {
		mUrl = url;
	}
	

}