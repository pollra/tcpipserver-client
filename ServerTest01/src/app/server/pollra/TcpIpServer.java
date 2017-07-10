package app.server.pollra;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer {
	
	public static void main(String args[]) {
		ServerSocket serverSocket = null; 		// 객체를 생성. 
		
		try {
			serverSocket = new ServerSocket(8086);		// 인스턴스를 생성 후 8086번 포트와 결합(바인드)
			System.out.println(getTime()+"서버 준비됨.");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			try {
				System.out.println(getTime()+"연결요청대기.");
				// 서버소켓은 클라이언트의 연결요청이 올 때까지 실행을 멈추고 계속 기다림
				// 클라이언트의 연결요청이 들어오면 클라이언트 소켓과 통신할 새로운 소켓을 생성
				Socket socket = serverSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+"로부터 연결요청이 들어옴.");
				// 소켓의 출력스트림을 얻는다.
				OutputStream out = socket.getOutputStream();
				// System.out.println("out : " + out);
				DataOutputStream dos = new DataOutputStream(out);
				// 원격 소켓(remote socket)에 데이터를 보낸다.
				dos.writeUTF("[Notice] Test Messagel from Server.V01");
				System.out.println(getTime()+"데이터를 전송했습니다.");
				
				// 스트림과 소켓을 닫아줌
				dos.close();
				socket.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}//while
	}//main

	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
}
