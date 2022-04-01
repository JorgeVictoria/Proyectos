package reloj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HoraController extends Main {
	
	@FXML
	private Label hora;
	
	@FXML
	private ComboBox<String> cbformato;
	
	/*public void initialize() {
		cbformato.getItems().setAll("Hora Peninsular", "Hora de Canarias");
	}*/
	
	@FXML
	private Button btnVolver;
	
	@FXML
	private Button incremento;
	
	@FXML
	private Button decremento;
	
	@FXML
	void cambiarFormato(ActionEvent event) {
		
	}
	
	@FXML
	void incrementarHora(ActionEvent event) {
		
		int unaHora = laHora.getHora();
		unaHora++;
		if(unaHora == 25) unaHora = 0;
		hora.setText(Integer.toString(unaHora));
		laHora.setHora(unaHora);
		
	}
	
	@FXML
	void decrementarHora(ActionEvent event ) {
		
		int unaHora = laHora.getHora();
		unaHora--;
		if(unaHora == -1) unaHora = 24;
		hora.setText(Integer.toString(unaHora));
		laHora.setHora(unaHora);
	}
	
	@FXML 
	void volver(ActionEvent event) {
		
		try {
			
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Reloj.fxml"));
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
            HoraController controlador = (HoraController) fxmlLoader.getController();
            controlador.setEtiqueta();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@FXML
	public void setEtiqueta() {
		
		hora.setText(Integer.toString(laHora.getHora()));
		
	}
	
	

}
