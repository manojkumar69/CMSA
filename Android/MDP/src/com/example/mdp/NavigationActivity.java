package com.example.mdp;

import java.util.HashMap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



public class NavigationActivity extends ActionBarActivity {
	RadioGroup radioGroupalert;
	RadioGroup radioGroupnavigation;
	RadioButton alert, nav;
	Button nextactivity;
	SharedPreferences pref;
	String alerttype = "", navtype = "";

	public void onCreate(Bundle savedInstancestate) {
		try {
			super.onCreate(savedInstancestate);
			setContentView(R.layout.activity_microapp_ui); 
			radioGroupalert = (RadioGroup) findViewById(R.id.radioalert);
			radioGroupnavigation = (RadioGroup) findViewById(R.id.radiopage);
			nextactivity = (Button) findViewById(R.id.btnnext);
			nextactivity.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
						// get selected radio button from radioGroup
						int selectedId = radioGroupalert.getCheckedRadioButtonId();
						String services = pref.getString("services", null);
						alert = (RadioButton) findViewById(selectedId);
						alerttype = alert.getText().toString();
						int selectedId1 = radioGroupnavigation.getCheckedRadioButtonId();
						// find the radiobutton by returned id
						nav = (RadioButton) findViewById(selectedId1);
						navtype = nav.getText().toString();
						services += "@" + alerttype + "@" + navtype;
						Editor edit = pref.edit();
						edit.putString("services", services);// updating the  navtype												
						edit.commit();
						Intent smsintent = new Intent(getApplicationContext(), SMSActivity.class);
						startActivity(smsintent);
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "EX_values:" + e, Toast.LENGTH_LONG).show();
					}
				}
			});
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Ex:" + e, Toast.LENGTH_LONG).show();
		}

	}
}
