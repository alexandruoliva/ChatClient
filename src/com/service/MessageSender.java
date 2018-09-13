package com.service;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class MessageSender extends Thread {

	
	static void runThread( String message, ObjectOutputStream output) throws IOException {
		
		MessageSender thread =new MessageSender();
		int n = Integer.parseInt(Regex.regexChecker("\\d+", message));
		message=Regex.regexReplaceNumbers(message);
		thread.run();
		for (int i = 0; i < n; i++) {
			output.writeObject("CLIENT - " + (n-i) + message);
			output.flush();
			ClientService.showMessage("\nClient -" + message);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
