package com.example.mdp;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.access.InitiateServer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedServices extends Activity implements services{
	TextView services;
	List<String> serviceList ;
	String serviceStr="",smstype="Single"; 
	String ip, port, phoneno;
	SharedPreferences pref;
	HashMap<String, String> keystoremap = new HashMap<String, String>();
	Button get_key_store_button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.selectedservice);
		services = (TextView) findViewById(R.id.services);
		get_key_store_button=(Button) findViewById(R.id.button1);
		pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//		=pref.getStringSet("services", null);
		Bundle bundle = getIntent().getExtras();
		serviceList= (ArrayList<String>)bundle.getStringArrayList("ser");
		StringBuilder sb=new StringBuilder();
		int i=1;
		serviceStr="";
		for(String ser: serviceList){
//			Toast.makeText(getApplicationContext(),"===>"+ ser,0).show();
			sb.append(i+"."+ser.toUpperCase().substring(0,ser.length()-1)+"\n\n");
			if(serviceStr.equals("")){
				serviceStr += serviceNames(ser);
			}
			else{
				serviceStr+=",";
				serviceStr += serviceNames(ser);
			}
			i++;
		}
		services.setText(sb);
		get_key_store_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getKeyStore();

			}
		});
	}
	public void showDialog(String names) {
		try {
			int i=1;
			serviceStr="";
			
			for(String ser: serviceList){
//				Toast.makeText(getApplicationContext(),"===>"+ ser,0).show();
//				sb.append(i+"."+ser.toUpperCase().substring(0,ser.length()-1)+"\n\n");
				if(serviceStr.equals("")){
					serviceStr += serviceNames(ser);
				}
				else{
					serviceStr+=",";
					serviceStr += serviceNames(ser);
				}
				i++;
			}
			String alldetails[] = names.split(",");

		
			String all = "";
			for (String s : alldetails) {
				all += s.split("@")[0] + ",";
				String password = s.split("@")[1];
				keystoremap.put(s.split("@")[0], password);
			}
			final String items[] = all.split(",");
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setTitle("Choose KeyStore File");
			alertDialogBuilder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					
//					Toast.makeText(getApplicationContext(),
//							 "You selected item No." + item + ": " + items[item],
//							 Toast.LENGTH_SHORT).show();
					String selecteditem = items[item].toString().split("@")[0];
					serviceStr += "@" + selecteditem + "@" + smstype;
					Toast.makeText(getApplicationContext(),
							 "servicestr===>" +serviceStr,
							 Toast.LENGTH_SHORT).show();
					dialog.dismiss();
					// soap call ...
					sendingSms(selecteditem);
					// sendAllRequirements();
				}
			});
			alertDialogBuilder.show();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "show diag:" + e, Toast.LENGTH_LONG).show();
		}

	}
	public void sendingSms(String item) {
		String password = keystoremap.get(item);
		String msg = "Your keystore password to release your Micro-App : " + password;
		Editor editor = pref.edit();
		editor.putString("msg", msg);
		editor.putString("services", serviceStr);
		editor.commit();
		Intent navintent = new Intent(getApplicationContext(), NavigationActivity.class);
		startActivity(navintent);
	}
	private String serviceNames(String name){
		
		return servMap.get(name);
	}
	public void getKeyStore(){
//		Toast.makeText(getApplicationContext(), "selected serv===>" + services, Toast.LENGTH_LONG).show();
		Boolean status = AvailableCombinationsActivity.checkCombinations(serviceList);
		status=true;
		if (status) {
//			Toast.makeText(getApplicationContext(), "service available===>" + services,
//					Toast.LENGTH_LONG).show();
			/**************** Get SharedPreferences data *******************/
			
			ip = pref.getString("ipaddress", null);
			port = pref.getString("portno", null);
			phoneno = pref.getString("phoneno", null);
			String st = InitiateServer.getKeyStoreNames(ip, port);
			showDialog(st);
		} else {
			Toast.makeText(getApplicationContext(), "Plz Select Other Combinations" + services,
					Toast.LENGTH_LONG).show();
		}
			
	}
} 
