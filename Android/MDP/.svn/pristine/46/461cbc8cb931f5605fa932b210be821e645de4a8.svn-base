package com.access;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.widget.Toast;

public class InitiateServer extends Activity {

	public static String signIn(String ip, String port, String email, String password) {
		String status = "";
		try {
			// If value for key not exist then return second param value - In
			// this case null
			SoapPrimitive resultsRequestSOAP = null;
			SoapObject request = new SoapObject("http://logic.wsdl.com/", "signin");
			PropertyInfo propInfo = new PropertyInfo();
			propInfo.name = "arg0";
			propInfo.type = PropertyInfo.STRING_CLASS;
			request.addProperty(propInfo, email);
			PropertyInfo propInfo1 = new PropertyInfo();
			propInfo1.name = "arg1";
			propInfo1.type = PropertyInfo.STRING_CLASS;
			request.addProperty(propInfo1, password);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE("http://" + ip + ":" + port
					+ "/ModelDrivenPattern/SigninPort");
			androidHttpTransport.call("http://logic.wsdl.com/signin", envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			status = resultsRequestSOAP.toString();
		} catch (Exception e) {
			// Toast.makeText(InitiateServer.this,""+e,Toast.LENGTH_LONG).show();
		}
		return status;
	}

	public static String signUp(String ip, String portno, String details) {
		String status = "";
		try {
			// If value for key not exist then return second param value - In
			// this case null
			SoapPrimitive resultsRequestSOAP = null;
			SoapObject request = new SoapObject("http://logic.wsdl.com/", "signup");
			PropertyInfo propInfo = new PropertyInfo();
			propInfo.name = "arg0";
			propInfo.type = PropertyInfo.STRING_CLASS;
			request.addProperty(propInfo, details);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE("http://" + ip + ":" + portno
					+ "/ModelDrivenPattern/SignupPort");
			androidHttpTransport.call("http://logic.wsdl.com/signup", envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			status = resultsRequestSOAP.toString();
		} catch (Exception e) {

		}
		return status;
	}

	public static String buildMicroapp(String ip, String portno, String services) {
		String status = "";
		try {

			SoapPrimitive resultsRequestSOAP = null;
			SoapObject request = new SoapObject("http://logic.wsdl.com/", "services");
			PropertyInfo propInfo = new PropertyInfo();
			propInfo.name = "arg0";
			propInfo.type = PropertyInfo.STRING_CLASS;
			request.addProperty(propInfo, services);
			
			PropertyInfo propInfo1 = new PropertyInfo();
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE("http://" + ip + ":" + portno
					+ "/ModelDrivenPattern/UserRequirementPort");
			androidHttpTransport.call("http://logic.wsdl.com/services", envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			status = resultsRequestSOAP.toString();
		} catch (Exception e) {

		}
		return status;
	}

	public static String getKeyStoreNames(String ip, String portno) {
		String status = "";
		try {

			SoapPrimitive resultsRequestSOAP = null;
			SoapObject request = new SoapObject("http://logic.wsdl.com/", "getKeyStoreNames");
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE("http://" + ip + ":" + portno
					+ "/ModelDrivenPattern/KeyStoreNamesPort");
			androidHttpTransport.call("http://logic.wsdl.com/getKeyStoreNames", envelope);
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
			status = resultsRequestSOAP.toString();
		} catch (Exception e) {
			status = "" + e;
		}
		return status;
	}

}
