package com.logic;

import android.net.Uri;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.view.Menu;
import android.view.MenuItem;
public class VideoPreview extends Activity implements InfoInterface{

	VideoView vv;
	Button nextactivity;
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
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoview);
		vv =(VideoView)findViewById(R.id.videoView1);
		nextactivity = (Button)findViewById(R.id.videoprenext);   
        nextactivity.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0) 
            {
                nextActivity();          
            }
        }); 		     
	   try
	   {
		   Toast.makeText(getApplicationContext(), alldatas.get("video"), 1);
		   if(alldatas.containsKey("video"))
		   {
			   String videourl = alldatas.get("video").toString();
			   MediaController mediaController= new MediaController(this);
			   mediaController.setAnchorView(vv);   
			   Uri uri=Uri.parse(videourl);        
			   vv.setMediaController(mediaController);
			   vv.setVideoURI(uri);        
			   vv.requestFocus();
			   vv.setEnabled(true);
			   vv.setKeepScreenOn(true);		   
			   vv.setVisibility(View.VISIBLE);
			   vv.start();
		   }
		   else
		   {
			   Toast.makeText(getBaseContext(), "Plz Record Video",Toast.LENGTH_LONG).show();
		   }
		}
	   catch(Exception e)
	   {
		   Toast.makeText(getBaseContext(), "Ex_vidprev:"+e,Toast.LENGTH_LONG).show();
	   }	    				   
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