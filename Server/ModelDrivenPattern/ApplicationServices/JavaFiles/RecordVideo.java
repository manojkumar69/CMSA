package com.logic;

import java.io.File;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
public class RecordVideo extends Activity implements InfoInterface{
     
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    public static RecordVideo ActivityContext =null; 
    public static  String filename="";
    public File root=new File(Environment.getExternalStorageDirectory()+"/MICROAPP/Video");
    Button buttonRecording,nextactivity;
    Boolean navtype=false;
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
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videorecording_activity);
        ActivityContext = this;        
        buttonRecording = (Button)findViewById(R.id.recording);
        nextactivity = (Button)findViewById(R.id.videonextact);    
        nextactivity.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) 
            {
                nextActivity();            
            }
            }); 
        buttonRecording.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) 
            {
                // create new Intent with Standard Intent action that can be
                // sent to have the camera application capture an video and return it. 
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
               // set the video image quality to high
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);  
                // start the Video Capture Intent
                startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);                 
            }
            });        
    }      
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // After camera screen this code will excuted
        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) 
        {
        	if (resultCode == RESULT_OK)
            {
        	  try
              {
        		  AssetFileDescriptor videoAsset = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
        		  FileInputStream fis = videoAsset.createInputStream();
        		  filename="android_"+System.currentTimeMillis()+".mp4";
        		  FileOutputStream fos = new FileOutputStream(new File(root, filename));
        		  byte[] buf = new byte[1024];
        		  int len;
        		  while ((len = fis.read(buf)) > 0) 
        		  {
        			  fos.write(buf, 0, len);
        		  }       
        		  fis.close();
        		  fos.close();
        		  Toast.makeText(this, "Video Saved Successfully", Toast.LENGTH_LONG).show();
        		  alldatas.put("video",root+"/"+filename);
        		  Toast.makeText(getApplicationContext(), alldatas.get("video"), 1);
        		  if(navtype)//forwarding to next Activity
        			  nextActivity();
              } 
        	  catch (Exception e) 
        	  {
        		  Toast.makeText(this, "Video saving :"+e, Toast.LENGTH_LONG).show();
        	  }
           } 
           else if (resultCode == RESULT_CANCELED) 
           {
               // User cancelled the video capture
                Toast.makeText(this, "User cancelled the video capture.",Toast.LENGTH_LONG).show();
           } 
           else
           {
                // Video capture failed, advise user
                Toast.makeText(this, "Video capture failed.",Toast.LENGTH_LONG).show();
           }
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