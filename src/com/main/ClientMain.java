package com.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.client.gui.ClientGui;
import com.client.service.ClientService;



public class ClientMain {
	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("C://Users/aoliva/Desktop/JAVA WORKSPACE/ChatClient/download.png");

		ClientGui clientApp = new ClientGui();
		ClientService clientService = new ClientService("127.0.01", clientApp);
		clientApp.setSize(700, 700);
		clientApp.setVisible(true);
		
		JFrame frame = new JFrame();
		
		clientApp.buildFrame(icon, clientApp, frame, "Client Chat");
		clientService.startRunning();
		
	
	}
}
