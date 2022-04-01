package application;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class BurguersController extends Main {
	
	@FXML
	private Label Pollastre;
	
	@FXML
	private Label Ternera;
	
	@FXML
	private Label Bacon;
	
	@FXML
	void DemanarPollastre(MouseEvent event) {
		
		//almacenamos nombre y precio
		burguer.setNombreProducto("Burguer de pollo");
		burguer.setPrecioProducto(5);
		
		try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Principal.fxml"));
            Stage stage = (Stage) Pollastre.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
          //construimos un objeto controlador al que le pasamos el nombre de usuario
            PrincipalController controlador = (PrincipalController) fxmlLoader.getController();
            controlador.setEtiqueta();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void DemanarTernera(MouseEvent event) {
		
		//almacenamos nombre y precio
		burguer.setNombreProducto("Burguer de Ternera");
		burguer.setPrecioProducto(6);
		
		try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Principal.fxml"));
            Stage stage = (Stage) Ternera.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
          //construimos un objeto controlador al que le pasamos el nombre de usuario
            PrincipalController controlador = (PrincipalController) fxmlLoader.getController();
            controlador.setEtiqueta();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void DemanarBacon(MouseEvent event) {
		
		//almacenamos nombre y precio
		burguer.setNombreProducto("Burguer de Ternera y Bacon");
		burguer.setPrecioProducto(7);
		
		try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Principal.fxml"));
            Stage stage = (Stage) Bacon.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            
          //construimos un objeto controlador al que le pasamos el nombre de usuario
            PrincipalController controlador = (PrincipalController) fxmlLoader.getController();
            controlador.setEtiqueta();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
