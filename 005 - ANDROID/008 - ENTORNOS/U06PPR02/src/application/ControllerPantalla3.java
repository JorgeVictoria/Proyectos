package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerPantalla3 extends Main{
	
	//variables miembro
	@FXML
	private Label NetVotos;
	
	@FXML
	private Label NetPorcent;
	
	@FXML
	private Label HBOVotos;
	
	@FXML
	private Label HBOPorcent;
	
	@FXML
	private Label DisneyVotos;
	
	@FXML
	private Label DisneyPorcent;
	
	@FXML
	private Label OtrosVotos;
	
	@FXML
	private Label OtrosPorcent;
	
	@FXML
	private Button Salir;
	
	@FXML
	private Button grafico;
	
	//metodo para rellenar la pantalla con los datos de los votos, porcentajes...
	void setValor() {
		
		//mostramos los votos por pantalla
		NetVotos.setText(String.format("%d", votos.getTotalVotos()[0]));
		HBOVotos.setText(String.format("%d", votos.getTotalVotos()[1]));
		DisneyVotos.setText(String.format("%d", votos.getTotalVotos()[2]));
		OtrosVotos.setText(String.format("%d", votos.getTotalVotos()[3]));
		
		//mostramos los porcentajes por pantalla
		int totalVotos = votos.getTotalVotos()[0] + votos.getTotalVotos()[1] + votos.getTotalVotos()[2] + votos.getTotalVotos()[3];
		NetPorcent.setText(String.format("%d %s", (votos.getTotalVotos()[0]*100)/totalVotos, "%"));
		HBOPorcent.setText(String.format("%d %s", (votos.getTotalVotos()[1]*100)/totalVotos, "%"));
		DisneyPorcent.setText(String.format("%d %s", (votos.getTotalVotos()[2]*100)/totalVotos, "%"));
		OtrosPorcent.setText(String.format("%d %s", (votos.getTotalVotos()[3]*100)/totalVotos, "%"));
		
	}
	
	//salida
	@FXML
	void salida(ActionEvent event) {
		
		//volvemos a la segunga escena para volver a votar
				try {
		    		FXMLLoader fxmlLoader = new FXMLLoader();
		            fxmlLoader.setLocation(getClass().getResource("pantalla2.fxml"));
		            Stage stage = (Stage) Salir.getScene().getWindow();
		            Scene scene = new Scene(fxmlLoader.load());
		            stage.setScene(scene);

		    	} catch(Exception e) {
					e.printStackTrace();
				}
		
	}
	
	//nos vamos a la pantalla cuatro
	@FXML
	void verGrafico(ActionEvent event) {
		
		try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("pantalla4.fxml"));
            Stage stage = (Stage) Salir.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
            // pasamos el objeto votos
            EstadisticasController controller = fxmlLoader.getController();
            controller.pintarGrafica(votos);

    	} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
