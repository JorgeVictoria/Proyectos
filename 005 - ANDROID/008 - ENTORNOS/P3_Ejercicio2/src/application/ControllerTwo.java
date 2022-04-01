package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ControllerTwo {
	
	@FXML
    private ComboBox<String> idComboBox2;
	
	public void initialize() {
		idComboBox2.getItems().setAll("A. Indianapolis", "B. Green Bay", "C. Minnesota");
		idComboBox2.getSelectionModel().select(0);
	}
	
	@FXML
    void SeleccioComboBox2(ActionEvent event) {
		if (idComboBox2.getSelectionModel().getSelectedIndex() == 1) {
			try {
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("ControladorThree.fxml"));
	            Stage stage = (Stage) idComboBox2.getScene().getWindow();
	            Scene scene = new Scene(fxmlLoader.load());
	            stage.setScene(scene);

	            ControllerThree controlador = (ControllerThree) fxmlLoader.getController();
	   


	    	} catch(Exception e) {
				e.printStackTrace();
			}
		}
    }

}
