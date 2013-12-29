//by HaoPingWang 102522040

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

public class BookmarkActivity extends SlideoutMenuActivity {
	
	ListView mList;
	
	String mBookmarkTitle[];
	String mBookmarkUUID[];
	
	String SDpath = Environment.getExternalStorageDirectory().getPath();
	String filename = "BookmarkSavedFile.txt";
	File mDir = new File(SDpath + "/HackerNews");
	File mFile = new File(SDpath + "/HackerNews/" + filename);
	
	@Override
	public void onCreate(Bundle savedState){
		super.onCreate(savedState);

		setContentView(R.layout.act_bookmark);
		
		getActionBarView().getController().setTitleText(getString(R.string.bookmark));
		setActiveMenuItem(R.id.bookmark_button);
		
		if(checkFile())
			loadBookmark();
		else
			nullBookmark();
		listSetting();
	}
	
	private boolean checkFile(){
		return (mDir.exists() && mFile.exists());
	}
	
	private void loadBookmark(){
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
	    
	    int cutpointLeft,cutpointRight;
	    String fileString = sb.toString();
	    String tuple[] = sb.toString().split("\n");
	    String title[] = new String[tuple.length] ;
	    String uuid[] = new String[tuple.length] ;
	    
	    for(int i=0;i<tuple.length;i++){
	    	cutpointLeft = tuple[i].indexOf(",");
	    	cutpointRight = tuple[i].lastIndexOf(",");
	    	title[i] = tuple[i].substring(0, cutpointLeft);
	    	uuid[i] = tuple[i].substring(cutpointRight+1, tuple[i].length());
	    }
	    
	    mBookmarkTitle = title;
	    mBookmarkUUID = uuid;
	    
	}
	
	private void listSetting(){
		// view
		mList = (ListView) findViewById(R.id.bookmarklist);
		// adapter
		mList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mBookmarkTitle));
		// listener
		mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
		    public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)   {
				BookmarkActivity activity = BookmarkActivity.this;
				UserActivity.startUserActivity(activity, mBookmarkUUID[position]);
		    }
		});
	}
	
	private void nullBookmark(){
		mBookmarkTitle = new String[] {};
		mBookmarkUUID = new String[] {};
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
	}
}