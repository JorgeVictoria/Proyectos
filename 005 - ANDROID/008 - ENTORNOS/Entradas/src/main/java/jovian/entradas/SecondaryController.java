package jovian.entradas;

import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * clase para controlar la compra de entradas
 * @author Jorge Victoria Andreu
 * @version 1.0
 * @since 20jan2022
 */
public class SecondaryController {
	
	//variable para controlar si hemos aceptado la compra de la entrada
	private static boolean entradaComprada = false;
	
	//creamos una entrada
	Entrada entrada = new Entrada();
	
	//precio de la entrada
	double precio = 9.00;
	double preciofinal = 0;
	
	//variables miembro del layout
	@FXML
	private ComboBox<String> cbPelicula;
	
	@FXML
	private ComboBox<String> cbSesion;
	
	@FXML
	private ComboBox<Integer> cbFila;
	
	@FXML
	private ComboBox<Integer> cbAsiento;
	
	@FXML
	private ComboBox<String> cbDescuento;
	
	@FXML
	private Label lbPrecio;
	
	@FXML
	private Button btnComprar;
	
	
	/**
	 * En este metodo añadimos los items (u opciones) que forman parte de cada uno de los combobox
	 * ademas tenemos un metodo que calcula el precio de la entrada en cuanto seleccionemos el tipo de descuento
	 */
	@FXML
	public void initialize() {
		
	    cbPelicula.getItems().removeAll(cbPelicula.getItems());
	    cbPelicula.getItems().addAll("Sala1:Iron Man", "Sala2:Thor", "Sala3:Capitan America");
	    cbPelicula.getSelectionModel().select("Sala1:Iron Man");
	    
	    cbSesion.getItems().removeAll(cbSesion.getItems());
	    cbSesion.getItems().addAll("Jueves 20, 16:00", "Jueves 20, 19:00", "Jueves 20, 22:00");
	    cbSesion.getSelectionModel().select("Jueves 20, 16:00");
	    
	    cbFila.getItems().removeAll(cbFila.getItems());
	    cbFila.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
	    cbFila.getSelectionModel().select(0);
	    
	    cbAsiento.getItems().removeAll(cbAsiento.getItems());
	    cbAsiento.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
	    cbAsiento.getSelectionModel().select(0);
	    
	    cbDescuento.getItems().removeAll(cbDescuento.getItems());
	    cbDescuento.getItems().addAll("Ninguno", "Estudiante: 50%", "Jubilado: 40%");
	    cbDescuento.getSelectionModel().select("Ninguno");
	    
	    cbDescuento.valueProperty().addListener(new ChangeListener<String>() {
	    	@Override public void changed(ObservableValue ov,String t, String t1) {
	    		if(t1.equals("Ninguno")) lbPrecio.setText(String.valueOf(precio) + " Euros");
	    		else if(t1.equals("Estudiante: 50%")) {
	    			lbPrecio.setText(String.valueOf(precio/2) + " Euros");
	    			preciofinal = precio/2;
	    		}
	    		else {
	    			lbPrecio.setText(String.valueOf(precio - (precio*40/100)) + " Euros");
	    			preciofinal = precio - (precio*40/100);
	    		}
	    	}
	    });
	    
	    
	}

	/**
	 * metodo para realizar la compra de la entrada
	 * Recoge los valores de los diferentes combobox
	 * @param event: recoge el evento de pulsar click
	 */
	@FXML
	private void comprarEntrada(ActionEvent event) {
		
		
		//primero mostramos un alert para ver si esta seguro de la compra
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("TuEntrada.com");
		alert.setContentText("Desea comprar la entrada?");
		Optional<ButtonType> option = alert.showAndWait();
		
			//si la respuesta es si
		    if (option.get() == ButtonType.OK) {
		        	//ponemos la variable a true
		        	entradaComprada = true;
		        	System.out.println(entradaComprada);
		 
		        	//creamos un arraylist para separar la sala de la pelicula
		        	String[] split = cbPelicula.getValue().split(":");
		        	
		        	//almacenamos los datos de la entrada en el objeto
		        	entrada.setSala(split[0]);
		        	entrada.setPelicula(split[1]);
		        	
		        	entrada.setHora(cbSesion.getValue());
		        	
		        	entrada.setFila(cbFila.getValue());
		        	
		        	entrada.setAsiento(cbAsiento.getValue());
		        	
		        	entrada.setPrecio(preciofinal);
		        	
		        	//nos vamos a la pantalla principal
		        	
		     } 
	
			if(entradaComprada) {
				try {
					
				   FXMLLoader fxmlLoader = new FXMLLoader();
		           fxmlLoader.setLocation(getClass().getResource("primary.fxml"));
		           Stage stage = (Stage) btnComprar.getScene().getWindow();
		           Scene scene = new Scene(fxmlLoader.load());
		           stage.setScene(scene);
		           
		           PrimaryController controlador = (PrimaryController) fxmlLoader.getController();
		           controlador.setEntrada(entrada);
		           
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
	}
	
	

}