//by HaoPingWang 102522040

package com.airlocksoftware.hackernews.activity;

import android.os.Bundle;
import com.airlocksoftware.hackernews.R;
import com.airlocksoftware.hackernews.interfaces.RestartableActivity;

public class BookmarkActivity extends SlideoutMenuActivity {
	
	RestartableActivity mRestart;
	
	@Override
	public void onCreate(Bundle savedState){
		super.onCreate(savedState);
		mRestart = this;
		setContentView(R.layout.act_bookmark);
		
		getActionBarView().getController().setTitleText(getString(R.string.bookmark));
		setActiveMenuItem(R.id.bookmark_button);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
	}
}