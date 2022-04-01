package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RelojController extends HoraCompleta {
	
	HoraCompleta laHora = new HoraCompleta();
	
	@FXML
	private Button btnHora;
	
	@FXML
	private Button btnMinutos;
	
	@FXML
	private Label lbHora;
	
	@FXML
	private Label lbMinutos;
	
	@FXML
	private Label lbFormato;
	
	@FXML
	private CheckBox cbFormato;
	
	@FXML
	void cambiarHora(ActionEvent event) {
		
		laHora.setHora(Integer.parseInt(lbHora.getText().toString()));
		laHora.setMinuto(Integer.parseInt(lbMinutos.getText().toString()));
		
		try {
			
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Hora.fxml"));
            Stage stage = (Stage) btnHora.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
            HoraController controlador = (HoraController) fxmlLoader.getController();
            controlador.setEtiqueta(laHora);
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void cambiarMinutos(ActionEvent event) {
		
		laHora.setHora(Integer.parseInt(lbHora.getText().toString()));
		laHora.setMinuto(Integer.parseInt(lbMinutos.getText().toString()));
		
		try {
			
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Minutos.fxml"));
            Stage stage = (Stage) btnMinutos.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
            MinutosController controlador = (MinutosController) fxmlLoader.getController();
            controlador.setHora(laHora);
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	void cambiarFormato(ActionEvent event) {
		
		if(cbFormato.isSelected()) {
			lbFormato.setVisible(false);
			lbHora.setText(String.valueOf(laHora.getHora()));
		}
		
		else {
			lbFormato.setVisible(true);
			if(laHora.getHora() > 12 ) {
				lbHora.setText(String.valueOf(laHora.getHora()-12));
				lbFormato.setText("PM");
			}
			else lbFormato.setText("AM");
			
			if(laHora.getHora() == 0 ) lbHora.setText("12");
		}
		
	}

	public void setEtiqueta(HoraCompleta unaHora) {
		
		
		laHora.setHora(unaHora.getHora());
		laHora.setMinuto(unaHora.getMinuto());
		lbHora.setText(String.valueOf(laHora.getHora()));
		lbMinutos.setText(String.valueOf(laHora.getMinuto()));
		
	}
	
}
