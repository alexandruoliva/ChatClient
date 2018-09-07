package com.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.service.ClientService;

public class ClientTest {
	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("C://Users/aoliva/Desktop/JAVA WORKSPACE/ChatClient/download.png");

		
		ClientService clientService = new ClientService("127.0.01", clientApp);
		ClientGui clientApp = new ClientGui(clientService);
		clientApp.setSize(700, 700);
		clientApp.setVisible(true);
		
		JFrame frame = new JFrame();
		
		clientApp.buildFrame(icon, clientApp, frame, "Client Chat");
		clientService.startRunning();
		
	
	}
}
