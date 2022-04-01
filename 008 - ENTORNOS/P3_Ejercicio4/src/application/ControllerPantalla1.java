package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControllerPantalla1 {
	
	//variables
	@FXML
	private TextField usuario;
	
	@FXML
	private TextField password;
	
	@FXML
	private Button acceder1;
	
	@FXML 
	private Button Salir1;
	
	//metodo para controlar el boton de acceso
	@FXML
    void acceso1(ActionEvent event) {
		
		//comprobamos que los datos son correctos. Si no son, mostramos un warning
		if(!usuario.getText().toString().equals("Test") || !password.getText().toString().equals("123456")) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Datos incorrectos");
			alert.show();
				
		} else {
			
			//si los datos de acceso son correctos, pasamos a la siguiente escena
			try {
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("pantalla2.fxml"));
	            Stage stage = (Stage) acceder1.getScene().getWindow();
	            Scene scene = new Scene(fxmlLoader.load());
	            stage.setScene(scene);

	            //construimos un objeto controlador al que le pasamos el nombre de usuario
	            ControllerPantalla2 controlador = (ControllerPantalla2) fxmlLoader.getController();
	            controlador.setEtiqueta("Hola " + usuario.getText().toString() + ", ¿cual es su plataforma favorita?" );


	    	} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	//metodo para salir del programa si pulsamos salir
	@FXML
    void salida1(ActionEvent event) {
		
		System.exit(0);
		
	}
	
	//metodo para activar el boton acceder cuando hayamos escrito usuario y password
	@FXML
	void controlTexto1(KeyEvent event) {
		
		if (usuario.getText().isEmpty() || password.getText().isEmpty()) {
    		acceder1.disableProperty().set(true);
    	}
		else acceder1.disableProperty().set(false);
	}
	
}
