package com.example.mdp;


import com.access.InitiateServer;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends ActionBarActivity {
	String msg = "", ph = "", ip, portno, services, phone;
	EditText mobileno, message;
	Boolean status = false;
	Button sendsms, buildapp, buttondownload;
	EditText phoneno;
	SharedPreferences pref;
	Boolean Smsstatus = false;
	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_sms);
			phoneno = (EditText) findViewById(R.id.editTextphoneno);
			sendsms = (Button) findViewById(R.id.sendbutton);
			buildapp = (Button) findViewById(R.id.buildbutton);
			buttondownload = (Button) findViewById(R.id.scanbutton);
			pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
			ip = pref.getString("ipaddress", null);
			portno = pref.getString("portno", null);
			phone = pref.getString("phoneno", null);
			msg = pref.getString("msg", null);
			services = pref.getString("services", null);
			phoneno.setText(phone);
			Toast.makeText(getApplicationContext(), "selected service==>" + services, Toast.LENGTH_LONG).show();

			sendsms.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					try {
						String phoneNo = phoneno.getText().toString();
						if (!phoneNo.contains("+91")) {
							phoneNo = "+91" + phoneNo;
						}
						SmsManager sms = SmsManager.getDefault();
						sms.sendTextMessage(phoneNo, null, msg, null, null);
						Toast.makeText(getApplicationContext(), "Message Sent Successfully!" + phoneNo,
								Toast.LENGTH_LONG).show();
						Smsstatus = true;
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "msg:" + e, Toast.LENGTH_LONG).show();
					}
				}
			});
			buildapp.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (Smsstatus) {
						new BuildMicroApplication().execute();// Third module...
					} else
						Toast.makeText(getApplicationContext(), "Send SMS to receive your Password", Toast.LENGTH_LONG)
								.show();
				}
			});
			buttondownload.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getApplicationContext(), ScanQRCodeActivity.class);
					startActivity(intent);
				}
			});
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "sms==>" + e, Toast.LENGTH_LONG).show();
		}
	}

	/** Building MicroApplication Class */
	private class BuildMicroApplication extends AsyncTask<String, String, String> {

		// Show Progress ..
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Shows Progress Dialog and then call doInBackground method
			try {
				dialog = ProgressDialog.show(SMSActivity.this, null, getString(R.string.building_dialog));
			} catch (Exception e) {
				showToast("" + e);
			}
		}

		@Override
		protected String doInBackground(String... param) {
			String status = "";
			try {
				status = InitiateServer.buildMicroapp(ip, portno, services);
			} catch (Exception e) {
				showToast("" + e);
			}
			return status;
		}

		@Override
		protected void onPostExecute(String result) {
			try {
				dialog.dismiss();
				showToast("Building apps " + result);
			} catch (Exception e) {
				showToast("" + e);
			}
		}
	}

	private void showToast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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
		case R.id.action_logout:
			SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
			pref.edit().clear().commit();
			Intent inten = new Intent(this, SigninActivity.class);
			startActivity(inten);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.findItem(R.id.action_avail_comb).setVisible(false);
		menu.findItem(R.id.action_scan).setVisible(false);
		menu.findItem(R.id.action_check_comb).setVisible(false);
		return super.onPrepareOptionsMenu(menu);
	}

}
