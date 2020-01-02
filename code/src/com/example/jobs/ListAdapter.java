package com.example.jobs;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapter extends ArrayAdapter<jobObject> {
	 public ListAdapter(Context context, int textViewResourceId) {
	        super(context, textViewResourceId);
	        // TODO Auto-generated constructor stub
	    }

	    private List<jobObject> items;
	    private Context context;

	    public ListAdapter(Context context, int resource, List<jobObject> items) {

	        super(context, resource, items);

	        this.items = items;
	        this.context = context;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	
	    	 if (convertView == null) {
	    	        // This a new view we inflate the new layout
	    	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	        convertView = inflater.inflate(R.layout.listrow, parent, false);
	    	    }
	    	        // Now we can fill the layout with the right values
	    	        TextView tv = (TextView) convertView.findViewById(R.id.rowName);
	    	        TextView add = (TextView) convertView.findViewById(R.id.rowAddress);
	    	        TextView jobId = (TextView) convertView.findViewById(R.id.rowJobId);
	    	        final jobObject p = items.get(position);
//	    	       
	    	        tv.setText("" + p.getName());
	    	        add.setText("" + p.getAddress());
	    	        jobId.setText("Job #" + p.getJobId() + ":");
	    	        
	    	        Button buttonOne = (Button) convertView.findViewById(R.id.btnCall);
	    			buttonOne.setOnClickListener(new View.OnClickListener() {
	    				
	    				public void onClick(View v) {
	    			    	Log.d("ListAdapter", "btnCall clicked!");
//	    			    	Intent callIntent = new Intent(Intent.ACTION_CALL); //call directly
	    			    	Intent callIntent = new Intent(Intent.ACTION_DIAL);//port no to dialer
	    			        callIntent.setData(Uri.parse("tel:" + p.getMobile()));
	    			        
	    			        	
	    			        try {
	    					    // the user can choose the email client
	    			        	context.startActivity(callIntent);	
	    				     
	    				     } catch (android.content.ActivityNotFoundException ex) {
	    				         Toast.makeText(context, "No caller app found.",
	    				        		 Toast.LENGTH_LONG).show();
	    				     }
	    		
	    			    }//end click
	    			}); //set onClickListener
	    	        
	    	        
	    	        return convertView;
	    	        

	    }

}
