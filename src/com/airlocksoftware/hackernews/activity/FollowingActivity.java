// by jjyung 102522092
package com.airlocksoftware.hackernews.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airlocksoftware.hackernews.R;

public class FollowingActivity extends SlideoutMenuActivity {
	
	ListView mList;
	
	String mFollowing[];
	String SDpath = Environment.getExternalStorageDirectory().getPath();
	String filename = "FollowSavedFile.txt";
	
	File mDir = new File(SDpath + "/HackerNews");
	File mFile = new File(SDpath + "/HackerNews/" + filename);
	
	@Override
	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		
		setContentView(R.layout.act_following);
		getActionBarView().getController()
											.setTitleText(getString(R.string.following));
		setActiveMenuItem(R.id.following_button);
		if(checkFile())
			loadFollowing();
		else
			nullFollowing();
		listSetting();
	}
	
	private boolean checkFile(){
		return (mDir.exists() && mFile.exists());
	}
	
	private void loadFollowing(){
	    StringBuilder sb = new StringBuilder();
	    try {
	        FileInputStream fin = new FileInputStream(mFile);
	        byte[] data = new byte[fin.available()];
	        while (fin.read(data) != -1) {
	            sb.append(new String(data));
	        }
	        fin.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    mFollowing = sb.toString().split("\n");
	}

	private void listSetting(){
		// view
		mList = (ListView) findViewById(R.id.followinglist);
		// adapter
		mList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mFollowing));
		// listener
		mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
		    public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)   {
				FollowingActivity activity = FollowingActivity.this;
				UserActivity.startUserActivity(activity, mFollowing[position]);
		    }
		});
	}
	
	private void nullFollowing(){
		mFollowing = new String[] {};
	}
	/* testing data
	 * 
	private static final String[] mStrings = new String[] {
		 "jjyung","abc","bbb","bcd","123","1234","test001","test002","test003","test004"
		 };
	*/
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	
}
