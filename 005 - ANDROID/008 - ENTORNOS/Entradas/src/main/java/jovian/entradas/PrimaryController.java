package jovian.entradas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * clase que gestiona la aplicacion de compra de entradas de cine
 * De momento solo se puede comprar una entrada, para posibles futuros proyectos se mejorará el servicio
 * @author Jorge Victoria Andreu
 * @since 20jan2022
 * @version 1.0
 */
public class PrimaryController {
	
	//creamos una entrada para crear la copia de la entrada
	Entrada miEntrada = new Entrada();
	
	//variables miembro de la clase
	@FXML
	private Button btnComprarEntrada;
	
	@FXML
	private Button btnImprimirEntrada;
	
	/**
	 * metodo para ir a la pantalla de compra de entradas
	 * @param event, recoge el evento del click en el boton
	 */
	@FXML
	private void comprarEntrada(ActionEvent event) {
		
		//como no hay entrada creada, creamos un objeto entrada
		Entrada entrada = new Entrada();
		
		//nos vamos a la pantalla para rellenar los datos de la entrada
		try {
			
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("secondary.fxml"));
            Stage stage = (Stage) btnComprarEntrada.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
            SecondaryController controlador = (SecondaryController) fxmlLoader.getController();
            
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * metodo para ir a la pantalla de impresion de entradas
	 * @param event, recoge el evento del click en el boton
	 */
	@FXML
	private void imprimirEntrada(ActionEvent event) {
		
		//nos vamos a la pantalla para rellenar los datos de la entrada
				try {
					
		    		FXMLLoader fxmlLoader = new FXMLLoader();
		            fxmlLoader.setLocation(getClass().getResource("final.fxml"));
		            Stage stage = (Stage) btnComprarEntrada.getScene().getWindow();
		            Scene scene = new Scene(fxmlLoader.load());
		            stage.setScene(scene);
		            
		            FinalController controlador = (FinalController) fxmlLoader.getController();
		            controlador.imprimirTicket(miEntrada);
		            
		            
				} catch(Exception e) {
					e.printStackTrace();
				}
		
	}

	/**
	 * metodo que copia los elementos de la entrada y activa y desactiva los botones
	 * @param entrada, recibe la entrada generada
	 */
	public void setEntrada(Entrada entrada) {
		
		btnImprimirEntrada.disableProperty().setValue(false);
		
		btnComprarEntrada.disableProperty().setValue(true);
		
		miEntrada = entrada;
		
	}
	
	

}
