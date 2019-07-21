package com.example.mdp;



import com.access.ServerConnection;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class SigninActivity extends ActionBarActivity {

	private EditText useremail, userpassword;
	String email, password;
	Boolean done = true;
	private Button signup, login;
	public static String username;
	AlertDialog.Builder alertDialogBuilder = null;
	AlertDialog alertDialog = null;
	public void onCreate(Bundle savedInstancestate) {
		super.onCreate(savedInstancestate);
		setContentView(R.layout.activity_signin);
		useremail = (EditText) findViewById(R.id.email);
		userpassword = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		signup = (Button) findViewById(R.id.signup);
		signup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), SignupActivity.class);
				startActivity(i);

			}
		});

		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				

				email = useremail.getText().toString().trim();
				password = userpassword.getText().toString().trim();
				if (email.equalsIgnoreCase("")) {
					done = false;
					Toast.makeText(getApplicationContext(), "Please enter email ", Toast.LENGTH_LONG).show();
				}
				else if (password.equalsIgnoreCase("")) {
					done = false;
					Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_LONG).show();
				}
				if (done) {
					try {
						/**************** Get SharedPreferences data *******************/
						SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
						String parameters = "b.email=" + email + "&b.password=" + password;
						Toast.makeText(getApplicationContext(),"param====>"+parameters, Toast.LENGTH_LONG).show();
						String status = ServerConnection.serverCall("Signin.action", parameters);
						String arr[] = status.split("@"); 
						if (arr[0].equalsIgnoreCase("Login")) {
							/******* Create SharedPreferences *******/
							Editor editor = pref.edit();
							editor.putString("username", arr[1]); // Saving
																	// username
							editor.putString("phoneno", arr[2]); // Saving
																	// phoneno
							editor.commit(); // commit changes
							username = arr[1];
							Intent i = new Intent(getApplicationContext(), DragActivity.class);
							startActivity(i);
						} else {
							Toast.makeText(getApplicationContext(),  status, Toast.LENGTH_LONG).show();
						}
					} catch (Exception e) {

						Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
					
					}
				}
			}
		});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		alertBox();
	}
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		return true;
//	}
	void alertBox() {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SigninActivity.this);
		alertDialogBuilder.setTitle("VMC");
		alertDialogBuilder.setMessage("Do you want change Ip address?");
		alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				alertDialog.dismiss();
				Intent intent1 = new Intent(SigninActivity.this, MainActivity.class);
				startActivity(intent1);
			}

		});
		alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				alertDialog.dismiss();
			}
		});
		alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}
}
