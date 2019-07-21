package com.logic;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
public class PhoneStatusActivity extends Activity implements InfoInterface{
	Button nextact;
	String alldevicestatus="";
	TextView textBatteryLevel = null;
	/*
	 * Signal level
	 */
	private static final int EXCELLENT_LEVEL = 75;
	private static final int GOOD_LEVEL = 50;
	private static final int MODERATE_LEVEL = 25;
	private static final int WEAK_LEVEL = 0;

	private static final int INFO_SERVICE_STATE_INDEX = 0;
	private static final int INFO_CELL_LOCATION_INDEX = 1;
	private static final int INFO_CALL_STATE_INDEX = 2;
	private static final int INFO_CONNECTION_STATE_INDEX = 3;
	private static final int INFO_SIGNAL_LEVEL_INDEX = 4;
	private static final int INFO_SIGNAL_LEVEL_INFO_INDEX = 5;
	private static final int INFO_DATA_DIRECTION_INDEX = 6;
	private static final int INFO_DEVICE_INFO_INDEX = 7;

	private static final int[] info_ids = { R.id.serviceState_info,
			R.id.cellLocation_info, R.id.callState_info,
			R.id.connectionState_info, R.id.signalLevel, R.id.signalLevelInfo,
			R.id.dataDirection, R.id.device_info };
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phonestatus);
		nextact = (Button) findViewById(R.id.nextact);
		textBatteryLevel = (TextView) findViewById(R.id.batterylevel);
		Log.i("ONCREATE", "<<------- START ------- >>");
		startSignalLevelListener();
		displayTelephonyInfo();
		Log.i("ONCREATE", "<<------- END ------- >>");
		nextact.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				alldatas.put("deviceinfo", alldevicestatus);
				nextActivity();
			}
		});
	}

	@Override
	protected void onStop() {
		super.onStop();
		// Stop listening to the telephony events
		StopListener();

	}

	@Override
	protected void onPause() {
		super.onPause();
		// Stop listening to the telephony events
		StopListener();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// subscribes to the telephony related events
		startSignalLevelListener();
	}

	/*
	 * Sets the textview contents
	 */
	private void setTextViewText(int id, String text) {
		((TextView) findViewById(id)).setText(text);
	}

	/*
     * 
     * */
	private void startSignalLevelListener() {

		Log.i("startSignalLevelListener", "<<------- START ------- >>");
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		@SuppressWarnings("deprecation")
		int events = PhoneStateListener.LISTEN_SIGNAL_STRENGTH
				| PhoneStateListener.LISTEN_DATA_ACTIVITY
				| PhoneStateListener.LISTEN_CELL_LOCATION
				| PhoneStateListener.LISTEN_CALL_STATE
				| PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR
				| PhoneStateListener.LISTEN_DATA_CONNECTION_STATE
				| PhoneStateListener.LISTEN_SERVICE_STATE;

		tm.listen(phoneListener, events);
		Log.i("startSignalLevelListener", "<<------- END ------- >>");
	}

	/*
	 * De-register the telephony events
	 */
	private void StopListener() {
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.listen(phoneListener, PhoneStateListener.LISTEN_NONE);
	}

	/*
	 * Display the telephony related information
	 */
	private void displayTelephonyInfo() {

		Log.i("displayTelephonyInfo", "<<------- START ------- >>");
		// access to the telephony services
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		// access to the gsm info ,..requires ACCESS_FINE_LOCATION and
		// ACCESS_COARSE_LOCATION permission
		GsmCellLocation gsmLoc = (GsmCellLocation) tm.getCellLocation();

		// Get the IMEI code
		String deviceid = tm.getDeviceId();
		// Get the phone number string for line 1, for example, the MSISDN for a
		// GSM phone
		String phonenumber = tm.getLine1Number();
		// Get the software version number for the device, for example, the
		// IMEI/SV for GSM phones
		String softwareversion = tm.getDeviceSoftwareVersion();
		// Get the alphabetic name of current registered operator.
		String operatorname = tm.getNetworkOperatorName();
		// Get the ISO country code equivalent for the SIM provider's country
		// code.
		String simcountrycode = tm.getSimCountryIso();
		// Get the Service Provider Name (SPN).
		String simoperator = tm.getSimOperatorName();
		// Get the serial number of the SIM, if applicable. Return null if it is
		// unavailable.
		String simserialno = tm.getSimSerialNumber();
		// Get the unique subscriber ID, for example, the IMSI for a GSM phone
		String subscriberid = tm.getSubscriberId();
		// Get the type indicating the radio technology (network type) currently
		// in use on the device for data transmission.
		// EDGE,GPRS,UMTS etc
		String networktype = getNetworkTypeString(tm.getNetworkType());
		// indicating the device phone type. This indicates the type of radio
		// used to transmit voice calls
		// GSM,CDMA etc
		String phonetype = getPhoneTypeString(tm.getPhoneType());

		String deviceinfo = "";

		deviceinfo += ("Device ID: " + deviceid + "\n");
		deviceinfo += ("Phone Number: " + phonenumber + "\n");
		deviceinfo += ("Software Version: " + softwareversion + "\n");
		deviceinfo += ("Operator Name: " + operatorname + "\n");
		deviceinfo += ("SIM Country Code: " + simcountrycode + "\n");
		deviceinfo += ("SIM Operator: " + simoperator + "\n");
		deviceinfo += ("SIM Serial No.: " + simserialno + "\n");
		deviceinfo += ("Subscriber ID: " + subscriberid + "\n");
		deviceinfo += ("Network Type: " + networktype + "\n");
		deviceinfo += ("Phone Type: " + phonetype + "\n");
		
		String stat="Service status-"+deviceinfo;
		if(alldevicestatus.equals(""))
			alldevicestatus=stat;
		else
			alldevicestatus+="\n"+stat;
		setTextViewText(info_ids[INFO_DEVICE_INFO_INDEX], deviceinfo);

	}

	private void setDataDirection(int id, int direction) {
		int resid = getDataDirectionRes(direction);
		// ((TextView)findViewById(id)).setCompoundDrawables(null,
		// null,getResources().getDrawable(resid), null);
		((ImageView) findViewById(id)).setImageResource(resid);
	}

	private int getDataDirectionRes(int direction) {
		int resid = R.drawable.nodata;

		switch (direction) {
		case TelephonyManager.DATA_ACTIVITY_IN:
			resid = R.drawable.indata; 
			break;
		case TelephonyManager.DATA_ACTIVITY_OUT:
			resid = R.drawable.outdata;
			break;
		case TelephonyManager.DATA_ACTIVITY_INOUT:
			resid = R.drawable.bidata;
			break;
		case TelephonyManager.DATA_ACTIVITY_NONE:
			resid = R.drawable.nodata;
			break;
		default:
			resid = R.drawable.nodata;
			break;
		}

		return resid;
	}

	private void setSignalLevel(int id, int infoid, int level) {

		int progress = (int) ((((float) level) / 31.0) * 100);

		String signalLevelString = getSignalLevelString(progress);

		// set the status
		((ProgressBar) findViewById(id)).setProgress(progress);		
		// set the status string
		((TextView) findViewById(infoid)).setText(signalLevelString);

		Log.i("signalLevel ", "" + progress);
	}

	private String getSignalLevelString(int level) {

		String signalLevelString = "Weak";

		if (level > EXCELLENT_LEVEL)
			signalLevelString = "Excellent";
		else if (level > GOOD_LEVEL)
			signalLevelString = "Good";
		else if (level > MODERATE_LEVEL)
			signalLevelString = "Moderate";
		else if (level > WEAK_LEVEL)
			signalLevelString = "Weak";
		
		return signalLevelString;
	}

	private String getNetworkTypeString(int type) {
		String typeString = "Unknown";

		switch (type) {
		case TelephonyManager.NETWORK_TYPE_EDGE:
			typeString = "EDGE";
			break;
		case TelephonyManager.NETWORK_TYPE_GPRS:
			typeString = "GPRS";
			break;
		case TelephonyManager.NETWORK_TYPE_UMTS:
			typeString = "UMTS";
			break;
		default:
			typeString = "UNKNOWN";
			break;
		}

		return typeString;
	}

	private String getPhoneTypeString(int type) {
		String typeString = "Unknown";

		switch (type) {
		case TelephonyManager.PHONE_TYPE_GSM:
			typeString = "GSM";
			break;
		case TelephonyManager.PHONE_TYPE_NONE:
			typeString = "UNKNOWN";
			break;
		default:
			typeString = "UNKNOWN";
			break;
		}

		return typeString;
	}

	private final PhoneStateListener phoneListener = new PhoneStateListener() {

		/*
		 * call fwding
		 */
		public void onCallForwardingIndicatorChanged(boolean cfi) {

			super.onCallForwardingIndicatorChanged(cfi);
		}

		/*
		 * Call State Changed
		 */
		public void onCallStateChanged(int state, String incomingNumber) {

			String phoneState = "UNKNOWN";

			switch (state) {

			case TelephonyManager.CALL_STATE_IDLE:
				phoneState = "IDLE";
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				phoneState = "Ringing (" + incomingNumber + ") ";
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				phoneState = "Offhook";
				break;

			}

			setTextViewText(info_ids[INFO_CALL_STATE_INDEX], phoneState);
			super.onCallStateChanged(state, incomingNumber);

		}

		/*
		 * Cell location changed event handler
		 */
		public void onCellLocationChanged(CellLocation location) {

			String strLocation = location.toString();

			setTextViewText(info_ids[INFO_CELL_LOCATION_INDEX], strLocation);

			super.onCellLocationChanged(location);
		}

		/*
		 * Cellphone data connection status
		 */
		public void onDataConnectionStateChanged(int state) {

			String phoneState = "UNKNOWN";

			switch (state) {

			case TelephonyManager.DATA_CONNECTED:
				phoneState = "Connected";
				break;
			case TelephonyManager.DATA_CONNECTING:
				phoneState = "Connecting..";
				break;
			case TelephonyManager.DATA_DISCONNECTED:
				phoneState = "Disconnected";
				break;
			case TelephonyManager.DATA_SUSPENDED:
				phoneState = "Suspended";
				break;
			}

			setTextViewText(info_ids[INFO_CONNECTION_STATE_INDEX], phoneState);

			super.onDataConnectionStateChanged(state);
		}

		/*
		 * Data activity handler
		 */
		public void onDataActivity(int direction) {

			String strDirection = "NONE";

			switch (direction) {

			case TelephonyManager.DATA_ACTIVITY_IN:
				strDirection = "IN";
				break;
			case TelephonyManager.DATA_ACTIVITY_INOUT:
				strDirection = "IN-OUT";
				break;
			case TelephonyManager.DATA_ACTIVITY_DORMANT:
				strDirection = "Dormant";
				break;
			case TelephonyManager.DATA_ACTIVITY_NONE:
				strDirection = "NONE";
				break;
			case TelephonyManager.DATA_ACTIVITY_OUT:
				strDirection = "OUT";
				break;

			}

			setDataDirection(info_ids[INFO_DATA_DIRECTION_INDEX], direction);
			String stat="Data activity-"+strDirection;
			if(alldevicestatus.equals(""))
				alldevicestatus=stat;
			else
				alldevicestatus+="\n"+stat;
			super.onDataActivity(direction);
		}

		/*
		 * Cellphone Service status
		 */
		public void onServiceStateChanged(ServiceState serviceState) {

			String strServiceState = "NONE";

			switch (serviceState.getState()) {

			case ServiceState.STATE_EMERGENCY_ONLY:
				strServiceState = "Emergency";
				break;

			case ServiceState.STATE_IN_SERVICE:
				strServiceState = "In Service";
				break;
			case ServiceState.STATE_OUT_OF_SERVICE:
				strServiceState = "Out of Service";
				break;
			case ServiceState.STATE_POWER_OFF:
				strServiceState = "Power off";
				break;
			}

			setTextViewText(info_ids[INFO_SERVICE_STATE_INDEX], strServiceState);
			String stat="Service status-"+strServiceState;
			if(alldevicestatus.equals(""))
				alldevicestatus=stat;
			else
				alldevicestatus+="\n"+stat;
			super.onServiceStateChanged(serviceState);

		}

		/*
		 * 
		 * */
		public void onSignalStrengthChanged(int asu) {

			setSignalLevel(info_ids[INFO_SIGNAL_LEVEL_INDEX],
					info_ids[INFO_SIGNAL_LEVEL_INFO_INDEX], asu);

			super.onSignalStrengthChanged(asu);
		}

	};
	//Battery Information.....
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
				Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();
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
		String stat="Battery Info-"+text;
		if(alldevicestatus.equals(""))
			alldevicestatus=stat;
		else
			alldevicestatus+="\n"+stat;
		
	}

	/*
	 * Battery Related Broadcast event registration
	 */
	private void registerBatteryLevelReceiver() {
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(battery_receiver, filter);
	}
	private void nextActivity() {
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
