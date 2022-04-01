package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MinutosController {
	
	HoraCompleta laHora = new HoraCompleta();
	
	@FXML
	private TextField tfMinutos;
	
	@FXML
	private Button btnVolver;
	
	@FXML
	void volver(ActionEvent event) {
		boolean error = false;
		int valor = 0;
		
		try {
			valor = Integer.parseInt(tfMinutos.getText().toString());
		} catch (Exception e) {
			error = true;
		}
		
			
			if (valor < 0 || valor > 59 || error == true) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("El valor es incorrecto." );
			alert.show();
			error = false;
		}
		
		else {
			
			laHora.setMinuto(valor);
			
			try {
				
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("reloj.fxml"));
	            Stage stage = (Stage) btnVolver.getScene().getWindow();
	            Scene scene = new Scene(fxmlLoader.load());
	            stage.setScene(scene);
	            
	            RelojController controlador = (RelojController) fxmlLoader.getController();
	            controlador.setEtiqueta(laHora);
	            
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setHora(HoraCompleta unaHora) {
		
		laHora.setHora(unaHora.getHora());
		laHora.setMinuto(unaHora.getMinuto());
		
	}

}
