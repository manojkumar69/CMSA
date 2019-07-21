package com.logic;

import java.io.File;
import android.view.Menu;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ContactsList extends Activity implements InfoInterface {
	SimpleCursorAdapter mAdapter;
	MatrixCursor mMatrixCursor;
	Button nextactivity;
	ArrayList<String> phonenolist = new ArrayList<String>();
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
		setContentView(R.layout.contacts_activity);
		nextactivity = (Button) findViewById(R.id.contactsnext);
		nextactivity.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				nextActivity();
			}
		});
		// The contacts from the contacts content provider is stored in this
		// cursor
		mMatrixCursor = new MatrixCursor(new String[] { "_id", "name", "photo",
				"details" });

		// Adapter to set data in the listview
		mAdapter = new SimpleCursorAdapter(getBaseContext(),
				R.layout.lv_layout, null, new String[] { "name", "photo",
						"details" }, new int[] { R.id.tv_name, R.id.iv_photo,
						R.id.tv_details }, 0);

		// Getting reference to listview
		ListView lstContacts = (ListView) findViewById(R.id.lst_contacts);

		// Setting the adapter to listview
		lstContacts.setAdapter(mAdapter);

		// Creating an AsyncTask object to retrieve and load listview with
		// contacts
		ListViewContactsLoader listViewContactsLoader = new ListViewContactsLoader();

		// Starting the AsyncTask process to retrieve and load listview with 
		// contacts
		listViewContactsLoader.execute();
		lstContacts
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent,
							final View view, int position, long id) {
						String pdetails = phonenolist.get(position).toString();
						String arr[] = pdetails.split("\n");
						if (arr != null) {
							String phoneno = "";
							for (String s : arr) {
								if (s.contains("MobilePhone :")) {
									if (phoneno.equals(""))
										phoneno = s.substring(13).trim();
									else
										phoneno += "," + s.substring(13).trim();
									alldatas.put("phoneno", s.substring(13)
											.trim());
									Toast.makeText(
											getApplicationContext(),
											"Selected List Phoneno===>"
													+ s.substring(13).trim(),
											Toast.LENGTH_LONG).show();
								}
								if (s.contains("WorkEmail :")) {
									alldatas.put("email", s.substring(11)
											.trim());
									Toast.makeText(
											getApplicationContext(),
											"Selected List Email===>"
													+ s.substring(11).trim(),
											Toast.LENGTH_LONG).show();
								}
							}
							alldatas.put("allphonenos", phoneno);
						}
					}
				});
	}

	/** An AsyncTask class to retrieve and load listview with contacts */
	private class ListViewContactsLoader extends AsyncTask<Void, Void, Cursor> {
		@Override
		protected Cursor doInBackground(Void... params) {
			Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
			// Querying the table ContactsContract.Contacts to retrieve all the
			// contacts
			Cursor contactsCursor = getContentResolver().query(contactsUri,
					null, null, null,
					ContactsContract.Contacts.DISPLAY_NAME + " ASC ");
			if (contactsCursor.moveToFirst()) {
				do {
					long contactId = contactsCursor.getLong(contactsCursor
							.getColumnIndex("_ID"));
					Uri dataUri = ContactsContract.Data.CONTENT_URI;

					// Querying the table ContactsContract.Data to retrieve
					// individual items like
					// home phone, mobile phone, work email etc corresponding to
					// each contact
					Cursor dataCursor = getContentResolver().query(dataUri,
							null,
							ContactsContract.Data.CONTACT_ID + "=" + contactId,
							null, null);
					String displayName = "";
					String nickName = "";
					String homePhone = "";
					String mobilePhone = "";
					String workPhone = "";
					// String photoPath="" + R.drawable.blank;
					String photoPath = "";
					byte[] photoByte = null;
					String homeEmail = "";
					String workEmail = "";
					String companyName = "";
					String title = "";
					if (dataCursor.moveToFirst()) {
						// Getting Display Name
						displayName = dataCursor
								.getString(dataCursor
										.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
						do {
							// Getting NickName
							if (dataCursor
									.getString(
											dataCursor
													.getColumnIndex("mimetype"))
									.equals(ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE))
								nickName = dataCursor.getString(dataCursor
										.getColumnIndex("data1"));
							// Getting Phone numbers
							if (dataCursor
									.getString(
											dataCursor
													.getColumnIndex("mimetype"))
									.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
								switch (dataCursor.getInt(dataCursor
										.getColumnIndex("data2"))) {
								case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
									homePhone = dataCursor.getString(dataCursor
											.getColumnIndex("data1"));
									break;
								case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
									mobilePhone = dataCursor
											.getString(dataCursor
													.getColumnIndex("data1"));
									break;
								case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
									workPhone = dataCursor.getString(dataCursor
											.getColumnIndex("data1"));
									break;
								}
							}
							// Getting EMails
							if (dataCursor
									.getString(
											dataCursor
													.getColumnIndex("mimetype"))
									.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {
								switch (dataCursor.getInt(dataCursor
										.getColumnIndex("data2"))) {
								case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
									homeEmail = dataCursor.getString(dataCursor
											.getColumnIndex("data1"));
									break;
								case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
									workEmail = dataCursor.getString(dataCursor
											.getColumnIndex("data1"));
									break;
								}
							}
							// Getting Organization details
							if (dataCursor
									.getString(
											dataCursor
													.getColumnIndex("mimetype"))
									.equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)) {
								companyName = dataCursor.getString(dataCursor
										.getColumnIndex("data1"));
								title = dataCursor.getString(dataCursor
										.getColumnIndex("data4"));
							}
							// Getting Photo
							if (dataCursor
									.getString(
											dataCursor
													.getColumnIndex("mimetype"))
									.equals(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)) {
								photoByte = dataCursor.getBlob(dataCursor
										.getColumnIndex("data15"));
								if (photoByte != null) {
									Bitmap bitmap = BitmapFactory
											.decodeByteArray(photoByte, 0,
													photoByte.length);

									// Getting Caching directory
									File cacheDirectory = getBaseContext()
											.getCacheDir();

									// Temporary file to store the contact image
									File tmpFile = new File(
											cacheDirectory.getPath() + "/wpta_"
													+ contactId + ".png");

									// The FileOutputStream to the temporary
									// file
									try {
										FileOutputStream fOutStream = new FileOutputStream(
												tmpFile);

										// Writing the bitmap to the temporary
										// file as png file
										bitmap.compress(
												Bitmap.CompressFormat.PNG, 100,
												fOutStream);

										// Flush the FileOutputStream
										fOutStream.flush();

										// Close the FileOutputStream
										fOutStream.close();

									} catch (Exception e) {
										e.printStackTrace();
									}

									photoPath = tmpFile.getPath();
								}
							}
						} while (dataCursor.moveToNext());
						String details = "";
						// Concatenating various information to single string
						if (homePhone != null && !homePhone.equals(""))
							details = "HomePhone : " + homePhone + "\n";
						if (mobilePhone != null && !mobilePhone.equals(""))
							details += "MobilePhone :" + mobilePhone + "\n";
						if (workPhone != null && !workPhone.equals(""))
							details += "WorkPhone : " + workPhone + "\n";
						if (nickName != null && !nickName.equals(""))
							details += "NickName : " + nickName + "\n";
						if (homeEmail != null && !homeEmail.equals(""))
							details += "HomeEmail : " + homeEmail + "\n";
						if (workEmail != null && !workEmail.equals(""))
							details += "WorkEmail : " + workEmail + "\n";
						if (companyName != null && !companyName.equals(""))
							details += "CompanyName : " + companyName + "\n";
						if (title != null && !title.equals(""))
							details += "Title : " + title + "\n";
						phonenolist.add(details);
						// Adding id, display name, path to photo and other
						// details to cursor
						mMatrixCursor.addRow(new Object[] {
								Long.toString(contactId), displayName,
								photoPath, details });
					}
				} while (contactsCursor.moveToNext());
			}
			return mMatrixCursor;
		}

		@Override
		protected void onPostExecute(Cursor result) {
			// Setting the cursor containing contacts to listview
			mAdapter.swapCursor(result);
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
