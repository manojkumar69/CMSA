package com.sample.logic;

import java.io.File;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;
import android.view.*;
public class MICROAPPActivity extends Activity implements InfoInterface {
	/** Called when the activity is first created. */
	  ListView list;
	  static String[] web = {"GPSLocation","SMS"
	        
	    } ;
	    Integer[] imageId = {R.drawable.gps,R.drawable.sms
	      
	    };
	
	    Class<?> dynamic_class;
	TextView start;
	File f = null;
    RadioGroup radioGroup;
	RadioButton cryptographytype,encrypt,decypt;
	Boolean encryptionStatus=true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); 
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
//		start = (TextView) findViewById(R.id.start);
//		start.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) { 
				try {
					f = new File(APP_PATH + "Video/");
					makeDirs(f);
					f = new File(APP_PATH + "Image/");
					makeDirs(f);
					f = new File(APP_PATH + "EncryptedImage/");
					makeDirs(f);
					f = new File(APP_PATH + "EncryptedVideo/");
					makeDirs(f);
					f = new File(APP_PATH + "DecryptedImage/");
					makeDirs(f);
					f = new File(APP_PATH + "DecryptedVideo/");
					makeDirs(f);
//					clrInterface();
				/**End of Encryption/Decryption*/ 
//					nextActivity();
					
					
					
				 CustomList adapter = new
			                CustomList(MICROAPPActivity.this, web, imageId);
			        list=(ListView)findViewById(R.id.list);
			                list.setAdapter(adapter);
			                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			 
			                    @Override
			                    public void onItemClick(AdapterView<?> parent, View view,
			                                            int position, long id) {
			                        Toast.makeText(getApplicationContext(), "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
			                        /**hereactivitychanges**/
if(web[+ position].equals("GPSLocation")){dynamic_class=GPSLocation.class;nextActivity();}
if(web[+ position].equals("SMS")){dynamic_class=SMS.class;nextActivity();}

			                       
			 
			                    }
			                });
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Ex_Mainact:" + e,
							Toast.LENGTH_LONG).show();
				}
			}
//		});
		
//	}
	private void makeDirs(File f) {
		try {
			if (!f.exists()) {
				f.mkdirs();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Ex_Mainact:" + e,
					Toast.LENGTH_LONG).show();
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
	private void nextActivity()
 	{
		try{
			Intent intent=new Intent(getApplicationContext(),dynamic_class);
	 		startActivity(intent);	
		}
		catch(Exception e){
			Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
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
	        if (id == R.id.pattern_changes) {
	        	Intent intent=new Intent(getApplicationContext(),FirstActivity.class);
		 		startActivity(intent);	
	            return true;
	        }
	        if (id == R.id.cleardata) {
	        	clrInterface();
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }

	    /**
	     * A placeholder fragment containing a simple view.
	     */
	    public static class PlaceholderFragment extends Fragment {

	        public PlaceholderFragment() {
	        }

	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
	            return rootView;
	        }
	    }
}
