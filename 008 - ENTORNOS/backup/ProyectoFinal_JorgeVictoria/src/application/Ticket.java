package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Ticket {
	
	boolean contrast;
	
	//coleccion de entradas vendidas
	List<Entrada> venudes = new ArrayList<Entrada>();
	
	@FXML
	private Label lbTicket;
	
	@FXML
	private Button btnImprimir;
	
	@FXML
	void volver(ActionEvent event) {
		
		try {
			if (contrast ) {
			   FXMLLoader fxmlLoader = new FXMLLoader();
	           fxmlLoader.setLocation(getClass().getResource("Principal.fxml"));
	           Stage stage = (Stage) btnImprimir.getScene().getWindow();
	           Scene scene = new Scene(fxmlLoader.load());
	           scene.getStylesheets().add(getClass().getResource("contrast.css").toExternalForm());
	           stage.setScene(scene);
	           
	           PrincipalController controlador = (PrincipalController) fxmlLoader.getController();
	           controlador.vuelta(contrast, venudes);
      
			} else {
				FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("Principal.fxml"));
		        Stage stage = (Stage) btnImprimir.getScene().getWindow();
		        Scene scene = new Scene(fxmlLoader.load());
		        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		        stage.setScene(scene);
		           
		        PrincipalController controlador = (PrincipalController) fxmlLoader.getController();
		        controlador.vuelta(contrast, venudes);
			}
           
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	/**
	 * metodo para imprimir el ticket
	 * @param contraste 
	 * @param vendidas 
	 * @param miEntrada, objeto con los datos de la entrada
	 */
	public void imprimirTicket(Entrada miEntrada, boolean contraste, List<Entrada> vendidas) {
		
		lbTicket.setText(miEntrada.toString());
		
		contrast = contraste;
		
		for(int i = 0; i < vendidas.size(); i++){
			venudes.add(vendidas.get(i));
		}
		
		
		
	}

}
