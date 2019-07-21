package com.example.mdp;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AvailableCombinationsActivity extends ActionBarActivity {
	TextView services;
	String comb = "";
	static LinkedHashSet<String> checkcombinationset = new LinkedHashSet<String>();
	
	public void onCreate(Bundle savedInstancestate) {
		super.onCreate(savedInstancestate);
		setContentView(R.layout.activity_available_services);
		services = (TextView) findViewById(R.id.services);
		services.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				checkCombinations(null);
				int count = 0;
				for (String s : checkcombinationset) {
					comb += count + ")" + s + ".\n\n";
					count++;
				}
				services.setText(comb);
			}
		});
	}

	public static Boolean checkCombinations(List<String> selectedServices) {
		Boolean status = false;

		checkcombinationset.add("DS,VR,TP,PP,RV,VP,SE,SD,GPSLoc,WInfo,CL,SMS,Email");// all services
		// using Photo
		checkcombinationset.add("TP,PP,SE,GPSLoc,WInfo,CL,SMS,Email");
		checkcombinationset.add("TP,PP,SE,SD,GPSLoc,WInfo,CL,SMS,Email");
		checkcombinationset.add("TP,SE,GPSLoc,WInfo,CL,SMS,Email");
		checkcombinationset.add("TP,PP,SE,GPSLoc,CL,SMS,Email");
		checkcombinationset.add("TP,PP,SE,SD,GPSLoc,CL,SMS,Email");
		checkcombinationset.add("TP,PP,SE,GPSLoc,WInfo,SMS,Email");
		checkcombinationset.add("TP,PP,SE,SD,GPSLoc,WInfo,SMS,Email");
		checkcombinationset.add("TP,SE,GPSLoc,WInfo,SMS,Email");
		checkcombinationset.add("TP,PP,SE,GPSLoc,SMS,Email");
		checkcombinationset.add("TP,PP,SE,SD,GPSLoc,SMS,Email");
		checkcombinationset.add("TP,PP,GPSLoc,WInfo,Email");
		checkcombinationset.add("TP,PP,WInfo,Email");
		checkcombinationset.add("TP,PP,GPSLoc,Email");
		checkcombinationset.add("TP,PP,SE,CL,SMS,Email");
		checkcombinationset.add("TP,PP,SE,SD,CL,SMS,Email");
		checkcombinationset.add("TP,PP,Email");
		checkcombinationset.add("TP,PP,DS,Email");
		checkcombinationset.add("TP,Email");
		// using Video
		checkcombinationset.add("RV,VP,SE,GPSLoc,WInfo,CL,SMS,Email");
		checkcombinationset.add("RV,VP,SE,SD,GPSLoc,WInfo,CL,SMS,Email");
		checkcombinationset.add("RV,SE,GPSLoc,WInfo,CL,SMS,Email");
		checkcombinationset.add("RV,VP,SE,GPSLoc,CL,SMS,Email");
		checkcombinationset.add("RV,VP,SE,SD,GPSLoc,CL,SMS,Email");
		checkcombinationset.add("RV,VP,SE,GPSLoc,WInfo,SMS,Email");
		checkcombinationset.add("RV,VP,SE,SD,GPSLoc,WInfo,SMS,Email");
		checkcombinationset.add("RV,SE,GPSLoc,WInfo,SMS,Email");
		checkcombinationset.add("RV,SE,SD,GPSLoc,WInfo,SMS,Email");
		checkcombinationset.add("RV,VP,SE,GPSLoc,SMS,Email");
		checkcombinationset.add("RV,VP,SE,SD,GPSLoc,SMS,Email");
		checkcombinationset.add("RV,GPSLoc,WInfo,Email");
		checkcombinationset.add("RV,VP,SE,CL,SMS,Email");
		checkcombinationset.add("RV,VP,SE,SD,CL,SMS,Email");
		checkcombinationset.add("RV,VP,SE,SMS,Email");
		checkcombinationset.add("RV,VP,SE,SD,SMS,Email");
		checkcombinationset.add("RV,VP,Email");
		checkcombinationset.add("RV,Email");
		checkcombinationset.add("RV,DS,Email");
		checkcombinationset.add("RV,DS,SD,SE,Email");
		// using Voice
		checkcombinationset.add("VR,GPSLoc,WInfo,CL,SMS,Email");
		checkcombinationset.add("VR,WInfo,CL,SMS");
		checkcombinationset.add("VR,WInfo,CL,Email");
		checkcombinationset.add("VR,GPSLoc,CL,SMS");
		checkcombinationset.add("VR,GPSLoc,CL,Email");
		checkcombinationset.add("VR,CL,SMS");
		checkcombinationset.add("VR,GPSLoc,WInfo,SMS,Email");
		checkcombinationset.add("VR,WInfo,SMS");
		checkcombinationset.add("VR,WInfo,Email");
		checkcombinationset.add("VR,GPSLoc,SMS");
		checkcombinationset.add("VR,GPSLoc,Email");
		checkcombinationset.add("VR,CL,SMS");
		checkcombinationset.add("VR,SMS");
		checkcombinationset.add("VR,Email");
		checkcombinationset.add("VR,DS,Email");
		checkcombinationset.add("VR,DS,SD,SE,Email");
		// using Location services
		checkcombinationset.add("GPSLoc,WInfo,Email");
		checkcombinationset.add("GPSLoc,WInfo,CL,SMS,Email");
		checkcombinationset.add("GPSLoc,Email");
		checkcombinationset.add("GPSLoc,CL,SMS");
		checkcombinationset.add("GPSLoc,SMS");
		checkcombinationset.add("GPSLoc,WInfo,CL,SMS,Email");
		checkcombinationset.add("GPSLoc,WInfo,SMS,Email");
		// using Weather Information
		checkcombinationset.add("WInfo,Email");
		checkcombinationset.add("WInfo,DS,Email");
		checkcombinationset.add("WInfo,CL,SMS");
		checkcombinationset.add("WInfo,SMS");
		checkcombinationset.add("WInfo,CL,SMS,Email");
		checkcombinationset.add("WInfo,SMS,Email");
		// decryption..
		checkcombinationset.add("SD");
		// device status only ..
		checkcombinationset.add("DS,CL,SMS,Email");
		checkcombinationset.add("DS,SMS,Email");
		checkcombinationset.add("DS,Email");
		checkcombinationset.add("DS,CL,SMS");
		checkcombinationset.add("DS,GPSLoc,WInfo,CL,SMS");
		checkcombinationset.add("DS,GPSLoc,WInfo,Email");
		//newservices
		checkcombinationset.add("TP,PP,DS,GPSLoc,Email");
		checkcombinationset.add("TP,PP,DS,GPSLoc,SMS,Email");
		checkcombinationset.add("TP,PP,DS,WInfo,Email");
		checkcombinationset.add("TP,PP,DS,WInfo,SMS,Email");

		checkcombinationset.add("TP,PP,DS,VR,GPSLoc,Email");
		checkcombinationset.add("TP,PP,DS,VR,GPSLoc,SMS,Email");
		checkcombinationset.add("TP,PP,DS,VR,WInfo,Email");
		checkcombinationset.add("TP,PP,DS,VR,WInfo,SMS,Email");

		checkcombinationset.add("TP,PP,DS,GPSLoc,WInfo,Email");
		checkcombinationset.add("TP,PP,DS,VR,GPSLoc,WInfo,Email");
		//Abbreviation...
		checkcombinationset.add("***Abrreviation***");
		checkcombinationset.add("DS=Device Status");
		checkcombinationset.add("TP=Take Photo");
		checkcombinationset.add("PP=Photo Preview");
		checkcombinationset.add("RV=Record Video");
		checkcombinationset.add("VP=Video Preview");
		checkcombinationset.add("VR=Voice Record");
		checkcombinationset.add("GPSLoc=GPS Location");
		checkcombinationset.add("WInfo=Weather Information");
		checkcombinationset.add("SE=Standard Encryption");
		checkcombinationset.add("SD=Standard Decryption");
		if (selectedServices != null) {
			for (String available : checkcombinationset) {
				String array[]=available.split(",");
				for(String s:array){
					if(selectedServices.contains(s))
						status=true;
					else{
						status=false;
						break;
					}
				}
				if(status)
				{
					for(String ser:selectedServices){
						if(!available.contains(ser)){
							status=false;
							break;
						}
					}
					if(status){
						break;
					}
				}
					
			}
		}
		return status;
	}
}
