package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SampleController {
	
	//variables locales
    private boolean inhabilitado = true;
	
    //variables de los elementos FXML
    
	@FXML
	private Button btnAmigos;
	
	@FXML
	private Button btnProgramando;
	
	//metodos de los elementos fxml
	
	@FXML
	void mostrarMensaje(ActionEvent event) {
		
		//salida del programa
		System.exit(0);
		
	}
	
	@FXML
	void moverBoton(MouseEvent event) {
		
		//mientras el boton esté inhabilitado para realizar su funcion, se ira moviendo hasta desaparecer cada vez que intentemos pulsarlo
		if(inhabilitado) btnAmigos.setLayoutY(btnAmigos.getLayoutY()+20);
		
	}
	
	@FXML
	void habilitarBotonAmigos(ActionEvent event) {
		
		//habilitamos el boton amigos
		inhabilitado = false;
		
		//movemos el boton amigos a su posicion inicial en el eje y
		btnAmigos.setLayoutY(190);
		
		//mostramos un alert para indicar que ya puede usar el boton amigos
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Info");
	    alert.setContentText("Venga, ahora puedes pulsar el otro botón");
	    alert.showAndWait();
		
	}
}
