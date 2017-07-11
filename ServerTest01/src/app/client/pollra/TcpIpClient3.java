package app.client.pollra;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class TcpIpClient3 {

	public static void main(String[] args) {
		if (args.length!=1) {
			System.out.println(args);
			System.out.println("USAGE: java TcpIpClient_V0.3");
			System.exit(0);
		}

		try {
			System.out.println("socket create");
			String serverIp = null;
			// 소켓생성 연결요청
			System.out.println("연결요청");
			Socket socket = new Socket(serverIp, 8087);

			System.out.println("서버 연결완료");
			Thread sender = new Thread(new ClientSender(socket, args[0]));
			Thread receiver = new Thread(new ClientReceiver(socket));

			sender.start();
			receiver.start();
			
		} catch (ConnectException e) {
			System.out.println("[!] 치명적 에러 발생");
		} catch (Exception e) {
			System.out.println("[!] 치명적 에러 발생2");
		}
	}
}

class ClientSender extends Thread {

	Socket socket;
	DataOutputStream out;
	String name;

	ClientSender(Socket socket, String name) {
		this.socket = socket;

		try {
			out = new DataOutputStream(socket.getOutputStream());
			this.name = name;
		} catch (Exception e) {
		}
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		try {
			if (out != null) {
				out.writeUTF(name);
			}
			while (out != null) {
				out.writeUTF(name + scanner.nextLine());
			}
		} catch (IOException e) {
		}
	}

}

class ClientReceiver extends Thread {

	Socket socket;
	DataInputStream in;

	public ClientReceiver(Socket socket) {
		this.socket = socket;
		try {
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
		}
	}

	public void run() {
		while (in != null) {
			try {
				System.out.println(in.readUTF());
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
	}

}
