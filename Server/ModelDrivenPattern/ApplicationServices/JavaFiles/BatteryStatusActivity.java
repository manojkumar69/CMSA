package com.logic;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BatteryStatusActivity extends Activity {

	TextView textBatteryLevel = null;
	String batteryLevelInfo = "Battery Level";
	Button nextactivity;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battery);
		textBatteryLevel = (TextView) findViewById(R.id.batterylevel);
		nextactivity = (Button) findViewById(R.id.nextactivity);
		registerBatteryLevelReceiver();
		nextactivity.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						TakePhoto.class);
				startActivity(intent);
			}
		});
	}

	/*
	 * Subscription to the Battery related Broadcast events and update the
	 * appropriate UI controls
	 */
	private BroadcastReceiver battery_receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			boolean isPresent = intent.getBooleanExtra("present", false);
			// Battery Technology
			String technology = intent.getStringExtra("technology");
			// Battery Plugged Information
			int plugged = intent.getIntExtra("plugged", -1);
			// Battery Scale
			int scale = intent.getIntExtra("scale", -1);
			// Battery Health
			int health = intent.getIntExtra("health", 0);
			// Battery Charging Status
			int status = intent.getIntExtra("status", 0);
			// Battery charging level
			int rawlevel = intent.getIntExtra("level", -1);
			int level = 0;
			Bundle bundle = intent.getExtras();
			Log.i("BatteryLevel", bundle.toString());
			if (isPresent) {
				if (rawlevel >= 0 && scale > 0) {
					level = (rawlevel * 100) / scale;
				}
				String info = "Battery Level: " + level + "%\n";
				info += ("Technology: " + technology + "\n");
				info += ("Plugged: " + getPlugTypeString(plugged) + "\n");
				info += ("Health: " + getHealthString(health) + "\n");
				info += ("Status: " + getStatusString(status) + "\n");
				setBatteryLevelText(info);
			} else {
				setBatteryLevelText("Battery not present!!!");
			}
		}
	};

	/*
	 * Battery Plugged Information
	 */
	private String getPlugTypeString(int plugged) {
		String plugType = "Unknown";

		switch (plugged) {
		case BatteryManager.BATTERY_PLUGGED_AC:
			plugType = "AC";
			break;
		case BatteryManager.BATTERY_PLUGGED_USB:
			plugType = "USB";
			break;
		}

		return plugType;
	}

	/*
	 * General health of the Battery
	 */
	private String getHealthString(int health) {
		String healthString = "Unknown";

		switch (health) {
		case BatteryManager.BATTERY_HEALTH_DEAD:
			healthString = "Dead";
			break;
		case BatteryManager.BATTERY_HEALTH_GOOD:
			healthString = "Good";
			break;
		case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
			healthString = "Over Voltage";
			break;
		case BatteryManager.BATTERY_HEALTH_OVERHEAT:
			healthString = "Over Heat";
			break;
		case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
			healthString = "Failure";
			break;
		}

		return healthString;
	}

	/*
	 * Charging status of the Battery
	 */
	private String getStatusString(int status) {
		String statusString = "Unknown";

		switch (status) {
		case BatteryManager.BATTERY_STATUS_CHARGING:
			statusString = "Charging";
			break;
		case BatteryManager.BATTERY_STATUS_DISCHARGING:
			statusString = "Discharging";
			break;
		case BatteryManager.BATTERY_STATUS_FULL:
			statusString = "Full";
			break;
		case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
			statusString = "Not Charging";
			break;
		}

		return statusString;
	}

	/*
	 * Battery Status TextView update
	 */
	private void setBatteryLevelText(String text) {
		textBatteryLevel.setText(text);
	}

	/*
	 * Battery Related Broadcast event registration
	 */
	private void registerBatteryLevelReceiver() {
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(battery_receiver, filter);
	}

}
