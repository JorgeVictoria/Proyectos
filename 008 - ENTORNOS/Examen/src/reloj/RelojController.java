package reloj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RelojController extends Main {
	
	@FXML
	private Button btnHora;
	
	@FXML
	private Button btnMinutos;
	
	@FXML
	private Label hora;
	
	@FXML
	private Label minutos;
	
	@FXML
	private Label formatoHora;
	
	@FXML
	private CheckBox cbFormato;
	
	@FXML
	void cambiarHora(ActionEvent event) {
		
		try {
			
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Hora.fxml"));
            Stage stage = (Stage) btnHora.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
            MinutosController controlador = (MinutosController) fxmlLoader.getController();
            controlador.setEtiqueta();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	void cambiarMinutos(ActionEvent event) {
		
try {
			
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Minutos.fxml"));
            Stage stage = (Stage) btnMinutos.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
            HoraController controlador = (HoraController) fxmlLoader.getController();
            controlador.setEtiqueta();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	void seleccionarFormat(ActionEvent event) {
		
		int unaHora = laHora.getHora();
		
		boolean isSelected = cbFormato.isSelected();
		
		if(isSelected) {
			if(unaHora == 24) hora.setText("00");
			else hora.setText(Integer.toString(unaHora));
			formatoHora.setVisible(false);
		}
		
		else if(!isSelected && unaHora > 12) {
			hora.setText(Integer.toString(unaHora-12));
			formatoHora.setVisible(true);
			formatoHora.setText("PM");
		}
		
		else if(!isSelected && unaHora < 12) {
			hora.setText(Integer.toString(unaHora));
			formatoHora.setVisible(true);
			formatoHora.setText("AM");
		}
	}
	

	public void setEtiqueta() {
		
		minutos.setText(Integer.toString(laHora.getMinutos()));
		hora.setText(Integer.toString(laHora.getHora()));
		
	}
	
}
