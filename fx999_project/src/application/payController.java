package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class payController implements Initializable{
	
	@FXML private Label lbldate;
	@FXML private ToggleGroup group;
	@FXML private RadioButton card, kakao, samsung, apple, naver, toss;
	@FXML private CheckBox terms1, terms2, terms3, terms4;
	@FXML private Button pay;
	@FXML private void selectCheckBox(ActionEvent event) {
		if(terms1.isSelected() && terms2.isSelected() && terms3.isSelected()) {
			
		}
	}
	
	public void toggle() {
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("FXML LOAD COMPLETED");
	}
	
}
