package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

public class Controlador {
	@FXML
    private ComboBox<String> idComboBox;
	
	public void initialize() {
		idComboBox.getItems().setAll("Opció A", "Opció B", "Opció C");
		idComboBox.getSelectionModel().select(0);
		
	}

    @FXML
    void SeleccioComboBox(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Missatge");
        alert.setHeaderText("Item: " + idComboBox.getSelectionModel().getSelectedIndex() + "   Valor: " + idComboBox.getValue());
        alert.show();

    }
	
}
