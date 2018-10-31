package com.client.service;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.client.gui.ClientGui;

public class IncomingReader implements Runnable {

	@Override
	public void run() {
		ImageIcon icon = new ImageIcon("C://Users/aoliva/Desktop/JAVA WORKSPACE/ChatClient/download.png");

		ClientGui clientGui = new ClientGui();

		ClientService clientService = new ClientService("127.0.01", clientGui);

		clientGui.addObserver(clientService);
		clientGui.setSize(700, 700);
		clientGui.setVisible(true);
		clientGui.buildFrame(icon, clientGui, new JFrame(), "Client Chat");
		
		clientService.start();
	}

}
