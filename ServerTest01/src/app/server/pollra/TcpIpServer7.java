package app.server.pollra;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class TcpIpServer7 {
	HashMap clients;
	
	public TcpIpServer7() {
		clients = new HashMap();
		Collections.synchronizedMap(clients);
	}
	
	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(8086);
			System.out.println("[!] Server Run... v0.7");
			while(true) {
				
				socket = serverSocket.accept();
				System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 접속.");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// start()	
	
	void sendToAll(String msg) {
//		System.out.println("sendToAll start");
		Iterator it = clients.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				DataOutputStream out = (DataOutputStream)clients.get(it.next());
				out.writeUTF(msg);
			} catch (IOException e) { }
		}// while
	}// sendToAll
	
	public static void main(String[] args) {
		new TcpIpServer7().start();
	}
	
	class ServerReceiver extends Thread {
		
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		
		public ServerReceiver(Socket socket) {
//			System.out.println("ServerReceiver(socket) start");
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) { }
		}
		@Override
		public void run() {
//			System.out.println("run() start");
			String name="";
			try {
				name = in.readUTF();
				sendToAll("#"+name+"님이 들어왔음.");
				
				clients.put(name, out);
				System.out.println("현재 접속자수 : " + clients.size());
				while(in!=null) {
					sendToAll(in.readUTF());
				}
			} catch (IOException e) {
				// TODO: handle exception
			}finally {
				sendToAll("#"+name+"님이 나갔음.");
				clients.remove(name);
				System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 접속 종료.");
				System.out.println("현재 접속자 수 : " + clients.size());
			}
		}
	}
}
