package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControladorHome {
    @FXML
    private Label lblUsuari;
    
    @FXML
    private Button btnEixir;

    @FXML
    void Eixir(ActionEvent event) {
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Vista.fxml"));
            Stage stage = (Stage) btnEixir.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
    	} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public void setUsuari(String IdUsuario){
        lblUsuari.setText(IdUsuario);
    }

}

