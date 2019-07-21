package com.logic;

import java.io.BufferedInputStream;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
public class SymmentricDecryption extends Activity implements InfoInterface{
Button decrypt,selfdecrypt,nextactivity; 
EditText pkey,vkey;
Base64 bs=new Base64();
Decrypt d=new Decrypt();
String photokey="",videokey="",photoencryphertext="";
StringBuffer sb=new StringBuffer();
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
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	    	try
	    	{
	    		super.onCreate(savedInstanceState);
	            setContentView(R.layout.decryption_activity);
	            decrypt=(Button)findViewById(R.id.decryptbutton);
	            nextactivity=(Button)findViewById(R.id.decnextactivity);
	            selfdecrypt=(Button)findViewById(R.id.decryptwithinbutton);
	            pkey=(EditText)findViewById(R.id.editTextphotkey);
	            vkey=(EditText)findViewById(R.id.editTextvideokey);
	            nextactivity.setOnClickListener(new Button.OnClickListener(){
	                @Override
	                public void onClick(View arg0) 
	                {
	                    nextActivity();
	                }
	                });
	            decrypt.setOnClickListener(new View.OnClickListener() 
	            {
	            	@Override
					public void onClick(View v)
	            	{
	            		photokey=pkey.getText().toString();
	            		videokey=vkey.getText().toString();
						if(!photokey.equals(""))
						{							
//							String path=Environment.getExternalStorageDirectory().getAbsolutePath()+
//							"/Download/EncryptedImage.txt";
//							convertImage(path);
						showFileChooser();
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Plz download Image_File from Email", Toast.LENGTH_LONG).show();
							 showFileChooser();
						}
						if(!videokey.equals(""))
						{
//							String path=Environment.getExternalStorageDirectory().getAbsolutePath()+
//							"/Download/EncryptedVideo.txt";
//							 convertVideo(path);
							showFileChooser();						}
						else
						{
							Toast.makeText(getApplicationContext(), "Plz download Video_File from Email", Toast.LENGTH_LONG).show();
						}
	            		
					}
	            });
//	            decrypt.setOnClickListener(new Button.OnClickListener(){
//
//	            	Intent intent = new Intent(getBaseContext(), FileDialog.class);
//	                intent.putExtra(FileDialog.START_PATH, "/sdcard");
//	                
//	                //can user select directories or not
//	                intent.putExtra(FileDialog.CAN_SELECT_DIR, true);
//	                
//	                //alternatively you can set file filter
//	                //intent.putExtra(FileDialog.FORMAT_FILTER, new String[] { "png" });
//	                
//	                startActivityForResult(intent, REQUEST_SAVE);
//	            	    
//	            	   }});
	            selfdecrypt.setOnClickListener(new View.OnClickListener() 
	            {
	            	@Override
					public void onClick(View v)
	            	{
	            		if(alldatas.containsKey("photokey"))
	                    {
	                    	photokey=alldatas.get("photokey").toString();
	                    	Toast.makeText(getApplicationContext(),"Image Convertion",Toast.LENGTH_LONG).show();
							convertImage(alldatas.get("photocyphertextpath"));
	                    }
	            		else
						{
							Toast.makeText(getApplicationContext(), "Plz Encrypt Your Images", Toast.LENGTH_LONG).show();
						}
	                    if(alldatas.containsKey("videokey"))
	                    {
	                    	videokey=alldatas.get("videokey").toString();
	                    	Toast.makeText(getApplicationContext(),"Video Convertion",Toast.LENGTH_LONG).show();
							convertVideo(alldatas.get("videocyphertextpath").toString());
	                	 }
	            	    else
						{
							Toast.makeText(getApplicationContext(), "Plz Encrypt Your Video", Toast.LENGTH_LONG).show();
						}
					}
			});
	    }
	    catch (Exception e) 
		{
			Toast.makeText(getApplicationContext(), "File path>"+e, Toast.LENGTH_LONG).show();
		} 
	 }
     private void convertImage(String path)
	 {
		 File f=new File(path);
			if(f.exists())
			{
				try 
				{
					FileReader fis=new FileReader(path);
					BufferedReader br=new BufferedReader(fis);
					String dum="";
				    //Reading lines  from the .java file..
				     while ((dum = br.readLine()) != null)
			         { 
				    	 sb.append(dum);
				     }
			         String decrypted=d.decrypt(sb.toString(),photokey);
					 byte ar[]=bs.decode(decrypted);
					 FileOutputStream fos=new FileOutputStream(APP_PATH+"/DecryptedImage/"+System.currentTimeMillis()+".jpg");
					 fos.write(ar);
					 fos.flush();
					 fos.close();
					 Toast.makeText(getApplicationContext(),"converted to Image",Toast.LENGTH_LONG).show();
				} 
				catch (Exception e) 
				{
					Toast.makeText(getApplicationContext(), "File path>"+e, Toast.LENGTH_LONG).show();
				} 
			}
	 }
     private void convertVideo(String path)
	 {
		 File f=new File(path);
			if(f.exists())
			{
				try 
				{
					FileReader fis=new FileReader(path);
					BufferedReader br=new BufferedReader(fis);
					String dum="";
				    //Reading lines  from the .java file..
				     while ((dum = br.readLine()) != null)
			         { 
				    	 sb.append(dum);
				     }
			         String decrypted=d.decrypt(sb.toString(),videokey);
					 byte ar[]=bs.decode(decrypted);
					 FileOutputStream fos=new FileOutputStream(APP_PATH+"/DecryptedVideo/"+System.currentTimeMillis()+".mp4");
					 fos.write(ar);
					 fos.flush();
					 fos.close();
					 Toast.makeText(getApplicationContext(),"converted to Video",Toast.LENGTH_LONG).show();
				} 
				catch (Exception e) 
				{
					Toast.makeText(getApplicationContext(), "File path>"+e, Toast.LENGTH_LONG).show();
				} 
			}
	 }
//     public void openFolder()
//     {
//     File file = new File(Environment.getExternalStorageDirectory(),
//         "myFolder");
//
//     Log.d("path", file.toString());
//
//     Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//     intent.setDataAndType(Uri.fromFile(file), "*/*");
//     startActivity(intent);
//     }
     private static final int FILE_SELECT_CODE = 0;

     private void showFileChooser() {
         Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
         intent.setType("*/*"); 
         intent.addCategory(Intent.CATEGORY_OPENABLE);

         try {
             startActivityForResult(
                     Intent.createChooser(intent, "Select a File to Upload"),
                     FILE_SELECT_CODE);
         } catch (android.content.ActivityNotFoundException ex) {
             // Potentially direct the user to the Market with a Dialog
             Toast.makeText(this, "Please install a File Manager.", 
                     Toast.LENGTH_SHORT).show();
         }
     }
     public static String getPath(Context context, Uri uri) throws URISyntaxException {
    	    if ("content".equalsIgnoreCase(uri.getScheme())) {
    	        String[] projection = { "_data" };
    	        Cursor cursor = null;

    	        try {
    	            cursor = context.getContentResolver().query(uri, projection, null, null, null);
    	            int column_index = cursor.getColumnIndexOrThrow("_data");
    	            if (cursor.moveToFirst()) {
    	                return cursor.getString(column_index);
    	            }
    	        } catch (Exception e) {
    	            // Eat it
    	        }
    	    }
    	    else if ("file".equalsIgnoreCase(uri.getScheme())) {
    	        return uri.getPath();
    	    }

    	    return null;
    	} 
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         switch (requestCode) {
             case FILE_SELECT_CODE:
             if (resultCode == RESULT_OK) {
                 // Get the Uri of the selected file 
                 Uri uri = data.getData();
                 // Get the path
                 try {
					String path = getPath(this, uri);
					 Toast.makeText(getApplicationContext(), "path:"+path, Toast.LENGTH_LONG).show();
					 if(path.contains("EncryptedImage.txt"))
					 {
						 convertImage(path);
						 
					 }
					 else if(path.contains("EncryptedVideo.txt"))
					 {
						 convertVideo(path);
					 }
				} catch (URISyntaxException e) {
				
					e.printStackTrace();
				}
             }
             break;
         }
         super.onActivityResult(requestCode, resultCode, data);
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
