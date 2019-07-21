package com.logic;
import android.view.Menu;

import android.view.MenuItem;
import java.io.File;

import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class TakePhoto extends Activity implements InfoInterface {
	Button camera,nxtservice;
	File sdbmpMainDirectory;
	Boolean navtype = true;
	File root = new File(Environment.getExternalStorageDirectory()+"/MICROAPP/Image/");
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        camera=(Button)findViewById(R.id.cam);
        nxtservice=(Button)findViewById(R.id.camnext);
        camera.setOnClickListener(new OnClickListener() 
        {
        	@Override
			public void onClick(View v) 
			{
        	
				openCam();
			}
		});
        nxtservice.setOnClickListener(new OnClickListener() 
        {
        	@Override
			public void onClick(View v) 
			{
        		nextActivity();
			}
		});
    }
	public void openCam() 
    {
		try
		{

			String filename="android_"+System.currentTimeMillis()+".jpg";
			sdbmpMainDirectory=new File(root,filename);
	        Uri outputFileUri = Uri.fromFile(sdbmpMainDirectory);
	        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
	        //updating in the interface
	        alldatas.put("photo",root+"/"+filename);
	        startActivityForResult(intent, 0);
//			String filename="android_"+System.currentTimeMillis()+".jpg";
//			sdbmpMainDirectory=new File(root,filename);
//	        Uri outputFileUri = Uri.fromFile(sdbmpMainDirectory);
//	        Intent photoPickerIntent = new Intent(
//                    Intent.ACTION_PICK,
//                    outputFileUri);
//			
//            photoPickerIntent.setType("image/*");
//            photoPickerIntent.putExtra("crop", "true");
//            photoPickerIntent.putExtra("outputX", 50);
//            photoPickerIntent.putExtra("outputY", 50);
//            photoPickerIntent.putExtra("aspectX", 1);
//            photoPickerIntent.putExtra("aspectY", 1);
//            photoPickerIntent.putExtra("scale", true);
//            photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, Environment.getExternalStorageDirectory()+"/MICROAPP/Image/");
//            photoPickerIntent.putExtra("outputFormat",
//                    Bitmap.CompressFormat.JPEG.toString());
//            startActivityForResult(photoPickerIntent, 0);
//            
//			
//			
//	        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//	        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
////	        intent.setType("image/*");
////	        intent.putExtra("crop", "true");
////	        intent.putExtra("outputX", 50);
////	        intent.putExtra("outputY", 50);
////	        intent.putExtra("aspectX", 1);
////	        intent.putExtra("aspectY", 1);
////	        intent.putExtra("scale", true);
////	        intent.putExtra("outputFormat",
////                    Bitmap.CompressFormat.JPEG.toString());
////	        //updating in the interface
//	        
//	        alldatas.put("photo",root+"/"+filename);
//	        Toast.makeText(getApplicationContext(), "set path===>"+alldatas.get("photo"),Toast.LENGTH_LONG).show();
//	        startActivityForResult(intent,0);
	        
	        
		}
		catch(Exception e)
		{
			  Toast.makeText(this,"Ex_phto:"+e,Toast.LENGTH_LONG).show();
		}		
	}
     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    {
    	try
    	{
	        super.onActivityResult(requestCode, resultCode, data);
	        if(navtype)//forwarding to next Activity
	        	nextActivity();
	    }
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
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
