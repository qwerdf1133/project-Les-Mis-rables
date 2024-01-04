package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class serverController implements Initializable {

	@FXML private TextArea displayText;
	@FXML private Button btnStartStop;
	
	ExecutorService serverPool;
	ServerSocket server;
	Hashtable<String, PrintWriter> clients;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Start 버튼으로 서버를 온, 오프 할 수 있음
		btnStartStop.setOnAction(e->{
			String text = btnStartStop.getText();
			if(text.equals("_Start")) {
				startServer();
				btnStartStop.setText("S_top");
			}else {
				stopServer();
				btnStartStop.setText("_Start");
			}
		});
	}
	
	public void startServer() {
		serverPool = Executors.newFixedThreadPool(50);
		clients = new Hashtable<>();
		
		int portNumber = 5001;	// 서버 접속 포트 번호. 바로 들어가지게 번호를 바로 할당함
		
		try {
			server = new ServerSocket(portNumber); 
		} catch (IOException e) {
			return;
		}
		
		Runnable run = new Runnable() {

			@Override
			public void run() {
				printText("[ START SERVER ]");
				
				while(true) {
					try {
						printText("client 연결 대기");
						
						Socket client = server.accept();
						String address = client.getRemoteSocketAddress().toString();
						
						printText("[ 연결 수락 : " + address + " ]");
						
						serverPool.submit(new serverTask(client, serverController.this));
					} catch (IOException e) {
						stopServer();
						break;
						
					}
				}
			}
			
		};
		
		serverPool.submit(run);
	}
	
	public void stopServer() {
		if(clients != null && !clients.isEmpty()) {
			for(PrintWriter p : clients.values()) {
				if(p != null) p.close();
			}
		}
		clients.clear();
		
		if(server != null && !server.isClosed()) {
			try {
				server.close();
			} catch (IOException e) {}
		}
		
		if(serverPool != null && !serverPool.isShutdown()) {
			serverPool.shutdown();
		}
		printText("[서버 중지]");
	}
	
	public void printText(String text) {
		Platform.runLater(()->{
			displayText.appendText(text+"\n");
		});
	}

}
