package app.client.opstion;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TcpIpClient4 {

	public static void main(String args[]) {
		if(args.length==0) {
			System.out.println("USAGE : java TcpIpClient_v0.4");
			System.exit(0);
		}
		
		try {
			String serverIp=null;
			//소켓생성 연결시도
			Socket socket = new Socket(serverIp, 8086);
			
			System.out.println("서버 연결 성공");
			Thread sender = new Thread(new ClientSender(socket, args[0]));
			Thread receiver = new Thread(new ClientReceiver(socket));
			
			sender.start();
			receiver.start();
			
		} catch (ConnectException e) {
			System.out.println("[!] Error : ConnectException");
		}catch (Exception e) {
			System.out.println("[!] Error : Exception");
			e.printStackTrace();
		}
	}//main
	
	static class ClientSender extends Thread{
		Socket socket;
		DataOutputStream out;
		String name;
		
		public ClientSender(Socket socket, String name) {
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
				this.name = name;
			} catch (Exception e) { }
		}// ClientSender()
		
		public void run() {
			Scanner scanner = new Scanner(System.in);
			try {
				if(out!=null) {
					out.writeUTF(name);
				}
				while(out!=null) {
					out.writeUTF("["+name+"]"+scanner.nextLine());
				}
			} catch (IOException e) { }
		}// run()
	}//ClientSender
	
	static class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) { }
		}
		
		public void run() {
			while(in!=null) {
				try {
					System.out.println(in.readUTF());
				} catch (IOException e) { }
			}
		}// run()
	}// ClientReceiver
}//class

