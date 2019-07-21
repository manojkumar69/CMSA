package com.valid.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity implements InfoInterface{
Button defaultbutton, changebutton,allservice;
static HashMap<String ,Class<?> >classMap=new HashMap<String,Class<?>>();
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	try{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.first_activity);
	        /**hereaddclassess**/classMap.put("VR",VoiceRecognition.class);
classMap.put("DS",PhoneStatusActivity.class);
classMap.put("Email",Email.class);

	        
	    	final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
				
	        defaultbutton=(Button) findViewById(R.id.default_button);
	        allservice=(Button) findViewById(R.id.allservices);
	        changebutton=(Button) findViewById(R.id.change_button);
	        allservice.setOnClickListener(new View.OnClickListener()
			{
			    public void onClick(View v) 
			    {
			    	clrInterface();
			    	 Toast.makeText(getApplicationContext(), "allservices", Toast.LENGTH_SHORT).show();
			    
	 					Editor editor = pref.edit();
	 					editor.putString("pattern","all");
	 					editor.commit(); // commit changes 
			    	 Intent intent = new Intent(getApplicationContext(), MICROAPPActivity.class);
			    	 startActivity(intent);
			    }
			    });
	        defaultbutton.setOnClickListener(new View.OnClickListener()
			{
			    public void onClick(View v) 
			    { 
			    	clrInterface();
					String s=pref.getString("selectedpattern", null);
 					Toast.makeText(getApplicationContext(), "s-"+s, Toast.LENGTH_SHORT).show();
 					if(s==null){
 						Toast.makeText(getApplicationContext(), "please set pattern first", Toast.LENGTH_SHORT).show();
 					}
 					else if(s.length()>0){
 						Toast.makeText(getApplicationContext(), "length-"+s.length(), Toast.LENGTH_SHORT).show();
 						String activity=s.split(",")[0];
 	 					Editor editor=pref.edit();
 	 					editor.putString("pattern", "custom");
 	 					editor.putInt("activity_count", 1);
 	 					editor.commit();
 						Intent intent = new Intent(getApplicationContext(),FirstActivity.classMap.get(activity));
 	 			    	 startActivity(intent);
 					}
 					else{
 						Toast.makeText(getApplicationContext(), "--else".length(), Toast.LENGTH_SHORT).show();
 					}
			    }
			    });
	        changebutton.setOnClickListener(new View.OnClickListener()
			{
			    public void onClick(View v) 
			    { 
			    	clrInterface();
			    	List<String> l=new ArrayList<String>();
			    	for(String ser:classMap.keySet()){
			    		l.add(ser);
			    	}
			    	
			    	 ArrayList<String> s= (ArrayList<String>) AvailableCombinationsActivity.checkCombinations(l);
//			    	 Toast.makeText(getApplicationContext(), "firstactivity->"+s, Toast.LENGTH_SHORT).show();
			    	 Bundle b=new Bundle();
						
						b.putStringArrayList("avail_ser", (ArrayList<String>) s); 
			 		 Intent intent = new Intent(getApplicationContext(), AvailablePatternActivity.class);
			 	
			 		intent.putExtras(b);
			    	 startActivity(intent);
			    }
			    });
	        Toast.makeText(getApplicationContext(), "fine", Toast.LENGTH_SHORT).show();
	    	}
	    	catch(Exception e){
	    		 Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
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
}
