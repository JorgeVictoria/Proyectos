package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Controlador {
	
	@FXML
    private TextField txtUsuari;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Button btnAcceptar;
    
    @FXML
    private Label lblUsuari;

    @FXML
    void Validar(ActionEvent event) {
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Home.fxml"));
            Stage stage = (Stage) btnAcceptar.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            ControladorHome controlador = (ControladorHome) fxmlLoader.getController();
            controlador.setUsuari(txtUsuari.getText());


    	} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void controlBuit(KeyEvent event) {
    	if (txtUsuari.getText().isEmpty() || txtPass.getText().isEmpty()) {
    		btnAcceptar.disableProperty().set(true);
    	}
    	else btnAcceptar.disableProperty().set(false);
    }
	
}
