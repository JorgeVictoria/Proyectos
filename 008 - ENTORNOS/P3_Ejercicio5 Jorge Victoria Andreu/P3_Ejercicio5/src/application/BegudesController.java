package application;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class BegudesController extends Main {
	
	//variables miembro
	@FXML
	private ImageView CocaCola;
	
	
	@FXML
	private ImageView Agua;

	
	//metodos
	@FXML
	void DemanarCocaCola(MouseEvent event ) {
		
	//almacenamos nombre y precio
	beguda.setNombreProducto("CocaCola");
	beguda.setPrecioProducto(2);
		
		try {
			
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Principal.fxml"));
            Stage stage = (Stage) CocaCola.getScene().getWindow();
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
	void DemanarAgua(MouseEvent event ) {
		
		//almacenamos nombre y precio
		beguda.setNombreProducto("Agua");
		beguda.setPrecioProducto(1);
		
		try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Principal.fxml"));
            Stage stage = (Stage) Agua.getScene().getWindow();
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
