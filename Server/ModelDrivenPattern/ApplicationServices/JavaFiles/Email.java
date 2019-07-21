package com.logic;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
public class Email extends Activity implements OnClickListener, InfoInterface {
	EditText editTextEmail, editTextSubject, editTextMessage;
	Button btnSend, btnAttachment;
	String email, subject, message = "", attachmentFile;
	Uri URI = null;
	Uri ImageURI = null;
	Uri VideoURI = null;
	Boolean status = false;
	ArrayList<Uri> uris = new ArrayList<Uri>();
	private static final int PICK_FROM_GALLERY = 101;
	int columnIndex;
	AlertDialog.Builder alertDialogBuilder;
	AlertDialog alertDialog;
	Boolean alerttype = false;
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
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.send_email);
			editTextEmail = (EditText) findViewById(R.id.editTextTo);
			editTextSubject = (EditText) findViewById(R.id.editTextSubject);
			editTextMessage = (EditText) findViewById(R.id.editTextMessage);
			btnAttachment = (Button) findViewById(R.id.buttonAttachment);
			btnSend = (Button) findViewById(R.id.buttonSend);
			btnSend.setOnClickListener(this);
			btnAttachment.setOnClickListener(this);
			if (alerttype) {// without alert adding all attachments and content ....
				if (alldatas.containsKey("email"))
					editTextEmail.setText(alldatas.get("email"));
				if (!locinfomap.isEmpty()) {
					Set<Integer> keys = locinfomap.keySet();
					for (int k : keys) {
						String info = locinfomap.get(k);
						if (!message.contains(info)) {
							message += "\n" + info;
						}
					}
				}

				if (alldatas.containsKey("photo"))
					uris.add(Uri.parse("file://" + alldatas.get("photo")));

				if (alldatas.containsKey("video"))
					uris.add(Uri.parse("file://" + alldatas.get("video")));

				if (alldatas.containsKey("photocyphertextpath")) {
					uris.add(Uri.parse("file://"
							+ alldatas.get("photocyphertextpath")));
					if (uris.contains(Uri.parse("file://"
							+ alldatas.get("photo")))) {
						uris.remove(Uri.parse("file://" + alldatas.get("photo")));
					}
				}

				if (alldatas.containsKey("videocyphertextpath")) {
					uris.add(Uri.parse("file://"
							+ alldatas.get("videocyphertextpath")));

					if (uris.contains(Uri.parse("file://"
							+ alldatas.get("video")))) {
						uris.remove(Uri.parse("file://" + alldatas.get("video")));
					}
				}
				if (alldatas.containsKey("voice"))
					message += "\n" + "Recorded voice " + "\n"
							+ alldatas.get("voice");

				if (alldatas.containsKey("weather"))
					message += "\n Weather Report- \n"
							+ alldatas.get("weather") + "\n";

				if (alldatas.containsKey("deviceinfo"))
					message += "\n" + "DeviceInfo- " + "\n"
							+ alldatas.get("deviceinfo");

			} else {
				if (!locinfomap.isEmpty())
					open("Location",
							"Are you sure,You want to Add Locatin Info");
				else
					attachEmail();
			}

		} catch (Exception e) {
			Toast.makeText(this, "Ex_mailatt:" + e, Toast.LENGTH_LONG).show();
		}
	}

	void attachEmail() {
		// Attching UserEmail
		if (alldatas.containsKey("email"))
			open("Email", "Are you sure,You want to send to selected Email");
		else
			attachImage();
	}

	void attachImage() {
		// Attaching Photo
		if (alldatas.containsKey("photo"))
			open("Photo", "Are you sure,You want to Attach Photo");
		else
			attachVideo();

	}

	void attachVideo() {
		// Attaching Photo
		if (alldatas.containsKey("video"))
			open("Video", "Are you sure,You want to Attach Video");
		else
			attachEncrypted_Image();
	}

	void attachEncrypted_Image() {
		// Attaching Encrypted Photo....
		if (alldatas.containsKey("photocyphertextpath"))
			open("Encrypted_Photo",
					"Are you sure,You want to Attach Encrypted_Image");
		else
			attachEncrypted_Video();

	}

	void attachEncrypted_Video() {
		// Attching Encrypted Video
		if (alldatas.containsKey("videocyphertextpath"))
			open("Encrypted_Video",
					"Are you sure,You want to Attach Encrypted_Video");
		else
			attachVoice();

	}

	void attachVoice() {
		// Attching Voice
		if (alldatas.containsKey("voice"))
			open("Voice", "Are you sure,You want to Add Recorded Voice");
		else
			checkGPSLoc();
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
			checkDeviceInfo();
	}

	void checkDeviceInfo() {
		if (alldatas.containsKey("deviceinfo")) {
			open("deviceinfo", "Are you sure,You want to Add Device Info");
		} else {
			addmsg();
		}
	}

	void addmsg() {
		if (!message.equals("")) {
			editTextMessage.setText(message);
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
			/**
			 * Get Path
			 */
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();
			columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			attachmentFile = cursor.getString(columnIndex);
			uris.add(Uri.parse("file://" + attachmentFile));
			cursor.close();
			Toast.makeText(this, "Attached path " + attachmentFile,
					Toast.LENGTH_LONG).show();
			nextActivity();
		}
	}

	@Override
	public void onClick(View v) {
		try {
			if (v == btnAttachment) {
				openGallery();
			}
		} catch (Exception e) {

		}
		if (v == btnSend) {
			try {
				email = editTextEmail.getText().toString();
				subject = editTextSubject.getText().toString();

				final Intent emailIntent = new Intent(
						android.content.Intent.ACTION_SEND_MULTIPLE);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
						new String[] { email });
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						subject);
				if (!uris.isEmpty()) {
					Toast.makeText(this, "size" + uris.size(),
							Toast.LENGTH_LONG).show();
					emailIntent.putParcelableArrayListExtra(
							Intent.EXTRA_STREAM, uris);
				}
				emailIntent
						.putExtra(android.content.Intent.EXTRA_TEXT, message);
				this.startActivity(Intent.createChooser(emailIntent,
						"Sending email..."));

			} catch (Throwable t) {
				Toast.makeText(this,
						"Request failed try again: " + t.toString(),
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public void openGallery() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.putExtra("return-data", true);
		startActivityForResult(
				Intent.createChooser(intent, "Complete action using"),
				PICK_FROM_GALLERY);
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
							if (title.equalsIgnoreCase("Location")) {
								Set<Integer> keys = locinfomap.keySet();
								for (int k : keys) {
									String info = locinfomap.get(k);
									if (!message.contains(info)) {
										message = message + "\n" + info;
									}
								}
								attachEmail();
							}

							// Attching UserEmail
							if (title.equalsIgnoreCase("Email")) {
								editTextEmail.setText(alldatas.get("email"));
								attachImage();
							}
							// Attaching Photo
							if (title.equalsIgnoreCase("Photo")) {
								uris.add(Uri.parse("file://"
										+ alldatas.get("photo")));
								attachVideo();
							}

							// Attaching Video
							if (title.equalsIgnoreCase("video")) {
								uris.add(Uri.parse("file://"
										+ alldatas.get("video")));
								attachEncrypted_Image();
							}

							// Attaching Encrypted Photo....
							if (title.equalsIgnoreCase("Encrypted_Photo")) {
								uris.add(Uri.parse("file://"
										+ alldatas.get("photocyphertextpath")));
								attachEncrypted_Video();
							}

							// Attching Encrypted Video
							if (title.equalsIgnoreCase("Encrypted_Video")) {
								uris.add(Uri.parse("file://"
										+ alldatas.get("videocyphertextpath")));
								attachVoice();
							}

							// Attching Voice
							if (title.equalsIgnoreCase("Voice")) {
								message += "\n" + "Recorded voice " + "\n"
										+ alldatas.get("voice");
								checkweather();
							}
							if (title.equalsIgnoreCase("Weather")) {
								message += "\n Weather Report- \n"
										+ alldatas.get("weather") + "\n";
								checkDeviceInfo();
							}

							if (title.equalsIgnoreCase("deviceinfo")) {
								message += "\n" + "Device Info- " + "\n"
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
							if (title.equalsIgnoreCase("Location")) {
								attachEmail();
							}
							// Attching UserEmail
							if (title.equalsIgnoreCase("Email")) {
								attachImage();
							}
							// Attaching Photo
							if (title.equalsIgnoreCase("Photo")) {
								attachVideo();
							}

							// Attaching Video
							if (title.equalsIgnoreCase("video")) {
								attachEncrypted_Image();
							}

							// Attaching Encrypted Photo....
							if (title.equalsIgnoreCase("Encrypted_Photo")) {
								attachEncrypted_Video();
							}

							// Attching Encrypted Video
							if (title.equalsIgnoreCase("Encrypted_Video")) {
								checkweather();
							}
							//Adding voice 
							if (title.equalsIgnoreCase("Voice"))
								checkweather();
							//Adding Weather Info
							if (title.equalsIgnoreCase("Weather"))
								checkDeviceInfo();
							//Adding Device Info
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
