package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class serverTask implements Runnable{

	Socket client;
	serverController sc;
	PrintWriter printer;
	BufferedReader reader;
	String name;
	boolean isRun = true;
	
	public serverTask(Socket client, serverController sc) {
		this.client = client;
		this.sc = sc;
		
		try {
			OutputStream os = client.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter writer = new BufferedWriter(osw);
			printer = new PrintWriter(writer,true);
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			sc.printText("client 연결 오류 : " + e.getMessage());
		}
	}
	
	@Override
	public void run() {
		sc.printText(client.getRemoteSocketAddress()+" receive 시작");
		
		while(isRun) {
		
		try {
		
			String receiveData = reader.readLine();
			if(receiveData == null) {
				break;
			}
			sc.printText(receiveData);
			String[] data = receiveData.split("\\|");
			String code = data[0];
			String text = data[1];
			
			
			
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

	}
	
}
