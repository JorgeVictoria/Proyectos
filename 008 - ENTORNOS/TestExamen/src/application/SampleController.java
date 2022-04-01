package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class SampleController {
	
	@FXML
	private Label Label1;
	
	@FXML
	void ojo(MouseEvent Event) {
		
		System.out.println(Label1.getText().toString());
		
	}
	
}