// by jjyung 102522092
package com.airlocksoftware.hackernews.activity;

import android.os.Bundle;

import com.airlocksoftware.hackernews.R;
import com.airlocksoftware.hackernews.interfaces.RestartableActivity;

public class FollowingActivity extends SlideoutMenuActivity {
	
	RestartableActivity mRestart;
	
	@Override
	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		mRestart = this;
		setContentView(R.layout.act_following);

		getActionBarView().getController()
											.setTitleText(getString(R.string.following));
		setActiveMenuItem(R.id.following_button);
	}
	
	
	
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
}
