package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class payFailController implements Initializable {

	@FXML private Label lbldata;
	@FXML private Button ok;
	@FXML private AnchorPane scenePane;
	
	@FXML void showDialog(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("필수 약관 동의");
		alert.setContentText("모든 필수 약관에 동의 해주세요.");
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK) {
			System.out.println("실패 확인창");
		}else {
			System.out.println("실패 거절창");
		}
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 결제 실패 창 확인 버튼을 눌렀을 때 꺼짐
		// 결제 실패 창만 꺼져야 하는데 하나만 끄는 방법을 모르겠어요
//		ok.setOnAction(e->{
//			System.exit(0);
//		});
		
	}
	
	

}
