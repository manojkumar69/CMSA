package com.valid.logic;

import java.util.ArrayList;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
public class VoiceRecognition extends Activity implements InfoInterface {

	protected static final int RESULT_SPEECH = 1;

	private Button speakbtn,nextactivity;
	private TextView recorded;
	ProgressDialog pDialog;
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voicerecognition);
		recorded = (TextView)findViewById(R.id.recorded);
		speakbtn = (Button)findViewById(R.id.voice);
		nextactivity=(Button)findViewById(R.id.vrnextactivity);
		nextactivity.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) 
            {
            	nextActivity();
             }
            }); 
		speakbtn.setOnClickListener(new View.OnClickListener()
		{
		    public void onClick(View v) 
		    {
		    	Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
				try
				{
					startActivityForResult(intent, RESULT_SPEECH);
					recorded.setText("");
				} 
				catch (ActivityNotFoundException a) 
				{
					Toast t = Toast.makeText(getApplicationContext(),
							"Oops! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});
	}	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				String invoice=text.get(0);
				recorded.setText(invoice);
				alldatas.put("voice",invoice);
				Toast.makeText(getApplicationContext(),"Recorded text==>"+invoice,Toast.LENGTH_SHORT).show();			
			}
			break;
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
