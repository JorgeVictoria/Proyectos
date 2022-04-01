package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerTwo {
	
	//variables
	@FXML
    private ComboBox<String> idComboBox2;
	
	@FXML
	private Label etiqueta2;
	
	//incialzamos el listado
	public void initialize() {
		idComboBox2.getItems().setAll("A. Indianapolis", "B. Green Bay", "C. Minnesota");
		idComboBox2.getSelectionModel().select(0);
	}
	
	
	@FXML
    void SeleccioComboBox2(ActionEvent event) {
		//si la respuesta es correcta, pasamos a la siguiente pantalla
		if (idComboBox2.getSelectionModel().getSelectedIndex() == 1) {
			try {
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("ControladorThree.fxml"));
	            Stage stage = (Stage) idComboBox2.getScene().getWindow();
	            Scene scene = new Scene(fxmlLoader.load());
	            stage.setScene(scene);

	            //creamos un objeto controlador de la siguiente pantalla y le enviamos la respuesta de este controlador
	            ControllerThree controlador = (ControllerThree) fxmlLoader.getController();
	            controlador.setEtiqueta("La respueta anterior era : " + idComboBox2.getSelectionModel().getSelectedItem().toString());
	   


	    	} catch(Exception e) {
				e.printStackTrace();
			}
		}
    }
	
	//metodo para escribir la respuesta de la pregunta 1 en la parte inferior
	public void setEtiqueta(String valor) {
		etiqueta2.setText(valor);
	}
	

}
