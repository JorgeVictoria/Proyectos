package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ControllerOne {
	
	@FXML
    private ComboBox<String> idComboBox1;
	
	public void initialize() {
		idComboBox1.getItems().setAll("A. Memphis", "B. Detroit", "C. Chicago");
		idComboBox1.getSelectionModel().select(0);
	}
	
	@FXML
    void SeleccioComboBox1(ActionEvent event) {
		if (idComboBox1.getSelectionModel().getSelectedIndex() == 2) {
			try {
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("ControladorTwo.fxml"));
	            Stage stage = (Stage) idComboBox1.getScene().getWindow();
	            Scene scene = new Scene(fxmlLoader.load());
	            stage.setScene(scene);

	            ControllerTwo controlador = (ControllerTwo) fxmlLoader.getController();
	   


	    	} catch(Exception e) {
				e.printStackTrace();
			}
		}
    }
	
}
