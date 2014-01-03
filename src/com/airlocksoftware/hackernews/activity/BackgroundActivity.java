// by jjyung 102522092
package com.airlocksoftware.hackernews.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.airlocksoftware.hackernews.R;
import com.airlocksoftware.hackernews.data.UserPrefs;
import com.airlocksoftware.hackernews.data.UserPrefs.Theme;
import com.airlocksoftware.hackernews.interfaces.RestartableActivity;

public class BackgroundActivity extends SlideoutMenuActivity {
	
	private UserPrefs mUserData;
	
	private View mRedButton;
	private View mPinkButton;
	private View mOrangeButton;
	private View mYellowButton;
	private View mGreenButton;
	private View mRoyalblueButton;
	private View mBlueButton;
	private View mPurpleButton;
	private View mBlackButton;
	private View mWhiteButton;
	
	
	RestartableActivity mRestart;
	
	@Override
	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		mUserData = new UserPrefs(this);
		mRestart = this;
		setContentView(R.layout.act_background);

		getActionBarView().getController()
											.setTitleText(getString(R.string.background));
		removeMenuCheckState();
		findAndBindViews();
	}
	
	
	private OnClickListener themeListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			switch(v.getId()){
			case R.id.btn_red:
				mUserData.setTheme(Theme.RED);
				break;
			case R.id.btn_pink:
				mUserData.setTheme(Theme.PINK);
				break;
			case R.id.btn_orange:
				mUserData.setTheme(Theme.ORANGE);
				break;
			case R.id.btn_yellow:
				mUserData.setTheme(Theme.YELLOW);
				break;
			case R.id.btn_green:
				mUserData.setTheme(Theme.GREEN);
				break;
			case R.id.btn_royalblue:
				mUserData.setTheme(Theme.ROYALBLUE);
				break;
			case R.id.btn_blue:
				mUserData.setTheme(Theme.BLUE);
				break;
			case R.id.btn_purple:
				mUserData.setTheme(Theme.PURPLE);
				break;
			case R.id.btn_white:
				mUserData.setTheme(Theme.LIGHT);
				break;
			case R.id.btn_black:
				mUserData.setTheme(Theme.DARK);
				break;
			}
			
			/*
			if (v.getId() == R.id.btn_white) mUserData.setTheme(Theme.LIGHT);
			else if (v.getId() == R.id.btn_black) mUserData.setTheme(Theme.DARK);
			*/
			
			mRestart.restartActivity();
		}
	};
	
	public void findAndBindViews(){
		mRedButton = findViewById(R.id.btn_red);
		mPinkButton = findViewById(R.id.btn_pink);
		mOrangeButton = findViewById(R.id.btn_orange);
		mYellowButton = findViewById(R.id.btn_yellow);
		mGreenButton = findViewById(R.id.btn_green);
		mRoyalblueButton = findViewById(R.id.btn_royalblue);
		mBlueButton = findViewById(R.id.btn_blue);
		mPurpleButton = findViewById(R.id.btn_purple);
		mBlackButton = findViewById(R.id.btn_black);
		mWhiteButton = findViewById(R.id.btn_white);
		
		mRedButton.setOnClickListener(themeListener);
		mPinkButton.setOnClickListener(themeListener);
		mOrangeButton.setOnClickListener(themeListener);
		mYellowButton.setOnClickListener(themeListener);
		mGreenButton.setOnClickListener(themeListener);
		mRoyalblueButton.setOnClickListener(themeListener);
		mBlueButton.setOnClickListener(themeListener);
		mPurpleButton.setOnClickListener(themeListener);
		mBlackButton.setOnClickListener(themeListener);
		mWhiteButton.setOnClickListener(themeListener);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
}
