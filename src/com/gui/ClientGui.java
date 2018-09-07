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

public class ClientGui extends JPanel {
	JPanel chatClient = new JPanel();
	JTextField inputTextTab = new JTextField("client input text",5);
	JTextArea outputTextTab = new JTextArea(5, 5);
	GridBagConstraints gridBagCon = new GridBagConstraints();

	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIp;
	private Socket connection;

	public ClientGui(String host) {
		serverIp = host;
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
		add(new JScrollPane(outputTextTab),gridBagCon);

		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		inputTextTab.setEditable(false);
		add(inputTextTab, gridBagCon);

		inputTextTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// getActionCommand sends the message;
				sendMessage(event.getActionCommand());
				outputTextTab.setText("");

			}
		});

	}

	public void startRunning() {
		try {
			// the client is responsible to connect on the server
			connectToServer();

			setUpStreams();

			whileChatting();

		} catch (EOFException eofException) {
			showMessage("\n Client terminated the connection");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			closeChat();
		}

	}

	private void connectToServer() throws IOException {
		showMessage("Attempting connection ... \n");
		connection = new Socket(InetAddress.getByName(serverIp), 6789);
		showMessage("Connected to: "+ connection.getInetAddress().getHostName());
		
	}

	// set up streams to send and receive messages
	private void setUpStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Dear client your streams are good to go! \n");

	}

	// while chatting with server
	private void whileChatting() throws IOException {
		ableToType(true);
		do {
			try {
				message = (String) input.readObject();
				showMessage("\n" + message);
			} catch (ClassNotFoundException classNotFoundException) {
				showMessage("\n I don't know that object type!");
			}
		} while (!message.equals("SERVER - END"));
	}

	// close the streams and socket
	private void closeChat() {
		showMessage("\n Closing chat down...");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

	}
	//send messages to server
	private void sendMessage(String message){
		try {
			output.writeObject("CLIENT - " + message);
			output.flush();
			showMessage("\nClient -" + message);
		} catch (IOException ioException) {
			outputTextTab.append("\n something messed up sending message hoss");
		}
	}
	//update the chatwindow
	private void showMessage(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				outputTextTab.append(text);
			}
		});
	}
	
	// let the user type stuff into their box
		private void ableToType(final boolean trueOrFalse) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					inputTextTab.setText("");
					inputTextTab.setEditable(trueOrFalse);
				}
			});
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
