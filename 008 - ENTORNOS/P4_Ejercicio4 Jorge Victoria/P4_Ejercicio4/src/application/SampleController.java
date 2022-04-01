package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SampleController {
	
	//variables
		@FXML
	    private PasswordField pfPassword;
		
		@FXML
		private TextField tfLogin;
		
		@FXML
		private Button btnAceptar;
		
		@FXML
	    void comprobarVacio(ActionEvent event) {
			if(pfPassword.getText().isEmpty() || tfLogin.getText().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setTitle("Missatge");
		        alert.setHeaderText("DEBE RELLENAR LOS DOS CAMPOS");
	        alert.show();
			}
		}
	
}
