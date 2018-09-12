package com.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.gui.ClientGui;
import com.service.ClientService;

public class ClientMain {

	public static void main(String[] args) {

		ImageIcon icon = new ImageIcon("C://Users/aoliva/Desktop/JAVA WORKSPACE/ChatClient/download.png");

		ClientGui clientGui = new ClientGui();

		ClientService clientService = new ClientService("127.0.01", clientGui);

		clientGui.addObserver(clientService);
		clientGui.setSize(700, 700);
		clientGui.setVisible(true);
		clientGui.buildFrame(icon, clientGui, new JFrame(), "Client Chat");

		clientService.startRunning();
	}
}
