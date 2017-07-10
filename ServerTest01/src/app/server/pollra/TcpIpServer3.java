package app.server.pollra;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer3 {
	
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
				// 요청 대기시간을 5초로 지정
				// 5초동안 요청이 없으면 SocketTimeoutException이 발생한다.
				serverSocket.setSoTimeout(5*1000);
				Socket socket = serverSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+"로부터 연결요청이 들어옴.");

				System.out.println("getPort() : "+socket.getPort());
				System.out.println("getLocalPort() : "+socket.getLocalPort());
				
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Messagel from Server_V0.3");
				System.out.println(getTime()+"데이터를 전송했습니다.");
				
				// 스트림과 소켓을 닫아줌
				dos.close();
				socket.close();
			}catch (SocketTimeoutException e) {
				System.out.println("[!] Error : 지정된 시간동안 접속요청이 없었습니다. 프로그램을 종료합니다.");
				e.printStackTrace();
				System.exit(0);
			}catch (NullPointerException e) {
				System.out.println("[!] Error : 서버가 가동중입니다. 프로그램을 종료합니다. time"+getTime());
				e.printStackTrace();
				System.exit(0);
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
