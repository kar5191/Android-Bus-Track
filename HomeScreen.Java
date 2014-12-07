package com.example.orbittrack;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;


public class HomeScreen extends Activity {
	 AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.1F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
       
        ImageButton ib1 = (ImageButton)findViewById(R.id.imageButton1);
        ImageButton ib2 = (ImageButton)findViewById(R.id.imageButton2);
        ib1.setOnClickListener(new View.OnClickListener() {

        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		v.startAnimation(buttonClick);
        		Intent intent = new Intent(getApplicationContext(), Mercury.class);
        		startActivity(intent);
        	}
        });
        
        ib2.setOnClickListener(new View.OnClickListener() {

        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		v.startAnimation(buttonClick);
        		Intent intent2 = new Intent(getApplicationContext(), Venus.class);
        		startActivity(intent2);
        		
        	}
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
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
