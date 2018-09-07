package com.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.service.ClientService;

public class ClientGui extends JPanel {
	JPanel chatClient = new JPanel();
	JTextField inputTextTab = new JTextField("client input text", 5);
	JTextArea outputTextTab = new JTextArea(5, 5);
	GridBagConstraints gridBagCon = new GridBagConstraints();
	
	
	
	
	public JTextField getInputTextTab() {
		return inputTextTab;
	}

	public void setInputTextTab(JTextField inputTextTab) {
		this.inputTextTab = inputTextTab;
	}

	public JTextArea getOutputTextTab() {
		return outputTextTab;
	}

	public void setOutputTextTab(JTextArea outputTextTab) {
		this.outputTextTab = outputTextTab;
	}

	public ClientGui()
	{	
		gridBagCon.weightx = 0.5;
		gridBagCon.weighty = 1.0;
		gridBagCon.fill = GridBagConstraints.BOTH;

		setLayout(new GridBagLayout());
		gridBagCon.insets = new Insets(2, 2, 2, 2);

		outputTextTab.setFont(outputTextTab.getFont().deriveFont(20f));
		outputTextTab.setEditable(false);

		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
		add(outputTextTab, gridBagCon);
		add(new JScrollPane(outputTextTab), gridBagCon);

		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		inputTextTab.setEditable(false);
		add(inputTextTab, gridBagCon);

		

	}

	public void buildFrame(ImageIcon icon, ClientGui object, JFrame frame, String titleFrame) {
		frame.add(chatClient);
		frame.setTitle(titleFrame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setIconImage(icon.getImage());
		frame.add(object);
		frame.pack();
	}

}
