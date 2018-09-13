package com.service;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class MessageSender extends Thread {

	String message;
	
	ObjectOutputStream output;
	
	public MessageSender(String message, ObjectOutputStream output) {
		this.message = message;
		this.output = output;
	}
	
	public void run() {
		int n = Integer.parseInt(Regex.regexChecker("\\d+", message));
		message=Regex.regexReplaceNumbers(message);
		ClientService.showMessage("\nClient -" +n+' '+ message);
		for (int i = 0; i < n; i++) {
			try {
				output.writeObject("CLIENT - " + (n-i) +' '+ message);
				output.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		message="";
		
	}
	
}
