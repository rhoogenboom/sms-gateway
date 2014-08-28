package net.belleron.gateway.smsgateway;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
 
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
 

public class smsgateway {
 
	private final String USER_AGENT = "Mozilla/5.0";
	private final String USERHASH = "5d50a93ac9c15d278fc7077eccb97d60";
	private final String URL = "https://smsservicecenter.nl/api.php/";
	//private final String SENDNAME = "smsgateway1";

	private Long smscredits = -1L;
	private String smssendname = "smsgateway1";
	private int responseCode = 0;
	private String responseResult = "";

	public Long getSMSCredits() {
		return this.smscredits;
	}

	public String getSMSSendname() {
		return this.smssendname;
	}

	public void setSMSSendname(String sendname) {
		this.smssendname = sendname;
	}

	public int getResponseCode() {
		return this.responseCode;
	}

	public String getResponseResult() {
		return this.responseResult;
	}

	public void smsgateway() {

	}

	//Get credits
	public void getCredits() throws Exception { 

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(URL + "getcredits/?userhash=" + USERHASH);
		
		// add request header
		request.addHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(request);

		//System.out.println("\nCredits : \n");
		//System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		this.responseCode = response.getStatusLine().getStatusCode();
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		//System.out.println(result.toString());
 		this.responseResult = result.toString();

		JsonParser parser = new JsonParser();
        // The JsonElement is the root node. It can be an object, array, null or
        // java primitive.
        JsonElement element = parser.parse(result.toString());
        // use the isxxx methods to find out the type of jsonelement. In our
        // example we know that the root object is the Albums object and
        // contains an array of dataset objects
        if (element.isJsonObject()) {
            JsonObject creditObj = element.getAsJsonObject();
            this.smscredits = Long.valueOf(creditObj.get("credits").getAsInt());
            // JsonArray datasets = albums.getAsJsonArray("dataset");
            // for (int i = 0; i < datasets.size(); i++) {
            //     JsonObject dataset = datasets.get(i).getAsJsonObject();
            //     System.out.println(dataset.get("album_title").getAsString());
            // }
        }

		//System.out.println();
	}


	//Get sentname
	public void getSendname() throws Exception { 

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(URL + "getsendname/?userhash=" + USERHASH);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(request);

		// System.out.println("\nGet send name : \n");
		// System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		this.responseCode = response.getStatusLine().getStatusCode();
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result.toString());
        if (element.isJsonObject()) {
            JsonObject responseObj = element.getAsJsonObject();
            this.smssendname = responseObj.get("sendname").getAsString();
        }
	}


	//Get history
	public void getHistory(String param) throws Exception { 

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(URL + "gethistory/?userhash=" + USERHASH + "&page=" + param);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(request);

		System.out.println("\nGet history : \n");
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

 		this.responseResult = result.toString();
		System.out.println(result.toString());
	}
 
	// Set send name
	public void setSendName(String name) throws Exception { 

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(URL + "setsendname/?userhash=" + USERHASH + "&sendname=" + name);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(request);

		// System.out.println("\nSetting send name to : " + name + "\n");
		// System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
 		this.responseCode = response.getStatusLine().getStatusCode();
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		System.out.println(result.toString());
	}

	// Send message
	public void sendMessage(String number, String message) throws Exception { 

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(URL + "send/?userhash=" + USERHASH + "&nr=" + number + "&msg=" + URLEncoder.encode(message, "UTF-8"));  

		// add request header
		request.addHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(request);

		// System.out.println("\nSending to number: " + number + "\n");
		// System.out.println("Message: " + message + "\n");
		// System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
 		this.responseCode = response.getStatusLine().getStatusCode();
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		System.out.println(result.toString());
	}
 
}