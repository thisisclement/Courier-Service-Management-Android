package com.example.jobs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class sickActivity extends Activity {
	private WebView webView;
	private ProgressBar prgrsBar;
	
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sickappweb);		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		webView = (WebView) findViewById(R.id.sickappWeb);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new MyBrowser());
		webView.loadUrl("http://scet.sg/courier/sickappAndroid.php");
		
		prgrsBar = (ProgressBar)findViewById(R.id.progressBarWeb);
		
	}
	
	private class MyBrowser extends WebViewClient {
	      @Override
	      public boolean shouldOverrideUrlLoading(WebView view, String url) {
//	         view.loadUrl(url);	        
	         if (url.equals("http://scet.sg/courier/sickappAndroid.php")){
	        	 Toast.makeText(sickActivity.this, "Sick leave successfully applied!",
		        		 Toast.LENGTH_LONG).show();
	        	 finish(); //end current activity and go back to previous
	         }
	         return true;
	      }
	      
	      @Override
	      public void onPageStarted(WebView view, String url, Bitmap favicon) {
	          // TODO Auto-generated method stub
	          super.onPageStarted(view, url, favicon);

	          prgrsBar.setVisibility(View.VISIBLE);
	      }

	       @Override
	      public void onPageFinished(WebView view, String url) {
	          // TODO Auto-generated method stub
	          super.onPageFinished(view, url);

	          prgrsBar.setVisibility(View.GONE);
	      }
	   }
	
	@Override
	 public boolean onOptionsItemSelected(MenuItem menuItem) {
	 	switch (menuItem.getItemId()) {
	     case android.R.id.home:
	       // ProjectsActivity is my 'home' activity
	    	 this.finish();
	         return true;
	 	}
	   return (super.onOptionsItemSelected(menuItem));
	 }

}
