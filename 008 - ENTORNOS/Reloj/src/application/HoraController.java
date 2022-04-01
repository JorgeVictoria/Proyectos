package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HoraController extends HoraCompleta {
	
	HoraCompleta laHora = new HoraCompleta();
	
	@FXML
	private Label lbHora;
	
	@FXML
	private ImageView ivIncremento;
	
	@FXML
	private ImageView ivDecremento;
	
	@FXML 
	private ComboBox<String> cbZonaHoraria;
	
	public void initialize() {
	cbZonaHoraria.getItems().addAll("Hora Peninsular", "Hora de Canarias");
	//cbZonaHoraria.getSelectionModel().select(0);
}
	
	@FXML
	private Button btnVolver;
	
	@FXML
	void incrementar(MouseEvent event) {
		
		int hora = laHora.getHora();
		hora++;
		if(hora == 24) hora = 0;
		laHora.setHora(hora);
		lbHora.setText(String.valueOf(hora));
		
	}
	
	@FXML
	void decrementar(MouseEvent event) {
		
		int hora = laHora.getHora();
		hora--;
		if(hora < 0) hora = 23;
		laHora.setHora(hora);
		lbHora.setText(String.valueOf(hora));
	}
	
	@FXML
	void cambiarHora(ActionEvent event) {
		
	}
	
	@FXML
	void volver(ActionEvent event) {
		
		if(cbZonaHoraria.getSelectionModel().getSelectedIndex() < 0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Debe seleccionar una zona horaria" );
			alert.show();
		}
		else {
			if(cbZonaHoraria.getSelectionModel().getSelectedIndex() == 1) {
				laHora.setHora(Integer.parseInt(lbHora.getText().toString()));
				laHora.setHora(laHora.getHora()-1);
			}
			
			else laHora.setHora(Integer.parseInt(lbHora.getText().toString()));
		
		
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

	public void setEtiqueta(HoraCompleta unaHora) {
		
		laHora.setHora(unaHora.getHora());
		laHora.setMinuto(unaHora.getMinuto());
		lbHora.setText(String.valueOf(laHora.getHora()));
		
	}
	
	
	

}
