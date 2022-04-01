package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControllerPantalla2 {
	
	//variables
	@FXML
	private Label etiqueta;
	
	@FXML
	private ToggleGroup ToggleGroup1;
	
	@FXML
	private Button aceptar2;
	
	@FXML
	void setEtiqueta(String cadena) {
		etiqueta.setText(cadena);
	}
	
	//metodo que controla la opcion elegida
	@FXML
	void acepta2(ActionEvent event) {
		
		//creamos un objeto radioButton que almacena la opcion elegida
		RadioButton selectedRadioButton = (RadioButton) ToggleGroup1.getSelectedToggle();
		//pasamos a cadena la opcion elegida
		String plataforma = selectedRadioButton.getText();
		
		//cargamos la tercera escena
		try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("pantalla3.fxml"));
            Stage stage = (Stage) aceptar2.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            //creamos un objeto controlador al que le pasamos la cadena con la opcion elegida
            ControllerPantalla3 controlador = (ControllerPantalla3) fxmlLoader.getController();
            controlador.setValor(plataforma);


    	} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//salida de la aplicacion
	@FXML
	void salida2(ActionEvent event) {
		
		System.exit(0);
		
	}

}
