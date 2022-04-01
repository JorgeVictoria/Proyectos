package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerPantalla3 {
	
	//variables locales
	private static int[] votos = new int[]{0, 0, 0, 0}; //almacen de votos de cada platafaorma
	private int totalVotos = 0;							//total de votos
	
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
	
	//metodo para rellenar la pantalla con los datos de los votos, porcentajes...
	void setValor(String cadena) {
		
		//cada vez que un usuario vote, incrementamos en uno el contador
		totalVotos++;
		
		//en funcion de la plataforma elegida, aumentamos sus votos en el array
		if(cadena.toUpperCase().equals("NETFLIX")) votos[0]++;
		else if (cadena.toUpperCase().equals("HBO")) votos[1]++;
		else if (cadena.toUpperCase().equals("DISNEY+")) votos[2]++;
		else votos[3]++;
		
		//mostramos los votos por pantalla
		NetVotos.setText(String.format("%d", votos[0]));
		HBOVotos.setText(String.format("%d", votos[1]));
		DisneyVotos.setText(String.format("%d", votos[2]));
		OtrosVotos.setText(String.format("%d", votos[3]));
		
		//mostramos los porcentajes por pantalla
		NetPorcent.setText(String.format("%d %s", (votos[0]*100)/totalVotos, "%"));
		HBOPorcent.setText(String.format("%d %s", (votos[1]*100)/totalVotos, "%"));
		DisneyPorcent.setText(String.format("%d %s", (votos[2]*100)/totalVotos, "%"));
		OtrosPorcent.setText(String.format("%d %s", (votos[3]*100)/totalVotos, "%"));
		
		
	}
	
	//salida
	@FXML
	void salida(ActionEvent event) {
		
		System.exit(0);
		
	}

}
