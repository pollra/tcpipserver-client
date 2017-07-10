package app.client.pollra;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

import app.server.service.Receiver;
import app.server.service.Sender;

public class TcpIpClient2 {
	public static void main(String[] args) {
		try {
			String serverIP = null;
			System.out.println("TcpIpClient_V0.2");
			System.out.println("서버에 연결중... 서버IP : " + serverIP);
			// 소켓을 생성하여 연결을 요청함
			Socket socket = new Socket(serverIP, 8086);

			System.out.println("서버연결 완료.");
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
		}catch(ConnectException ce) {
			System.out.println("[!] ConnectException");
			ce.printStackTrace();
		}catch(IOException e) {
			System.out.println("[!] IOException");
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("[!] Exception");
			e.printStackTrace();
		}
	}
}
