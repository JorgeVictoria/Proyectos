package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorVistaPrincipal {
    @FXML
    private Button btnLlums;

    @FXML
    private Button btnTemperatura;

    @FXML
    private Label lblLlums;

    @FXML
    private Label lblTemperatura;

    @FXML
    void btnLlums(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("VistaLlums.fxml"));
        Stage stage = (Stage) btnLlums.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        
		ControladorLlums conTemp = (ControladorLlums) fxmlLoader.getController();
		conTemp.enviaDatos(lblLlums.getText(), lblTemperatura.getText());
    }

    @FXML
    void btnTemperatura(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("VistaTemperatura.fxml"));
        Stage stage = (Stage) btnTemperatura.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        
        ControladorTemperatura conTemp = (ControladorTemperatura) fxmlLoader.getController();
		conTemp.enviaDatos(lblLlums.getText(), lblTemperatura.getText());
		

    }

	public void setTemperatura(String temp) {
		lblTemperatura.setText(temp);
		
	}
	
	public void setLlumns(String temp) {
		lblLlums.setText(temp);
		
	}

}

