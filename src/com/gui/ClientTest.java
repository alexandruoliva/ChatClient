package com.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ClientTest {
	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("C://Users/aoliva/Desktop/JAVA WORKSPACE/ChatClient/download.png");

		ClientGui clientApp = new ClientGui("127.0.01");
		
		clientApp.setSize(700, 700);
		clientApp.setVisible(true);
		
		JFrame frame = new JFrame();
		
		clientApp.buildFrame(icon, clientApp, frame, "Client Chat");
		clientApp.startRunning();
		
	
	}
}
