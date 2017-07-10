package app.client.pollra;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class TcpIpClient {
	public static void main(String[] args) {
		try {
			String serverIP = null;
			
			System.out.println("서버에 연결중... 서버IP : " + serverIP);
			// 소켓을 생성하여 연결을 요청함
			Socket socket = new Socket(serverIP, 8086);
			
			// 소켓의 입력스트림을 얻는다.
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			// 소켓으로 부터 받은 데이터를 출력한다.
			System.out.println("Server :"+dis.readUTF());
			System.out.println("연결을 종료함.");
			
			// 스트림과 소켓을 닫음
			dis.close();
			socket.close();
			System.out.println("연결이 종료되었습니다.");
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
