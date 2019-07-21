package com.tamil.logic;
 
import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.Vector;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import android.view.Menu;
import android.view.MenuItem;
public class WeatherInfo extends Activity implements InfoInterface
{
	TextView editTexttemperature,editTexthumidity,editTextweather,editTextwind,editTextcity,editTextdate;
	Button nextservice;
	String weatherdetails="";
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
	public void onCreate(Bundle savedInstanceState) 
	{
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.weather_activity);
			editTextdate=(TextView)findViewById(R.id.date);
			editTextcity=(TextView)findViewById(R.id.city);
			editTexttemperature=(TextView)findViewById(R.id.temp);
			editTexthumidity=(TextView)findViewById(R.id.hum);
			editTextwind=(TextView)findViewById(R.id.wind);
			editTextweather=(TextView)findViewById(R.id.weather);
			nextservice=(Button)findViewById(R.id.weathernext);
			nextservice.setOnClickListener(new View.OnClickListener() 
			{
				@Override
				public void onClick(View v)
				{
						nextActivity();		
				}
			});
			checkWeather("Chennai");
			Date date = new Date();
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			editTextdate.setText(modifiedDate);
			weatherdetails+="Date:"+modifiedDate+"\n";
			alldatas.put("weather",weatherdetails);
		}
        catch (Exception e) 
        {
        	Toast.makeText(getApplicationContext(),"Xml "+e,Toast.LENGTH_LONG).show();
		}
	}
	public void checkWeather(String city)
	{ 
		try
		{
		   Vector vectagname=new Vector();
		   String strval="";
	       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	       dbf.setValidating(false);
	       DocumentBuilder db = dbf.newDocumentBuilder();
	       vectagname.add("city");
//	       vectagname.add("coord");
//	       vectagname.add("sun");
	       vectagname.add("temperature");
	       vectagname.add("humidity");
//	       vectagname.add("pressure");
	       vectagname.add("windSpeed ");
	       vectagname.add("direction");
//	       vectagname.add("clouds");
	       vectagname.add("weather");
//	       vectagname.add("lastupdate");
	       
	       Document doc = db.parse("http://api.openweathermap.org/data/2.5/forecast/chennai?id=524901&APPID=e210ecb7615f312109615855cde6750c&mode=xml");
	       NodeList entries; 
		   for(int i=0;i<vectagname.size();i++)
		   {
			   entries=doc.getElementsByTagName(vectagname.get(i).toString());
			   int num = entries.getLength();
			   String values="";
		        for(int j=0;j<num;j++)
		        {
		        	Element node=(Element)entries.item(0);
		            values=listAllAttributes(node);
		        }
		        if(i==0)
				{
					editTextcity.setText(values);
					weatherdetails+="City:"+values+"\n";
				}
				else if(i==1)
				{
					editTexttemperature.setText(values);
					weatherdetails+="Temperature:"+values+"\n";
				}
				else if(i==2)
				{
					editTexthumidity.setText(values);
					weatherdetails+="Humidity:"+values+"\n";
				}
				else if(i==3)
				{
					editTextwind.setText(values);
					weatherdetails+="Direction:"+values+"\n";
				}
				else if(i==4)
				{
					editTextweather.setText(values);
					weatherdetails+="Weather:"+values+"\n";
				}
		   } 
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "Ex-Weather1=>"+e,Toast.LENGTH_LONG).show();
			for(StackTraceElement s:e.getStackTrace()){
				if(s.getClassName().equalsIgnoreCase("com.example.listviewsample.WeatherInfo"))
				Toast.makeText(getApplicationContext(), s.getClassName()+"=="+s.getMethodName()+"=="+s.getLineNumber(),Toast.LENGTH_LONG).show();
			}
			
		}
	}
	public String  listAllAttributes(Element element)
	{
		String values ="";
		try
		{
			NamedNodeMap attributes = element.getAttributes();
			int numAttrs = attributes.getLength();
			for (int i=0;i<numAttrs;i++)
			{
				Attr attr=(Attr)attributes.item(i);
			    String attrName = attr.getNodeName();
			    String attrValue = attr.getNodeValue();
				values=values+"\n"+attrName+"-"+attrValue;
			}
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "Ex-Weather2=>"+e,Toast.LENGTH_LONG).show();
			for(StackTraceElement s:e.getStackTrace()){
				if(s.getClassName().equalsIgnoreCase("WeatherInfo"))
				Toast.makeText(getApplicationContext(), s.getClassName()+"=="+s.getMethodName()+"=="+s.getLineNumber(),Toast.LENGTH_LONG).show();
			}
		}
		return values;
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
