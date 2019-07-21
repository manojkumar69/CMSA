package com.logic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
public class GPSLocation extends Activity implements InfoInterface {

	private String nextActivity = "Acivity";
	Button btnGPSShowLocation, nextservice;
	Button btnShowAddress;
	TextView tvAddress;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.gpslocation_activity);
			tvAddress = (TextView) findViewById(R.id.tvAddress);
			nextservice = (Button) findViewById(R.id.next);
			// btnGPSShowLocation = (Button)
			// findViewById(R.id.btnGPSShowLocation);
			btnShowAddress = (Button) findViewById(R.id.btnShowAddress);
			// appLocationService = new
			// AppLocationService(getApplicationContext());

			nextservice.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					nextActivity();
				}
			});
			// btnGPSShowLocation.setOnClickListener(new View.OnClickListener()
			// {
			// @Override
			// public void onClick(View arg0) {
			// Location gpsLocation = appLocationService
			// .getLocation(LocationManager.GPS_PROVIDER);
			// if (gpsLocation != null) {
			// double latitude = gpsLocation.getLatitude();
			// double longitude = gpsLocation.getLongitude();
			// String result = "Latitude: "
			// + gpsLocation.getLatitude() + " Longitude: "
			// + gpsLocation.getLongitude();
			// tvAddress.setText(result);
			// locinfomap.put(1, result);
			// } else {
			// showSettingsAlert();
			// }
			// }
			// });
			btnShowAddress.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					try {
						GPSTracker location = new GPSTracker(GPSLocation.this);
						if (location != null) {
							double latitude = location.getLatitude();
							double longitude = location.getLongitude();
							LocationAddress locationAddress = new LocationAddress();
							locationAddress.getAddressFromLocation(latitude,
									longitude, getApplicationContext(),
									new GeocoderHandler());
						} else {
							showSettingsAlert();
						}
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "" + e,
								Toast.LENGTH_LONG).show();
					}

				}
			});
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG)
					.show();
		}

	}

	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				GPSLocation.this);
		alertDialog.setTitle("SETTINGS");
		alertDialog
				.setMessage("Enable Location Provider! Go to settings menu?");
		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						GPSLocation.this.startActivity(intent);
					}
				});
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		alertDialog.show();
	}

	private class GeocoderHandler extends Handler {
		@Override
		public void handleMessage(Message message) {
			String locationAddress = "";
			switch (message.what) {
			case 1:
				Bundle bundle = message.getData();
				locationAddress = bundle.getString("address");
				break;
			default:
				locationAddress = null;
			}
			tvAddress.setText(locationAddress);
			locinfomap.put(1, locationAddress);
		}
	}
}
