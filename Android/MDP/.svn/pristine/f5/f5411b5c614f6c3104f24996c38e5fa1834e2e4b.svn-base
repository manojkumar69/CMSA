package com.example.mdp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.access.ServerConnection;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends ActionBarActivity {
	private EditText name, mail, pass, loc, phoneno, username, dob;
	private Button signup;
	private Spinner gender;
	static final int DATE_DIALOG_ID = 0;
	private int pYear;
	private int pMonth;
	private int pDay;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		name = (EditText) findViewById(R.id.name);
		mail = (EditText) findViewById(R.id.email);
		pass = (EditText) findViewById(R.id.pass);
		phoneno = (EditText) findViewById(R.id.phoneno);
		username = (EditText) findViewById(R.id.username);
		dob = (EditText) findViewById(R.id.dob);
		loc = (EditText) findViewById(R.id.loc);
		signup = (Button) findViewById(R.id.submit);
		List<String> list = new ArrayList<String>();
		list.add("Male");
		list.add("Female");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gender = (Spinner) findViewById(R.id.gender);
		gender.setAdapter(dataAdapter);
		// Spinner item selection Listener
		addListenerOnSpinnerItemSelection();
		dob.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}

		});
		final Calendar cal = Calendar.getInstance();
		pYear = cal.get(Calendar.YEAR);
		pMonth = cal.get(Calendar.MONTH);
		pDay = cal.get(Calendar.DAY_OF_MONTH);

		/** Display the current date in the TextView */
		updateDisplay();
		signup.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				boolean done = true;
				try {
					StringTokenizer str1 = new StringTokenizer(dob.getText().toString(), "/");
					int dd = Integer.parseInt(str1.nextToken().trim());
					int mm = Integer.parseInt(str1.nextToken().trim());
					int yy = Integer.parseInt(str1.nextToken().trim());
					Pattern pattern = Pattern.compile("\\d{10}");
					Matcher matcher = pattern.matcher(phoneno.getText().toString());
					if (name.getText().toString().equals("")) {
						done &= false;
						Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
					} else if (username.getText().toString().equals("")) {
						done &= false;
						Toast.makeText(getApplicationContext(), "Please Enter Username", Toast.LENGTH_SHORT).show();
					} else if (!mail.getText().toString().contains("@")) {
						done &= false;
						Toast.makeText(getApplicationContext(), "Invalid Email ", Toast.LENGTH_SHORT).show();
					} else if (pass.getText().toString().equals("")) {
						done &= false;
						Toast.makeText(getApplicationContext(), "Please Enter password", Toast.LENGTH_SHORT).show();
					} else if (dob.getText().toString().equals("")) {
						done &= false;
						Toast.makeText(getApplicationContext(), "Please Enter DOB", Toast.LENGTH_SHORT).show();
					} else if (yy > 1997) {
						Toast.makeText(getApplicationContext(), "Age should be above 18", Toast.LENGTH_SHORT).show();
						done &= false;
					}

					else if (phoneno.getText().toString().equals("")) {
						done &= false;
						Toast.makeText(getApplicationContext(), "Please Enter Mobile Number", Toast.LENGTH_SHORT)
								.show();
					} else if (!matcher.matches()) {
						done &= false;
						Toast.makeText(getApplicationContext(), "Please Enter valid no", Toast.LENGTH_SHORT).show();
					} else if (loc.getText().toString().equals("")) {
						done &= false;
						Toast.makeText(getApplicationContext(), "Please Enter Location", Toast.LENGTH_SHORT).show();
					}
					if (done) {
											
						StringBuilder parameters = new StringBuilder();
						parameters.append("b.name=" + name.getText().toString());
						parameters.append("&b.username=" + username.getText().toString());
						parameters.append("&b.email=" + mail.getText().toString());
						parameters.append("&b.password=" + pass.getText().toString());
						parameters.append("&b.dob=" + dob.getText().toString());
						parameters.append("&b.gender=" + gender.getSelectedItem().toString());
						parameters.append("&b.phoneno=" + phoneno.getText().toString());
						parameters.append("&b.location=" + loc.getText().toString());
						/**Connecting to tomcat server*/
						String status = ServerConnection.serverCall("Signup.action", parameters.toString());
						// String status = InitiateServer.signUp(ip, portno,
						// details);
						if (status.contains("User Registered Successfully")) {
							Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT)
									.show();
							Intent i = new Intent(getApplicationContext(), SigninActivity.class);
							startActivity(i);
						} else {

							Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
						}
					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Exception==>" + e, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	// spinner button....
	public void addListenerOnSpinnerItemSelection() {

		gender.setOnItemSelectedListener(new CustomOnItemSelectedListener());

	}

	public void addListenerOnButton() {
		gender = (Spinner) findViewById(R.id.gender);
	}

	// date picker...
	private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			pYear = year;
			pMonth = monthOfYear;
			pDay = dayOfMonth;
			updateDisplay();
		}

	};

	/** Updates the date in the TextView */
	private void updateDisplay() {
		dob.setText(new StringBuilder()
		// Month is 0 based so add 1
				.append(pDay).append("/").append(pMonth + 1).append("/").append(pYear).append(" "));
	}

	/** Displays a notification when the date is updated */
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, pDateSetListener, pYear, pMonth, pDay);
		}
		return null;
	}
}