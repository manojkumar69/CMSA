package com.logic;

import java.io.ByteArrayOutputStream;



import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
public class SymmentricEncryption extends Activity implements InfoInterface{ 
	Button encryption,nextactivity;
	TextView keys;
	public String passWord="",pkey="",vkey="",videocyphertext,photocyphertext;
	String filepath="";
	Base64 bs=new Base64();
	Random r=new Random();
	
	String photoencryptedString="",videoencryptedString="",pvkey="";
	public AlertDialog.Builder alertDialogBuilder=null;
	public AlertDialog alertDialog =null;
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.encryption_activity);
		
		encryption=(Button)findViewById(R.id.encryptbutton);
		keys=(TextView)findViewById(R.id.keystextview);
		nextactivity=(Button)findViewById(R.id.encryptnxtact);
		alertDialogBuilder = new AlertDialog.Builder(this);
		nextactivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				nextActivity();
			}
		});
		encryption.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try
				{
					
				    //getting file from interface.
					if(alldatas.containsKey("photo"))
					{
						alertDialogBuilder.setMessage("Are you sure,You want to Encrypt Your Image");
					    alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
				         @Override
				         public void onClick(DialogInterface arg0, int arg1) {
				        	 alertDialog.dismiss();
				        	 pkey=String.valueOf(r.nextInt(99))+String.valueOf(r.nextInt(99))+String.valueOf(r.nextInt(99));
							Toast.makeText(getApplicationContext(),"Im_key"+pkey,Toast.LENGTH_SHORT).show();
							alldatas.put("photokey",pkey);
							pvkey="Photo-Key:"+pkey;
							keys.setText(pvkey);
							filepath=alldatas.get("photo").toString();
							Toast.makeText(getApplicationContext(), "imageurl=>"+filepath,Toast.LENGTH_LONG).show();
					        new CipherText().doInBackground(filepath);
						  }
				      });
					      alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
					         @Override
					         public void onClick(DialogInterface dialog, int which) {
					        	 alertDialog.dismiss();
					        	 encrypytVideo();
					         }
					      });
					      alertDialog = alertDialogBuilder.create();
					      alertDialog.show();
					}
					else
					{
						Toast.makeText(getApplicationContext(),"!!No Imagefiles to Encrypt",Toast.LENGTH_LONG).show();
						encrypytVideo();
					}
					
				}
				catch(Exception e)
				{
					 Toast.makeText(getApplicationContext(), "Enc Exe:=>"+e,Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	void encrypytVideo()
	{
		try
		{
			if(alldatas.containsKey("video"))
			{
				alertDialogBuilder.setMessage("Are you sure,You want to Encrypt Your Video");
			     alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
		         @Override
		         public void onClick(DialogInterface arg0, int arg1) {
		        	 alertDialog.dismiss();
		        	 vkey=String.valueOf(r.nextInt(99))+String.valueOf(r.nextInt(99))+String.valueOf(r.nextInt(99));
						alldatas.put("videokey",vkey);
						if(!pvkey.equals(""))
						{
							pvkey+="\n Video_Key:"+vkey;
							keys.setText(pvkey);
						}
						else
						{
							pvkey="Video_Key:"+vkey;
							keys.setText(pvkey);
						}
						filepath=alldatas.get("video").toString();
						Toast.makeText(getApplicationContext(), "videourl=>"+filepath,Toast.LENGTH_LONG).show();
						new CipherText().doInBackground(filepath);
		         }
		      });
			      alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
			         @Override
			         public void onClick(DialogInterface dialog, int which) {
			          alertDialog.dismiss();
			         }
			      });
			      alertDialog = alertDialogBuilder.create();
			      alertDialog.show();
		    }
			else
			{
				Toast.makeText(getApplicationContext(),"!!No Video files to Encrypt",Toast.LENGTH_LONG).show();
			}
		}
		catch(Exception e)
		{
			 Toast.makeText(getApplicationContext(), "Enc Exe:=>"+e,Toast.LENGTH_LONG).show();
		}
		
	}
	public byte[] convertByteArray(String fileurl)throws IOException
	{
		byte[] bytes = null;
		try
		{
		
		FileInputStream fis = new FileInputStream(fileurl);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = fis.read(buf)) != -1;)
        {
        	bos.write(buf, 0, readNum); //no doubt here is 0
            bytes = bos.toByteArray();
        }
        bos.flush();
        bos.close();
        fis.close();
        System.out.println("bye arr==>"+bytes);
		}
	   	catch(Exception e)
	   	{
	   	 Toast.makeText(getApplicationContext(), "Enc conv:=>"+e,Toast.LENGTH_LONG).show();
	   	}
        return bytes;
	}
	public String encrypt(String mess,String passWord1) throws InvalidKeySpecException, IOException
    {
		SecretKeyFactory keyFactory;
		byte[] passByte;
	    Cipher desCipher;
	    SecretKey myDesKey ;
	    String sss="";
	    passWord=passWord1;
       	try
       	{
    	   manageKeystrengthMethod();
    	   keyFactory = SecretKeyFactory.getInstance("DES");
    	   passByte=passWord.getBytes();
    	   DESKeySpec dspec= new DESKeySpec(passByte);
    	   myDesKey = keyFactory.generateSecret(dspec);			
			// Create the cipher
    	   desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");	
		    // Initialize the cipher for encryption
    	   desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);	
		    //sensitive information
    	   byte[] text = mess.getBytes();	
		   // System.out.println("Text [Byte Format] : " + text);
		  //  System.out.println("Text : " + new String(text));	
		    // Encrypt the text
    	   byte[] textEncrypted = desCipher.doFinal(text);	
		    //System.out.println("Text Encryted : " + textEncrypted);
    	   sss=bs.encodeBytes(textEncrypted);           
       	}
       	catch(Exception e)
       	{
       		Toast.makeText(getApplicationContext(), "En_DES:=>"+e,Toast.LENGTH_LONG).show();
       	}
	    return sss;
    }
	private void manageKeystrengthMethod()
	{
		if(passWord.length()<8)
		{
			int counter=passWord.length();
			while(counter<8)
			{
				passWord+='@';
				counter++;
			}
		}
	}
// Async Task Class
class CipherText extends AsyncTask<String, String, String> {
				@Override
				protected String doInBackground(String ...f_url) {
					try 
					{
						String filename="";
						//converting to byte array and converting into encoded string using base64 .				       			       
				        //writing into text file
//						AlertDialog.Builder AlertDialogBuilder = new AlertDialog.Builder(SymmentricEncryption.this);
//						AlertDialogBuilder.setMessage("Please wait while Encrypting .....");
//						 alertDialog = AlertDialogBuilder.create();
//					      alertDialog.show();
				        if(f_url[0].contains(".jpg"))
				        {
				        	photoencryptedString= bs.encodeBytes(convertByteArray(f_url[0]));	
				        	photocyphertext=encrypt(photoencryptedString,pkey);
				        	filename="EncryptedImage.txt";
				        	alldatas.put("photocyphertextpath",APP_PATH+"EncryptedImage/"+filename);
				        	FileOutputStream fos=new FileOutputStream(APP_PATH+"EncryptedImage/"+filename);
							fos.write(photocyphertext.getBytes());
							fos.flush();
							fos.close();
							encrypytVideo();
				        }
				        else
				        {
				        	videoencryptedString= bs.encodeBytes(convertByteArray(f_url[0]));	
				        	videocyphertext=encrypt(videoencryptedString,vkey);
				        	filename="EncryptedVideo.txt";
				        	alldatas.put("videocyphertextpath",APP_PATH+"EncryptedVideo/"+filename);
				        	FileOutputStream fos=new FileOutputStream(APP_PATH+"EncryptedVideo/"+filename);
							fos.write(videocyphertext.getBytes());
							fos.flush();
							fos.close();
				        }
//				        if(alertDialog!=null)
//				        {
//				        	alertDialog.dismiss();
//				        }
						Toast.makeText(getApplicationContext(), "File Encrypted Successfully", Toast.LENGTH_LONG).show();
					}
					catch (Exception e) 
					{
						Toast.makeText(getApplicationContext(), "Ex-enc->"+e, Toast.LENGTH_LONG).show();
					}
					return null;
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