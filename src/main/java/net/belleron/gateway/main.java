package net.belleron.gateway;

import net.belleron.gateway.smsgateway.*;

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

			smsgateway http = new smsgateway();

			switch (command) {
				case "getcredits" : http.getCredits(); 
					System.out.println(http.getSMSCredits());
					System.out.println(http.getResponseCode());
					System.out.println(http.getResponseResult());
					break;
				case "getsendname" : http.getSendname();
					break;
				case "gethistory" : 
					if (param == "") {
						param = "1";
					}
					http.getHistory(param);
					break;
				case "setsendname" : 
					if (param == "") {
						param = "smsgateway1";
					}
					http.setSendName(param);
					break;
				case "sendmessage" : http.sendMessage(param, message.toString().toString().substring(0, message.toString().length()-1));
					break;
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

}