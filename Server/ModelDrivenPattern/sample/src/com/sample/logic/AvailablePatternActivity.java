package com.sample.logic;

import java.util.ArrayList;
import android.view.Menu;
import android.view.MenuItem;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AvailablePatternActivity extends Activity implements OnClickListener,InfoInterface  {
	ArrayList<String> serviceList;
	RadioGroup ll;
	RadioButton   selectedRadioButton; 
	AlertDialog.Builder alertDialogBuilder; 
	Button submit;
	AlertDialog alertDialog;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_pattern);
        clrInterface();
        Bundle bundle = getIntent().getExtras();
        serviceList= (ArrayList<String>)bundle.getStringArrayList("avail_ser");
//        Toast.makeText(getApplicationContext(), "available services--->"+serviceList, Toast.LENGTH_SHORT).show();
        addRadioButtons();
        
        submit=(Button) findViewById(R.id.selectpattern);
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 int selectedId = ll.getCheckedRadioButtonId();
				  if(selectedId>1){
					  // find the radiobutton by returned id
					  selectedRadioButton  = (RadioButton) findViewById(selectedId);
					final String selected_pattern= selectedRadioButton.getText().toString();
			            Toast.makeText(getApplicationContext(),"selected pattern"+ 
			            		selectedRadioButton.getText(), Toast.LENGTH_SHORT).show();
			            AlertDialog.Builder builder = new AlertDialog.Builder(AvailablePatternActivity.this);
	 
			            builder.setTitle("Confirm");
			            builder.setMessage("Are you sure to make as default pattern?");

			            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			                public void onClick(DialogInterface dialog, int which) {
			                    // Do nothing but close the dialog

			                	 SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
			 					Editor editor = pref.edit();
			 					editor.putString("pattern","custom");
			 					editor.putString("selectedpattern",selected_pattern);
			 					editor.putInt("activity_count", 1);
			 					editor.commit(); // commit changes 
			 					String s=selected_pattern.split(",")[0];
			 					 Intent intent = new Intent(getApplicationContext(),FirstActivity.classMap.get(s));
			 			    	 startActivity(intent);
			                }
			            });

			            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			                @Override
			                public void onClick(DialogInterface dialog, int which) {

			                    // Do nothing
			                    dialog.dismiss();
			                }
			            });

			            AlertDialog alert = builder.create();
			            alert.show(); 
				  }
				  else{
					   Toast.makeText(getApplicationContext(),"selected atleast one pattern", Toast.LENGTH_SHORT).show();  
				  }
			}
		});
    }

    public void addRadioButtons() {

    for (int row = 1; row < 2; row++) {
    	ll  = new RadioGroup(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i <= serviceList.size()-1; i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId((row * 2) + i);
            rdbtn.setText(serviceList.get(i));
            ll.addView(rdbtn);
        }
        ((ViewGroup) findViewById(R.id.radiogroup)).addView(ll);
    }

  }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


}
 
