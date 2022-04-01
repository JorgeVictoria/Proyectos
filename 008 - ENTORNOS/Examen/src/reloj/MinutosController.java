package reloj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MinutosController extends Main {
	
	@FXML
	private TextField tfMinutos;
	
	@FXML
	private Button btnVolver;
	
	@FXML
	void volver(ActionEvent event) {
		
		String texto = tfMinutos.getText().toString();
		int valido = Integer.parseInt(texto);
		
		if(valido >= 0 && valido <= 59) {
			laHora.setMinutos(valido);
		
			try {
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("Reloj.fxml"));
	            Stage stage = (Stage) btnVolver.getScene().getWindow();
	            Scene scene = new Scene(fxmlLoader.load());
	            stage.setScene(scene);
	            
	            RelojController controlador = (RelojController) fxmlLoader.getController();
	            controlador.setEtiqueta();
	            
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Numero de minutos incorrecto");
			alert.show();
		}
		
	}

	public void setEtiqueta() {
		
		tfMinutos.setText(Integer.toString(laHora.getMinutos()));
		
	}

}
