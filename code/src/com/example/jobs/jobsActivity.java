package com.example.jobs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class jobsActivity extends Activity {
//	List<jobObject> myList = com.example.jobs.MainActivity.getJobsList();
	String jobId;
	String jobName;
	String jobAddress;
	String jobMobile;
	String jobPostal;
	String jobUnitNo;
	String jobType;
	
	TextView tvType;
	
	 public void onCreate(Bundle icicle) {
		    super.onCreate(icicle);
	        setContentView(R.layout.listdetail);
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        
	        Intent i = getIntent();
	        jobId = i.getStringExtra("jobId");
	        jobName = i.getStringExtra("jobName");
	        jobAddress = i.getStringExtra("jobAddress");
	        jobMobile = i.getStringExtra("jobMobile");
	        jobPostal = i.getStringExtra("jobPostal");
	        jobUnitNo = i.getStringExtra("jobUnitNo");
	        jobType = i.getStringExtra("jobType");
	        
	        TextView tvName = (TextView)findViewById(R.id.name);
       	    TextView tvAdd = (TextView)findViewById(R.id.address);
       	    TextView tvMobile = (TextView)findViewById(R.id.mobile);
       	    TextView tvPostal = (TextView)findViewById(R.id.postalCode);
       	    tvType = (TextView)findViewById(R.id.jobType);

       	    tvName.setText("" + jobName);
       	    tvAdd.setText("" + jobAddress + ", #" + jobUnitNo);
       	    tvMobile.setText("" + jobMobile);
       	    tvPostal.setText("S " + jobPostal);
       	    tvType.setText("" + jobType);
       	    Log.d("jobsActivityTAG", jobType);
       	    if (jobType.equals("pickup")){
       	    	tvType.setText("" + "Pickup");
       	    }
       	    
       	    if  (jobType.equals("delivery")){
       	    	tvType.setText("" + "Delivery");
       	    }
       	    
       	 Button buttonOne = (Button) findViewById(R.id.btnNavigate);
			buttonOne.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
			    	Toast.makeText(getApplicationContext(), "Opening Google Maps...", Toast.LENGTH_SHORT).show();
			    	String uri = "https://maps.google.com/maps?saddr=&daddr=" + jobAddress;        
			    	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
			    	//Opens only Google Maps
			    	i.setClassName("com.google.android.apps.maps", 
			    			"com.google.android.maps.MapsActivity");
			    	startActivity(i);			
		
			    }//end click
			}); //set onClickListener
	        
	 
		 Button buttontwo = (Button) findViewById(R.id.btnDone);
		 buttontwo.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
//			    	Toast.makeText(getApplicationContext(), "Done! Sending email to cust..",
//			    			Toast.LENGTH_SHORT).show();
			    openAlert(v);
			    }//end click
			}); //set onClickListener
	     
		 } //onCreate
	 
	 private void openAlert(View view) {
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(jobsActivity.this);
	     
		 alertDialogBuilder.setTitle("Job Done Confirmation");
		 alertDialogBuilder.setMessage("Confirm item is picked up or delivered?");
		 // set positive button: Yes message
		 alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					Toast.makeText(getApplicationContext(), "Sending confirmation email to cust..",
			    			Toast.LENGTH_SHORT).show();
					// go to a new activity of the app
//					Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
//					
//				      // prompts email clients only
//				      email.setType("message/rfc822");
//
//				      email.putExtra(Intent.EXTRA_EMAIL, new String[]{"clem0007@e.ntu.edu.sg"});
//				      email.putExtra(Intent.EXTRA_SUBJECT, "Pickup/Delivery Confirmation");
//				      email.putExtra(Intent.EXTRA_TEXT, "Dearest customer,\n\nyour item has been "
//				      		+ "delivered/picked up. Thank you.\n\nRegards,\nTAQBIN");
//				      email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
					
					String recepientEmail = "clem0007@e.ntu.edu.sg"; // either set to destination email or leave empty
					Intent email = new Intent(Intent.ACTION_SENDTO);
					email.setData(Uri.parse("mailto:" + recepientEmail));
					email.putExtra(Intent.EXTRA_SUBJECT, "Pickup/Delivery Confirmation");
				    email.putExtra(Intent.EXTRA_TEXT, "Dearest customer,\n\nyour item has been "
				      		+ "delivered/picked up. Thank you.\n\nRegards,\nTAQBIN");

			        try {
				    // the user can choose the email client
//				         startActivity(Intent.createChooser(email, "Choose an email client "
//				         		+ "from..."));
			        	startActivity(email);
			     
			        } catch (android.content.ActivityNotFoundException ex) {
			        	Toast.makeText(jobsActivity.this, "No email client installed.",
			        		 Toast.LENGTH_LONG).show();
			        }
				}
			  });
		 // set negative button: No message
		 alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// cancel the alert box and put a Toast to the user
					dialog.cancel();
				}
			});
		 
		 AlertDialog alertDialog = alertDialogBuilder.create();
		 // show alert
		 alertDialog.show();
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
