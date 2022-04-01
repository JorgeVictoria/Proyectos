package application;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class SampleController {
	
	@FXML
	public ComboBox<String> mycombobox;
	
	public void initialize() {
		mycombobox.getItems().setAll("Castellano", "Valenciano", "Inglés");
		mycombobox.getSelectionModel().select(0);
	}
	
}
