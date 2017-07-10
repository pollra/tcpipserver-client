package app.server.service;

import java.io.*;
import java.net.*;

public class Receiver extends Thread{
	Socket socket;
	DataInputStream in;
	
	public Receiver(Socket socket) {
		this.socket = socket;
		try {
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) { }
	}

	@Override
	public void run() {
		while(in!=null) {
			try {
				System.out.println(in.readUTF());
			} catch (IOException e) { }
		}
	}
}
