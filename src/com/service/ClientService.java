package com.service;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;

import com.gui.ClientGui;
import com.observer.Observer;
import com.observer.Subject;

public class ClientService implements Observer {

	private static ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIp;
	private Socket connection;
	private boolean specialMessage;
	// dependency injection (constructor injection)

	ClientGui clientGui;

	public ClientService(String host, ClientGui clientGui) {
		serverIp = host;
		this.clientGui = clientGui;
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
		showMessage("Connected to: " + connection.getInetAddress().getHostName());
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

	// send messages to server
	public void sendMessage(ClientGui clientGui) {
		try {
			// output.writeObject("CLIENT - " + message);
			// output.flush();
			// showMessage("\nClient -" + message);
			message = clientGui.getInputTextTab().getText();

			if (Character.isDigit(message.charAt(0))) {
				int n = Integer.parseInt(message);
				// regexChecker("", message)
				for (int i = 0; i < n; i++) {
					output.writeObject("CLIENT - " + message);
					output.flush();
					showMessage("\nClient -" + message);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				n=0;
			} else {
				output.writeObject("CLIENT - " + message);
				output.flush();
				showMessage("\nClient -" + message);
			}
		} catch (IOException ioException) {
			clientGui.getOutputTextTab().append("\n something messed up sending message hoss");
		}

	}

	// update the chatwindow
	private void showMessage(final String text) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				clientGui.getOutputTextTab().append(text);

			}
		});
	}

	// let the user type stuff into their box
	private void ableToType(final boolean trueOrFalse) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				clientGui.getInputTextTab().setText("");
				clientGui.getInputTextTab().setEditable(trueOrFalse);
			}
		});
	}

	@Override
	public void update(Subject subject) {
		sendMessage(clientGui);
	}

	public void regexChecker(String theRegex, String str2Check) {
		Pattern checkRegex = Pattern.compile(theRegex);

		Matcher regexMatcher = checkRegex.matcher(str2Check);

		while (regexMatcher.find()) {
			if (regexMatcher.group().length() != 0) {
				System.out.println(regexMatcher.group().trim());
			}
			System.out.println("start index" + regexMatcher.start());
			System.out.println("End index" + regexMatcher.end());
		}
	}
}
