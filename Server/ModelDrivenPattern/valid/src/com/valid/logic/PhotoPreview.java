package com.valid.logic;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
public class PhotoPreview extends Activity implements InfoInterface{
    /** Called when the activity is first created. */
	ImageView preimage;
	Button previous,next;

    private void clrInterface() {
		try {
			alldatas.clear();
			locinfomap.clear();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Ex_clear:" + e,
					Toast.LENGTH_LONG).show();
		}
	}
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();
	switch (id) {
	case R.id.cleardata:
		clrInterface();
		return true;
	
	case R.id.pattern_changes:
		Intent intent=new Intent(getApplicationContext(),FirstActivity.class);
 		startActivity(intent);	
		return true;


	default:
		return super.onOptionsItemSelected(item);
	}
}
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_activity);
        preimage=(ImageView)findViewById(R.id.image);
        next=(Button)findViewById(R.id.prenext);
        previous=(Button)findViewById(R.id.precam);
        try
        {
        	Toast.makeText(getApplicationContext(), "path===>"+alldatas.get("photo"),Toast.LENGTH_LONG).show();
        	if(alldatas.containsKey("photo"))
        	{
        		String imageurl=alldatas.get("photo").toString();
            	Toast.makeText(getApplicationContext(), "url=>"+imageurl,Toast.LENGTH_LONG).show();
            	preimage.setImageURI(Uri.parse(imageurl));
        	}
        	else
        	{
        		Toast.makeText(getApplicationContext(), "Plz Capture Image to Preview",Toast.LENGTH_LONG).show();
        	}
        	
        }
        catch(Exception e)
        {
        	Toast.makeText(getApplicationContext(), "Ex_prev:"+e,Toast.LENGTH_LONG).show();
        }
        previous.setOnClickListener(new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				Intent intent=new Intent(getApplicationContext(),TakePhoto.class);
				startActivity(intent);
			}
		});
         next.setOnClickListener(new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				nextActivity();
			}
		});
    }
    private void nextActivity()
	{
    	SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		String pattern = pref.getString("pattern", null);
		if(pattern.contains("all")){
			Intent intent = new Intent(getApplicationContext(),MICROAPPActivity.class);
	 		startActivity(intent);
		}
		else if(pattern.contains("custom")){
			String s=pref.getString("selectedpattern", null);
			int i=pref.getInt("activity_count", 0);
			Intent intent = new Intent(getApplicationContext(),FirstActivity.classMap.get(s.split(",")[i]));
	 		startActivity(intent);
			Editor editor = pref.edit();
			editor.putInt("activity_count", ++i);
			editor.commit();
		}
	}
}
