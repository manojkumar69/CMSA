package com.access;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.mdp.MDP_Application;


public class ServerConnection {

	public static String serverCall(String actionName, String parameters) {
		String response = "";
		try {
			if (parameters.contains(" ")) {
				parameters = parameters.replace(" ", "%20");
			}
			String url = "http://" + MDP_Application.IP_ADDRESS + ":" + MDP_Application.PORT_NO + "/"
					+ MDP_Application.DOMAIN_NAME + "/" + actionName + "?" + parameters;
			response = getHttpResponse(url);
		} catch (Exception e) {
			response = "No Server Connection"+e;
		}
		return response.trim();
	}

	public static String getHttpResponse(String url) {
		String response = "";
		InputStream is = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(url);
			HttpResponse res = httpClient.execute(getRequest);
			is = res.getEntity().getContent();
			byte[] b = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int ch;
			while ((ch = is.read()) != -1) {
				bos.write(ch);
			}
			b = bos.toByteArray();
			response = new String(b).replace("\n", "").trim();
		} catch (NullPointerException e) {
			response = "No Server Connection==>"+e;
		} catch (Exception e) {
			response = e.toString();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response.trim();
	}
}
