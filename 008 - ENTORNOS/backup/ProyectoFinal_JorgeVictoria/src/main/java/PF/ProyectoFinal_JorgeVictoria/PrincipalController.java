package PF.ProyectoFinal_JorgeVictoria;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PrincipalController{ //creamos una entrada para crear la copia de la entrada
	
	Entrada miEntrada = new Entrada();

	//coleccion de entradas vendidas
	public List<Entrada> vendidas = new ArrayList<Entrada>();
	static ObservableList<Entrada> entradasVendidas = FXCollections.observableArrayList();
	
	boolean contraste;

	//variables miembro de la clase
	@FXML
	private Button btnComprarEntrada;

	@FXML
	private Button btnImprimirEntrada;
	
	@FXML
	private Button btnImprimirListado;
	
	@FXML
	private Button btnImprimirInforme;
	
	@FXML
	private MenuItem miComprarEntrada;
	
	@FXML
	private MenuItem miImprimirEntrada;
	
	@FXML
	private MenuItem miImprimirListado;
	
	@FXML
	private MenuItem miImprimirInforme;
	
	@FXML
	private ToggleButton btnColor;
	
	@FXML
	private WebView wvAyuda;

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
		
		if(!btnColor.isSelected()) {
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("Taquilla.fxml"));
	        Stage stage = new Stage();
	        stage = (Stage) btnComprarEntrada.getScene().getWindow();
	        Scene scene = new Scene(fxmlLoader.load());
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        stage.setScene(scene);
	        
	        TaquillaControler controlador = (TaquillaControler) fxmlLoader.getController();
	        controlador.setDatos(vendidas, contraste);
	        
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("Taquilla.fxml"));
	        Stage stage = new Stage();
	        stage = (Stage) btnComprarEntrada.getScene().getWindow();
	        Scene scene = new Scene(fxmlLoader.load());
	        scene.getStylesheets().add(getClass().getResource("contrast.css").toExternalForm());
	        stage.setScene(scene);
	        
	        TaquillaControler controlador = (TaquillaControler) fxmlLoader.getController();
	        controlador.setDatos(vendidas, contraste);
		}
        
       
        
        
        
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
	
		//nos vamos a la pantalla para imprimir la entrada
		try {
			
			if(!btnColor.isSelected()) {
				FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("Ticket.fxml"));
		        Stage stage = new Stage();
		        stage = (Stage) btnImprimirEntrada.getScene().getWindow();
		        Scene scene = new Scene(fxmlLoader.load());
		        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		        stage.setScene(scene);
		        
		        Ticket controlador = (Ticket) fxmlLoader.getController();
		        controlador.imprimirTicket(miEntrada, contraste, vendidas);
		        
			} else {
				FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("Ticket.fxml"));
		        Stage stage = new Stage();
		        stage = (Stage) btnImprimirEntrada.getScene().getWindow();
		        Scene scene = new Scene(fxmlLoader.load());
		        scene.getStylesheets().add(getClass().getResource("contrast.css").toExternalForm());
		        stage.setScene(scene);
		        
		        Ticket controlador = (Ticket) fxmlLoader.getController();
		        controlador.imprimirTicket(miEntrada, contraste, vendidas);
			} 
		}catch(Exception e) {
				e.printStackTrace();
			}
	
}
	
	@FXML
	void imprimirListado(ActionEvent event) {
		
		entradasVendidas.removeAll(entradasVendidas);
		
		for(int i = 0; i < vendidas.size(); i++){
			entradasVendidas.add(vendidas.get(i));
		}
				
				try {
					FXMLLoader fxmlLoader = new FXMLLoader();
					AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Listado.fxml"));
					Scene scene = new Scene(root,800,600);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage stage = new Stage();
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setScene(scene);
					//stage.setTitle("Agenda Jorge Victoria Andreu");
					//stage.getIcons().add(new Image("file:barra.png"));
					stage.show();
					
					
				} catch(Exception e) {
					e.printStackTrace();
				}
		}
			

	/**
	 * metodo que copia los elementos de la entrada y activa y desactiva los botones
	 * @param venudes 
	 * @param contrast 
	 * @param entrada, recibe la entrada generada
	 */
	public void setEntrada(Entrada entrada, List<Entrada> venudes, boolean contrast) {
		
		btnImprimirEntrada.disableProperty().setValue(false);
		
		btnComprarEntrada.disableProperty().setValue(true);
		
		btnImprimirInforme.disableProperty().setValue(false);
		
		btnImprimirListado.disableProperty().setValue(false);
		
		miEntrada = entrada;
		
		for(int i = 0; i < venudes.size(); i++){
			vendidas.add(venudes.get(i));
		}
		
		
		contraste = contrast;
		if(contraste) {
			btnColor.setText("claro");
			btnColor.setSelected(true);
		}
		else {
			btnColor.setText("oscuro");
			btnColor.setSelected(false);
		}
	}

	/**
	 * Metodo para cambiar la hoja de estilos que debe cargar y leer la aplicacion, en funcion del modo de escritorio que desee el usuario.
	 * @param event: Listener que recoge el click sobre el boton
	 */
	@FXML
	public void cambiarColor(ActionEvent event) {
		
		if(btnColor.isSelected()) {
			 Main.stage.getScene().getStylesheets().clear();
	         Main.stage.getScene().setUserAgentStylesheet(null);
			 Main.stage.getScene().getStylesheets().add(getClass().getResource("contrast.css").toExternalForm());
			 btnColor.setText("claro");
			 contraste = true;
			
		} else {
			Main.stage.getScene().getStylesheets().clear();
	         Main.stage.getScene().setUserAgentStylesheet(null);
			 Main.stage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 btnColor.setText("oscuro");
			 contraste = false;
		}
	}


	public void vuelta(boolean contrast, List<Entrada> venudes) {
		
		btnImprimirEntrada.disableProperty().setValue(true);
		
		btnComprarEntrada.disableProperty().setValue(false);
		
		btnImprimirInforme.disableProperty().setValue(false);
		
		btnImprimirListado.disableProperty().setValue(false);
		
		contraste = contrast;
		if(contraste) {
			btnColor.setText("claro");
			btnColor.setSelected(true);
		}
		else {
			btnColor.setText("oscuro");
			btnColor.setSelected(false);
		}
		
		for(int i = 0; i < venudes.size(); i++){
			vendidas.add(venudes.get(i));
		}
		
		
	}
	
	@FXML
	void imprimirInforme() {
		
	}
	
	@FXML 
	void mostrarAyuda() {
		
		WebEngine webEngine = wvAyuda.getEngine();
		//URL url = this.getClass().getResource("/html/principal.html");
		//webEngine.load(url.toString());
		webEngine.load("file:\\\\Mac\\Home\\Documents\\Proyectos\\008 - ENTORNOS\\ProyectoFinal_JorgeVictoria\\src\\html\\principal.html");
		wvAyuda.setVisible(true);
		
	}


}
