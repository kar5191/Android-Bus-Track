package com.example.orbittrack;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import datastore.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Mercury extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mercury);
		//RadioGroup radio = (RadioGroup) findViewById(R.id.radioGroup2);
		RadioButton btn0 = (RadioButton) findViewById(R.id.radio0);
        //DBHelper helper=null;
		 btn0.setOnClickListener(new View.OnClickListener() {

	        	@Override
	        	public void onClick(View v) {
	        		// TODO Auto-generated method stub
	        		//Intent intent = new Intent(getApplicationContext(), Mercury.class);
	        		//startActivity(intent);
	        		Calendar cal = Calendar.getInstance(); 
	        		int hours=cal.get(Calendar.HOUR_OF_DAY);
	        		int minutes=cal.get(Calendar.MINUTE);
	        		String time=Integer.toString(hours)+":"+Integer.toString(minutes);
	        		String time2=Integer.toString(hours)+":"+Integer.toString(minutes+16);
	        		DBHelper helper=new DBHelper(Mercury.this);
	        		 try {
	        			 
	        			 helper.createDataBase();
	        			 Log.i(getLocalClassName(), "Called create db");
	        			  
	        			 } catch (IOException ioe) {
	        			  
	        				 Log.i(getCallingPackage(), "IOexception in calling create");
	        			 throw new Error("Unable to create database");
	        			  
	        			 }
	        		ArrayList<String> arrayList=new ArrayList<String>();
	        		try{
	        			Log.i(getLocalClassName(), "Calling open db");
	        		helper.openDataBase();}
	        		catch (SQLException sqle)
	        		{
	        			Log.i(getLocalClassName(), "SQL Exception");
	        			throw sqle;
	        		}
	        		arrayList=helper.getDataMEast(time,time2);
	        		helper.close();
	        		TextView textView=(TextView) findViewById(R.id.textView2);
	        		textView.setText("Next Bus at TransitCentre: " +arrayList.get(0));
	        		TextView textView1=(TextView) findViewById(R.id.textView4);
	        		textView1.setText("Arrives in McClintock at: " +arrayList.get(1));
	        		//Toast.makeText(getApplicationContext(),"Eastbound selected" , Toast.LENGTH_SHORT).show();
	        	}
	        });
		 
			RadioButton btn1 = (RadioButton) findViewById(R.id.radio1);
		       
			 btn1.setOnClickListener(new View.OnClickListener() {

		        	@Override
		        	public void onClick(View v) {
		        		// TODO Auto-generated method stub
		        		//Intent intent = new Intent(getApplicationContext(), Mercury.class);
		        		//startActivity(intent);
		        		Toast.makeText(getApplicationContext(),"Westbound selected" , Toast.LENGTH_LONG).show();
		        	}
		        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mercury, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
