package com.logic;

import java.util.*;

import android.os.Environment;

public interface InfoInterface {
	TreeMap<Integer, String> locinfomap = new TreeMap<Integer, String>();
	Map<String, String> alldatas = new LinkedHashMap<String, String>();
	String APP_PATH = Environment.getExternalStorageDirectory() + "/MICROAPP/";
	Set<String> checkcombintionset = new HashSet<String>();

}
