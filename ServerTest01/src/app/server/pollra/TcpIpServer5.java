package app.server.pollra;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.server.service.Receiver;
import app.server.service.Sender;

public class TcpIpServer5 {

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		int portNum = 8086;
		try {
			System.out.println("TcpIpServer_V0.5");
			serverSocket = new ServerSocket(portNum);
			System.out.println(getTime() + "서버가 준비되었습니다.");
			
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "에서 접속하셨습니다.");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static String getTime() {
		return new SimpleDateFormat("[hh:mm:ss]").format(new Date());
	}
}
