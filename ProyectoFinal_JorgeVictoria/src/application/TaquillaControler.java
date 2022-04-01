package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class TaquillaControler{
	
	//variable para controlar si hemos aceptado la compra de la entrada
	private static boolean entradaComprada = false;

	//coleccion de entradas vendidas
	List<Entrada> venudes = new ArrayList<Entrada>();

	//creamos una entrada
	Entrada entrada = new Entrada();

	//precio de la entrada
	double precio = 9.00;
	double preciofinal = precio;
	
	boolean contrast;

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
	private Button btnPagar;


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
	    		if(t1.equals("Ninguno")){
	    			preciofinal = calcularPrecio(0);
	    			lbPrecio.setText(String.valueOf(preciofinal) + " Euros");
	    			
	    		}
	    		else if(t1.equals("Estudiante: 50%")) {
	    			preciofinal = calcularPrecio(50);
	    			lbPrecio.setText(String.valueOf(preciofinal) + " Euros");
	    		}
	    		else {
	    			preciofinal = calcularPrecio(40);
	    			lbPrecio.setText(String.valueOf(preciofinal) + " Euros");
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
	 
	        	//creamos un arraylist para separar la sala de la pelicula
	        	String[] split = cbPelicula.getValue().split(":");
	        	
	        	//almacenamos los datos de la entrada en el objeto
	        	entrada.setSala(split[0]);
	        	entrada.setPelicula(split[1]);
	        	
	        	entrada.setHora(cbSesion.getValue());
	        	
	        	entrada.setFila(cbFila.getValue());
	        	
	        	entrada.setAsiento(cbAsiento.getValue());
	        	
	        	entrada.setPrecio(preciofinal);
	        	
	        	//comprobamos si el asiento esta vacio
	        	entradaComprada = compararEntrada(entrada);
	        	
	        	if(!entradaComprada ) {
	        		Alert alerta = new Alert(Alert.AlertType.WARNING);
	        		alerta.setTitle("TuEntrada.com");
	        		alerta.setContentText("El asiento no esta disponible");
	        		alerta.show();
	        	}
	        	
	        	
	        	
	        	
	     } 

		if(entradaComprada) {
			
			venudes.add(entrada);
			
			
			
			try {
				if (contrast ) {
				   FXMLLoader fxmlLoader = new FXMLLoader();
		           fxmlLoader.setLocation(getClass().getResource("Principal.fxml"));
		           Stage stage = (Stage) btnPagar.getScene().getWindow();
		           Scene scene = new Scene(fxmlLoader.load());
		           scene.getStylesheets().add(getClass().getResource("contrast.css").toExternalForm());
		           stage.setScene(scene);
		           
		           PrincipalController controlador = (PrincipalController) fxmlLoader.getController();
		           controlador.setEntrada(entrada, venudes, contrast);
	      
				} else {
					FXMLLoader fxmlLoader = new FXMLLoader();
			        fxmlLoader.setLocation(getClass().getResource("Principal.fxml"));
			        Stage stage = (Stage) btnPagar.getScene().getWindow();
			        Scene scene = new Scene(fxmlLoader.load());
			        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			        stage.setScene(scene);
			           
			        PrincipalController controlador = (PrincipalController) fxmlLoader.getController();
			        controlador.setEntrada(entrada, venudes, contrast);
				}
	           
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean compararEntrada(Entrada copiaEntrada) {
		
		boolean asientoLibre = true;
		
		if(venudes.size() == 0) asientoLibre = true;
		else {
			for(int i = 0; i < venudes.size(); i++) {
				Entrada entra = venudes.get(i);
				if (copiaEntrada.getAsiento() == entra.getAsiento() && 
						copiaEntrada.getFila() == entra.getFila() &&
						copiaEntrada.getHora().equals(entra.getHora()) &&
						copiaEntrada.getPelicula().equals(entra.getPelicula()) &&
						copiaEntrada.getSala().equals(entra.getSala())) asientoLibre = false;
						System.out.println(asientoLibre);
			}
		}
		
		return asientoLibre;
	}

	public void setDatos(List<Entrada> vendidas, boolean contraste) {
		
		if(vendidas.size() > 0) {
			for(int i = 0; i < vendidas.size(); i++){
				venudes.add(vendidas.get(i));
			}
		}
		
		contrast = contraste;
		
	}
	
	public double calcularPrecio(int porcentaje) {
		
		return precio - (precio*porcentaje/100);
		
	}

}




