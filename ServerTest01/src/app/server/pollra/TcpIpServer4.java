package app.server.pollra;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer4 implements Runnable{
	
	ServerSocket serverSocket;
	Thread[] threadArr;
	
	public static void main(String[] args) {
		
		// 5개의 쓰레드를 생성하는 서버를 생성
		TcpIpServer4 server = new TcpIpServer4(5);
		server.start();
	}// main
	public TcpIpServer4(int num) {
		try {
			// 서버소켓을 생성. 8086포트와 bind
			serverSocket = new ServerSocket(8086);
			System.out.println(getTime() + "서버 준비완료.");
			
			threadArr = new Thread[num];
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void start() {
		for(int i=0;i<threadArr.length; i++) {
			System.out.println(i);
			threadArr[i] = new Thread(this);
			threadArr[i].start();
		}
	}
	private String getTime() {
		return new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + Thread.currentThread().getName();
	}
	@Override
	public void run() {
		while(true) {
			try {
				System.out.println(getTime() + "가 연결요청대기.");
				
				Socket socket = serverSocket.accept();
				System.out.println(getTime() + socket.getInetAddress() + "로부터 연결요청.");
				
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Messagel from server.");
				System.out.println(getTime() + "데이터 전송완료.");
				dos.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
