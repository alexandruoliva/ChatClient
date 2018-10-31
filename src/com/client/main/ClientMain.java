package com.client.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.client.gui.ClientGui;
import com.client.service.ClientService;
import com.client.service.IncomingReader;

public class ClientMain {

	public static void main(String[] args) {
		Thread readerThread = new Thread ( new IncomingReader());
		readerThread.start();
		
		
		
		
		
		
		
		
	}
}
