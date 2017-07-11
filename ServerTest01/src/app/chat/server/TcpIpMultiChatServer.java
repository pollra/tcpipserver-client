package app.chat.server;

import java.net.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;


public class TcpIpMultiChatServer extends Thread{
	HashMap clients;
	
	TcpIpMultiChatServer() {
		clients = new HashMap();
		Collections.synchronizedMap(clients);
	}
	@Override
	public void run(){
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(8086);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println("");
				System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 접속하였습니다.");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}// start()
	
	void sendToAll(String msg) {
		Iterator it = clients.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				DataOutputStream out = (DataOutputStream)clients.get(it.next());
				out.writeUTF(msg);
			} catch (IOException e) { }
		}// while
	}// sendToAll
	
	public static void main(String args[]) {
		TcpIpMultiChatServer charServer = new TcpIpMultiChatServer();
		charServer.start();
		marsterCode(charServer);
		
	}
	private static void marsterCode(TcpIpMultiChatServer charServer) {
		Scanner sc = new Scanner(System.in);
		String controll;
		while(true) {
			System.out.print("명령어 입력 :");
			controll = sc.nextLine();
			System.out.println();
			if(controll.equals("exit")) {
				System.out.println("서버가 종료됩니다.");
				charServer.sendToAll("서버가 종료되었습니다.");
				System.exit(0);
			}else{
				System.out.println("잘못된 명령입니다.");
			}
		}
	}
	
	public static String getTime() {
		return new SimpleDateFormat("[hh:mm:ss]").format(new Date());
	}
	
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		
		ServerReceiver(Socket socket){
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) { }
		}//ServerReceiver()
		public void run() {
			String name ="";
			try {
				name = in.readUTF();
				sendToAll("#"+name+"님이 들어오셨습니다.");
				
				clients.put(name, out);
				System.out.println("현재 서버접속자 수는 "+clients.size()+"입니다.");
				while(in!=null) {
					sendToAll(in.readUTF());
				}
			} catch (IOException e) {
				// TODO: handle exception
			} finally {
				sendToAll("#"+name+"님이 나가셨습니다.");
				clients.remove(name);
				System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 접속을 종료하였습니다.");
				System.out.println("현재 서버접속자 수는 " + clients.size()+"입니다.");
			}
		}
	}//ServerReceiver
	
	
}
