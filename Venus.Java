package com.example.orbittrack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import datastore.DBHelper;
import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class Venus extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venus);
		RadioButton btn0 = (RadioButton) findViewById(R.id.radio0);
        //DBHelper helper=null;
		 btn0.setOnClickListener(new View.OnClickListener() {

	        	@Override
	        	public void onClick(View v) {
	        		// TODO Auto-generated method stub
	        		//Intent intent = new Intent(getApplicationContext(), Mercury.class);
	        		//startActivity(intent);
	        		dbCall("venus_f");
	        	}
	        });
		 
			RadioButton btn1 = (RadioButton) findViewById(R.id.radio1);
		       
			 btn1.setOnClickListener(new View.OnClickListener() {

		        	@Override
		        	public void onClick(View v) {
		        		// TODO Auto-generated method stub
		        		//Intent intent = new Intent(getApplicationContext(), Mercury.class);
		        		//startActivity(intent);
		        		//Toast.makeText(getApplicationContext(),"Westbound selected" , Toast.LENGTH_LONG).show();
		        		dbCall("venus_back");
		        	}
		        });
	}

	//}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.venus, menu);
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
	
	
	public void dbCall(String forward_back)
	{
		String AM_PM="AM";
		Calendar cal = Calendar.getInstance(); 
		int hours=cal.get(Calendar.HOUR_OF_DAY);
		int day_of_week=cal.get(Calendar.DAY_OF_WEEK);		
		int minutes=cal.get(Calendar.MINUTE);
		String time="";
		String time2="";
		
		switch(day_of_week)
		{
		case 1:
			if(forward_back.equals("venus_f"))forward_back="venus_f_sun";
			else forward_back="venus_back_sun";
			break;
			
		case 7:
			if(forward_back.equals("venus_f")) forward_back="venus_f_sat";
			else forward_back="venus_back_sat";
		}
		
		if(hours==12){AM_PM="PM";}
		if(hours>12 && hours!=23) {hours=hours%12;AM_PM="PM";}
		if(minutes<10)
			time=Integer.toString(hours)+":"+"0"+Integer.toString(minutes);
		else
			time=Integer.toString(hours)+":"+Integer.toString(minutes);
		if(minutes+31>=60)
		{
			minutes=minutes%60;
			if(minutes<10)
				time2=Integer.toString(hours)+":"+"0"+Integer.toString(minutes);
			else
			time2=Integer.toString(hours+1)+":"+Integer.toString(minutes);
		}
		else
			time2=Integer.toString(hours)+":"+Integer.toString(minutes+31);
		
		DBHelper helper=new DBHelper(Venus.this);
		 try {
			 
			 helper.createDataBase();
			 Log.i(getLocalClassName(), "Called create db venus");
			  
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
		//if(east_west.equals("East"))
		arrayList=helper.getDataOrbit(time,time2,AM_PM,forward_back,day_of_week);
		//else
		//arrayList=helper.ge	
		helper.close();
		
		TextView textView1=(TextView) findViewById(R.id.textView3);
		TextView textView2=(TextView) findViewById(R.id.textView4);
     	TextView textView3=(TextView) findViewById(R.id.textView5);	
		if(!arrayList.isEmpty())
		{
			if(forward_back.equals("venus_f")||forward_back.equals("venus_f_sat")||forward_back.equals("venus_f_sun"))
			{
				textView1.setText("Next Bus at Transit Center: " +arrayList.get(0));
			   textView2.setText("Arrives in Brdway&Rsvlt: " +arrayList.get(1));
			   textView3.setText("Arrives Unvrsty&Prst: " +arrayList.get(2));
		//Toast.makeText(getApplicationContext(),"Eastbound selected" , Toast.LENGTH_SHORT).show();
			}
			else
			{
				textView1.setText("Next Bus at Transit Center: " +arrayList.get(0));
			   textView2.setText("Arrives in Unvrsty&Prst: " +arrayList.get(1));
			   textView3.setText("Arrives Rsvlt&Brdwy: " +arrayList.get(2));
			}
		}
		else
		textView1.setText("There are no buses plying at this time.");
	}
}
