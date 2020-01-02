package com.example.jobs;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobs.library.JSONParser;

public class MainActivity extends ActionBarActivity {
	private static String url= "http://scet.sg/courier/wp-content/themes/spacious-child/data/android.json";
	private static final String TAG_DATE = "26/08/2014";
	public List<jobObject> yourData = new ArrayList<jobObject>();
	int size = 0;
	Activity mainActivity;
	private static Context mContext;
	int x;
	ListView yourListView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		TextView txt = (TextView) findViewById(R.id.name);  
//		Typeface font = Typeface.createFromAsset(getAssets(), "helvetica.ttf");  
//		txt.setTypeface(font); 
		//mainActivity for AsyncTask use
		mainActivity = this;
        //execute JSON AsyncTask
		new JSONParse().execute();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbarmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}	
//		return super.onOptionsItemSelected(item);
		
		switch (item.getItemId()) {
        case android.R.id.home:
            // app icon in action bar clicked; go home
//        	this.finish();
        	finish();
            return true;
            
        case R.id.action_refresh:
            // refresh clicked; reload list
        	yourData.clear();
        	new JSONParse().execute();  
        	return true;
        	
        case R.id.action_sickapp:
        	// Start sickActivity
            Intent i = new Intent(MainActivity.this, sickActivity.class);               
            startActivity(i);
        	return true;	
        default:
            return super.onOptionsItemSelected(item);
		}
		
	}
	
	
	
	
	private class JSONParse extends AsyncTask<String, String, JSONObject> {
	    private ProgressDialog pDialog;
	   @Override
	     protected void onPreExecute() {
	         super.onPreExecute();	          
	         pDialog = new ProgressDialog(MainActivity.this);
	         pDialog.setMessage("Getting Data ...");
	         pDialog.setIndeterminate(false);
	         pDialog.setCancelable(true);
	         pDialog.show();
	   }
	   @Override
	     protected JSONObject doInBackground(String... args) {
	     JSONParser jParser = new JSONParser(mContext);
	     // Getting JSON from URL
	     JSONObject json = jParser.getJSONFromUrl(url);
	     return json;
	   }
	    @Override
	   protected void onPostExecute(JSONObject json) {
	      pDialog.dismiss();
	      
	      
	      try {
	    	    JSONObject c = json.getJSONObject("date");
	            JSONArray c1 = c.getJSONArray(TAG_DATE);
	            x = 1;
	            for(int i=0;i<c1.length();i++)
	            {
	            	
	                JSONObject c2 = c1.getJSONObject(i);// Used JSON Object from Android
	                //pickup and delivery specific
	                JSONObject jsonAdd = c2.getJSONObject("address");
	                JSONObject jsonFloor = c2.getJSONObject("floorNo");
	                JSONObject jsonUnit = c2.getJSONObject("unitNo");
	                JSONObject jsonPostal = c2.getJSONObject("postalcode");
	                
	                //Storing each Json in a string variable
	                String NAME= c2.getString("name");	                
	                String MOBILE = c2.getString("tel");
	                String TYPE = c2.getString("type");
	                int JOBSID = x;
	                
	                String ADD= jsonAdd.getString(TYPE);
	                String UNITNO = jsonFloor.getString(TYPE) + "-" + jsonUnit.getString(TYPE);
	                String POSTAL= jsonPostal.getString(TYPE);
	                
	                yourData.add(new jobObject(NAME, ADD, MOBILE, JOBSID, UNITNO, TYPE, POSTAL));
	                x++;
	            }
	            size = yourData.size(); 
	            
	            Log.d("ADebugTag", "List: " + size);     
	     } catch (JSONException e) {
	       e.printStackTrace();
	     }
	      
	     yourListView = (ListView) findViewById(R.id.listView);		
         ListAdapter customAdapter = new ListAdapter (mainActivity, R.layout.listrow, yourData);        
         yourListView.setAdapter(customAdapter);        
         
         
         yourListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          	  
          	  public void onItemClick(AdapterView<?> parent, View view,
          	    int position, long id) {
          		TextView jobIdView = (TextView) view.findViewById(R.id.rowJobId);
          		TextView tvName = (TextView) view.findViewById(R.id.rowName);
	       	    TextView tvAdd = (TextView) view.findViewById(R.id.rowAddress);
	       	    
          		final String jobId = (String)jobIdView.getText().toString();
          		final String jobName = (String)tvName.getText().toString();          		
          		final String jobAddress = (String)tvAdd.getText().toString();
          		final String jobUnitNo = yourData.get(position).getUnitNo();
          		final String jobMobile = yourData.get(position).getMobile();  
          		final String jobPostal = yourData.get(position).getPostal();
          		final String jobType = yourData.get(position).getType();
//          	    Toast.makeText(getApplicationContext(),
//          	      "Click ListItem Number " + position + " " + jobId, Toast.LENGTH_SHORT)
//          	      .show();    
          	    
          	    // Start jobActivity
                Intent i = new Intent(MainActivity.this, jobsActivity.class);               
                i.putExtra("jobId", jobId);
                i.putExtra("jobName", jobName);
                i.putExtra("jobAddress", jobAddress);
                i.putExtra("jobMobile", jobMobile);
                i.putExtra("jobPostal", jobPostal);
                i.putExtra("jobUnitNo", jobUnitNo);
                i.putExtra("jobType", jobType);
                startActivity(i);
          	              	    
              
          		
          	  }
          	}); 
        
	      
	    }
	      
	 }
	
}
