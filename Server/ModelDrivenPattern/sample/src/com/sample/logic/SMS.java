package com.sample.logic;

import java.util.Set;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
public class SMS extends Activity implements InfoInterface, Runnable {
	String msg = "", phoneno = "";
	EditText mobileno, message;
	Boolean status = false;
	Button sendsms, nextactivity;
	String smscheck = "Single";
	AlertDialog.Builder alertDialogBuilder;
	AlertDialog alertDialog;
	Boolean navtype = false;
	Boolean alertype = false;
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
		setContentView(R.layout.sms_activity);
		mobileno = (EditText) findViewById(R.id.editText1);
		message = (EditText) findViewById(R.id.editText2);
		sendsms = (Button) findViewById(R.id.sendsmsbutton);
		nextactivity = (Button) findViewById(R.id.smsnext);
		message.setText("");
		mobileno.setText("");
		try {
			if (alertype)// without alert adding all attachments....
			{
				if (alldatas.containsKey("phoneno"))
					mobileno.setText(alldatas.get("phoneno"));
				if (alldatas.containsKey("photokey"))
					msg = "Key to decrypt the Photo" + alldatas.get("photokey")
							+ "\n";
				if (alldatas.containsKey("videokey"))
					msg = msg + "\n" + "Key to decrypt the Video"
							+ alldatas.get("videokey") + "\n";
				if (!locinfomap.isEmpty()) {
					Set<Integer> keys = locinfomap.keySet();
					for (int k : keys) {
						String info = locinfomap.get(k);
						if (!msg.contains(info)) {
							msg += "\n  GPSLoc Info- \n" + info;
						}
					}
				}
				if (alldatas.containsKey("weather"))
					msg += "\n Weather Report- \n" + alldatas.get("weather")
							+ "\n";
				if (alldatas.containsKey("voice"))
					msg += "\n" + "Recorded voice- " + "\n"
							+ alldatas.get("voice");
				if (alldatas.containsKey("deviceinfo"))
					msg += "\n" + "DeviceInfo- " + "\n"
							+ alldatas.get("deviceinfo");
			} else {
				if (alldatas.containsKey("phoneno"))
					open("Phoneno",
							"Are you sure,You want to add the Ph no you have selected");
				else
					checkGPSLoc();
				if (alldatas.containsKey("photokey"))
					msg = "Key to decrypt the Photo" + alldatas.get("photokey")
							+ "\n";

				if (alldatas.containsKey("videokey"))
					msg = msg + "\n" + "Key to decrypt the Video"
							+ alldatas.get("videokey") + "\n";
			}
			nextactivity.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					nextActivity();
				}
			});
			sendsms.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					try {
						phoneno = mobileno.getText().toString();
						msg = message.getText().toString();
						if (smscheck.equals("Group")) {
							Toast.makeText(getApplicationContext(), "Group :"+alldatas,
									Toast.LENGTH_LONG).show();
							if (alldatas.containsKey("allphonenos")) {
								String allnos = alldatas.get("allphonenos");
								String arr[] = allnos.split("\\,");
								for (String ph : arr) {
									sendMessage(ph);
									Thread.sleep(500);
								}
							}
						} else if (smscheck.equals("Single")) {
							Toast.makeText(getApplicationContext(), "Single",
									Toast.LENGTH_LONG).show();
							if (alldatas.containsKey("phoneno")) {
								sendMessage(phoneno);
							} else if (!phoneno.trim().equals("")) {
								sendMessage(phoneno);
							}
						}
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "Ex_sms:" + e,
								Toast.LENGTH_LONG).show();
					}
				}
			});
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Ex_sms:" + e,
					Toast.LENGTH_LONG).show();
		}
	}

	void sendMessage(String no) {
		try {
			if (!no.contains("+91")) {
				no = "+91" + no;
			}
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(no, null, msg, null, null);
			Toast.makeText(getApplicationContext(),
					"Message Sent successfully to " + no, Toast.LENGTH_LONG)
					.show();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Msg:" + e,
					Toast.LENGTH_LONG).show();
		}
	}

	void checkGPSLoc() {
		if (!locinfomap.isEmpty())
			open("GPSLoc", "Are you sure,You want to Add Locatin Info");
		else
			checkweather();
	}

	void checkweather() {
		if (alldatas.containsKey("weather"))
			open("Weather", "Are you sure,You want to Add Weather details");
		else
			checkvoice();
	}

	void checkvoice() {
		if (alldatas.containsKey("voice")) {
			open("Voice", "Are you sure,You want to Add Recorded Voice");
		} else {
			checkDeviceInfo();
		}
	}

	void checkDeviceInfo() {
		if (alldatas.containsKey("deviceinfo")) {
			open("deviceinfo", "Are you sure,You want to Add Device Info");
		} else {
			addmsg();
		}
	}

	void addmsg() {
		if (!msg.equals("")) {
			message.setText(msg);
		}
	}

	public void open(final String title, String alertmsg) {
		try {
			alertDialogBuilder = new AlertDialog.Builder(this);

			alertDialogBuilder.setTitle(title);
			alertDialogBuilder.setMessage(alertmsg);
			alertDialogBuilder.setPositiveButton("yes",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (title.equalsIgnoreCase("Phoneno")) {
								mobileno.setText(alldatas.get("phoneno"));
								checkGPSLoc();
							}
							if (title.equalsIgnoreCase("GPSLoc")) {
								Set<Integer> keys = locinfomap.keySet();
								for (int k : keys) {
									String info = locinfomap.get(k);
									if (!msg.contains(info)) {
										msg += "\n  GPSLoc Info- \n" + info;
									}
								}
								checkweather();
							}
							if (title.equalsIgnoreCase("Weather")) {
								msg += "\n\n Weather Report- \n"
										+ alldatas.get("weather") + "\n";
								checkvoice();
							}
							if (title.equalsIgnoreCase("Voice")) {
								msg += "\n" + "Recorded voice- " + "\n"
										+ alldatas.get("voice");
								checkDeviceInfo();
							}
							if (title.equalsIgnoreCase("deviceinfo")) {
								msg += "\n" + "Device Info- " + "\n"
										+ alldatas.get("deviceinfo");
								addmsg();
							}
						}
					});
			alertDialogBuilder.setNegativeButton("No",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							alertDialog.dismiss();
							if (title.equalsIgnoreCase("Phoneno"))
								checkGPSLoc();
							if (title.equalsIgnoreCase("GPSLoc"))
								checkweather();
							if (title.equalsIgnoreCase("Weather"))
								checkvoice();
							if (title.equalsIgnoreCase("Voice"))
								checkDeviceInfo();
							if (title.equalsIgnoreCase("deviceinfo"))
								addmsg();

						}
					});
			alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		} catch (Exception e) {
			Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

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
