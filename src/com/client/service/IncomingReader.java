package com.client.service;

import java.io.IOException;

public class IncomingReader implements Runnable {

	@Override
	public void run() {
		try {
			new ClientService().whileChatting();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
