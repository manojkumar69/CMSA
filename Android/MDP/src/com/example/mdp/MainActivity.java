package com.example.mdp;



import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	RelativeLayout select;
	EditText ipaddr;
	public static String ipaddress;
	public static String portno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        select = (RelativeLayout) findViewById(R.id.container);
		ipaddr = (EditText) findViewById(R.id.ip);
		select.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if (android.os.Build.VERSION.SDK_INT > 9) {
						StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
						StrictMode.setThreadPolicy(policy);
					}
					if (ipaddr.getText().toString().trim().equalsIgnoreCase("")) {
						Toast.makeText(getApplicationContext(), "Give your System Ip Address", Toast.LENGTH_LONG)
								.show();
					} else {
						String address = ipaddr.getText().toString().trim();
						String arr[] = address.split(":");
						ipaddress = arr[0];
						portno = arr[1];
						MDP_Application.IP_ADDRESS=ipaddress;
						MDP_Application.PORT_NO=portno;
						SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
						Editor editor = pref.edit();
						editor.putString("ipaddress", arr[0]);
						editor.putString("portno", arr[1]);// Saving string
						editor.commit(); // commit changes
						Intent r = new Intent(getApplicationContext(), SigninActivity.class);
						startActivity(r);
					}

				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Exception in url==>" + e, Toast.LENGTH_SHORT).show();
				}
			}
		});
    }


  

    

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
