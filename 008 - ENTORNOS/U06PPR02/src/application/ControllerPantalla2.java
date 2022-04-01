package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerPantalla2 extends Main {
	
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
		
		//en funcion de la plataforma elegida, aumentamos sus votos en el array
		if(plataforma.toUpperCase().equals("NETFLIX")) votos.setTotalVotos(0);
		else if (plataforma.toUpperCase().equals("HBO"))  votos.setTotalVotos(1);
		else if (plataforma.toUpperCase().equals("DISNEY+")) votos.setTotalVotos(2);
		else votos.setTotalVotos(3);
		
		//cargamos la tercera escena
		try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("pantalla3.fxml"));
            Stage stage = (Stage) aceptar2.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            //creamos un objeto controlador y llamamos al metodo setValor para que muestre las estadistica
            ControllerPantalla3 controlador = (ControllerPantalla3) fxmlLoader.getController();
            controlador.setValor();


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
