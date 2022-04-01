package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerThree {

	//variables
	@FXML
    private ComboBox<String> idComboBox3;
	
	@FXML
	private Label etiqueta3;
	
	//incialzamos el listado
	public void initialize() {
		idComboBox3.getItems().setAll("A. Chicago", "B. San Francisco", "C. New York");
		idComboBox3.getSelectionModel().select(0);
	}
	
	@FXML
    void SeleccioComboBox3(ActionEvent event) {
		//si la respuesta es correcta, mostraremos un alert
		if (idComboBox3.getSelectionModel().getSelectedIndex() == 2) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Congratulations");
	        alert.setHeaderText("Has acertado las 3 preguntas");
	        alert.show();
		}
    }
	
	//metodo para escribir la respuesta de la pregunta 2 en la parte inferior
	public void setEtiqueta(String valor) {
		etiqueta3.setText(valor);
	}
	
}
