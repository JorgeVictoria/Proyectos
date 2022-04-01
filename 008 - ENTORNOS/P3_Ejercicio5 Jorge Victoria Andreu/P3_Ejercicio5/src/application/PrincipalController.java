package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class PrincipalController extends Main {
	
	
	
	//variables locales
	@FXML
	private Button btnBeguda;
	
	@FXML
	private Button btnBurguer;
	
	@FXML
	private Label LineaUno;
	
	@FXML
	private Label LineaDos;
	
	@FXML
	private Label LineaUnoPrecio;
	
	@FXML
	private Label LineaDosPrecio;
	
	@FXML
	private Button btnComanda;
	
	void setEtiqueta() {
		
		
		if(beguda.getNombreProducto() != null) {
			LineaUno.setText(beguda.getNombreProducto());
			LineaUnoPrecio.setText(beguda.getPrecioProducto() + "€");
		}
		
		if(burguer.getNombreProducto() != null) {
			LineaDos.setText(burguer.getNombreProducto());
			LineaDosPrecio.setText(burguer.getPrecioProducto() + "€");
		}
	}
	
	@FXML
	void TriarBeguda(ActionEvent event) {
		
		try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Begudes.fxml"));
            Stage stage = (Stage) btnBeguda.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	void TriarBurguer(ActionEvent event) {
		
		try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Burguers.fxml"));
            Stage stage = (Stage) btnBeguda.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@FXML
	void FerComanda(ActionEvent event) {
		if(beguda.getNombreProducto() == null && burguer.getNombreProducto() == null) {
			btnComanda.disableProperty().set(true);
		} else {
			btnComanda.disableProperty().set(false);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Ticket");
			alert.setHeaderText("Importe Total: " + (beguda.getPrecioProducto()+burguer.getPrecioProducto()) + "€" );
			alert.show();
		}
	}
	
	
	
}
