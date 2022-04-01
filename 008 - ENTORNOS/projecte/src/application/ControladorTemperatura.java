package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControladorTemperatura {
	
	//variables locales
	private final static int TEMP_MAXIMA = 30;
	private final static int TEMP_MINIMA = 15;
	private static int temperatura = 22;
	private static String llumGlobal = "";

	
	@FXML
	private Button btnAceptar;

	@FXML
	private Label lblTemperatura;

	@FXML
	void btnAceptar(ActionEvent event) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("VistaPrincipal.fxml"));
        Stage stage = (Stage) btnAceptar.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
		
        ControladorVistaPrincipal control = (ControladorVistaPrincipal) fxmlLoader.getController();
		control.setTemperatura(lblTemperatura.getText());
		control.setLlumns(llumGlobal);
	}

	
	@FXML
	void btnBajar(MouseEvent event) {
			
			if(temperatura > TEMP_MINIMA) {
				temperatura--;
				String temperaturaMenos = "" + temperatura;
				lblTemperatura.setText(temperaturaMenos);
			}
	}

	@FXML
	void btnSubir(MouseEvent event) {
		
		if(temperatura < TEMP_MAXIMA) {
			temperatura++;
			String temperaturaMas = "" + temperatura;
			lblTemperatura.setText(temperaturaMas);
		}

	}


	public void enviaDatos(String llum, String temp) {
		
		//pasamos la temperatura que hay en la ventana principal
		lblTemperatura.setText(temp);
		//almacenamos la lluma en una variable global de la clase
		llumGlobal = llum;
		
	}

}
