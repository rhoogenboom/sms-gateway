package net.belleron.gateway;

import net.belleron.gateway.smsgateway.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
 
import org.apache.http.*;
// import org.apache.http.HttpResponse;
// import org.apache.http.client.HttpClient;
// import org.apache.http.client.methods.HttpGet;
// import org.apache.http.impl.client.DefaultHttpClient;

// import org.apache.http.NameValuePair;
// import org.apache.http.message.BasicNameValuePair;
// import org.apache.http.client.entity.UrlEncodedFormEntity;
// import org.apache.http.client.methods.HttpPost;


public class main {

	public static void main(String[] args) throws Exception {

		if (args.length == 0 ) {
			printHelp();
		}
		else {
			String command = ""; 
			String param = "";

			boolean isFirstParameter = true;
			boolean isSecondParameter = false;

			StringBuilder message = new StringBuilder();
			for (String s : args) {
				if (isFirstParameter) {
					command = s;
					isFirstParameter = false;
					isSecondParameter = true;
				} else {
					if (isSecondParameter) {
						param = s;
						isSecondParameter = false;
					} else {
						message.append(s + " ");	
					}
				}
			}

			//System.out.println(command + "\n" + param + "\n" + message.toString() + "\n");

			//smsgateway http = new smsgateway();

			main http = new main();

			switch (command) {
				case "getcredits" : 

					http.getCredits();
					// http.getCredits(); 
					// System.out.println(http.getSMSCredits());
					// System.out.println(http.getResponseCode());
					// System.out.println(http.getResponseResult());
					break;
				// case "getsendname" : http.getSendname();
				// 	break;
				// case "gethistory" : 
				// 	if (param == "") {
				// 		param = "1";
				// 	}
				// 	http.getHistory(param);
				// 	break;
				// case "setsendname" : 
				// 	if (param == "") {
				// 		param = "smsgateway1";
				// 	}
				// 	http.setSendName(param);
				// 	break;
				// case "sendmessage" : http.sendMessage(param, message.toString().toString().substring(0, message.toString().length()-1));
				// 	break;
			};


		}
	}

	private static void printHelp() {
	        System.out.print("SMS gateway\n " +
	                "Input parameters: action [parameters]\n" +
	                "\n" +
	                "action:\n" +
	                "getcredits\n" +
	                "getsendname\n" +
	                "gethistory parameter\n" +
	                "setsendname parameter\n" +
	                "sendmessage parameters\n" +
	                "\n" +
	                "\n");
	        System.out.print("action: gethistory number\n" +
					"parameter: number - history page number\n" +
					"\n" +
					"action: setsendname name\n" +
					"parameter: name - name to set sender to\n" +
					"\n" +
					"action: sendname number message\n" +
					"parameters: number - number to sent to\n" +
					"parameters: message - message to send\n" +
					"\n" +
					"\n");
	        System.out.print("Example of usage:\n" +
	                "smsgateway getcredits\n" +
	                "smsgateway sendmessage 0612345678 this is a test\n");
    }

    public void getCredits() throws Exception { 

		org.apache.http.client.HttpClient client = new org.apache.http.impl.client.DefaultHttpClient();
		org.apache.http.client.methods.HttpGet request = new org.apache.http.client.methods.HttpGet("https://smsservicecenter.nl/api.php/" + "getcredits/?userhash=" + "5d50a93ac9c15d278fc7077eccb97d60");
		
		// add request header
		request.addHeader("User-Agent", "Mozilla/5.0");

		org.apache.http.HttpResponse response = client.execute(request);

		//System.out.println("\nCredits : \n");
		//System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		//this.responseCode = response.getStatusLine().getStatusCode();
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		//System.out.println(result.toString());
 		//this.responseResult = result.toString();
	}


}