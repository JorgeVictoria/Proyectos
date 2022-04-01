package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class Controlador {
	
	@FXML
    private TextField txtUsuari;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Button btnAcceptar;

    @FXML
    void Validar(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Missatge");
        alert.setHeaderText("Usuari i contrasenya omplits");
        alert.show();
    }

    @FXML
    void controlBuit(KeyEvent event) {
    	if (txtUsuari.getText().isEmpty() || txtPass.getText().isEmpty()) {
    		btnAcceptar.disableProperty().set(true);
    	}
    	else btnAcceptar.disableProperty().set(false);
    }
	
}
