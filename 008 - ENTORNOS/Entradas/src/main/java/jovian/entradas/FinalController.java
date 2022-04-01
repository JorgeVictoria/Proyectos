package jovian.entradas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * clase para imprimir el ticket
 * @author Jorge Victoria Andreu
 * @since 20jan2022
 * @version 1.0
 */
public class FinalController {
	
	@FXML
	private Label lbTicket;

	/**
	 * metodo para imprimir el ticket
	 * @param miEntrada, objeto con los datos de la entrada
	 */
	public void imprimirTicket(Entrada miEntrada) {
		
		lbTicket.setText(miEntrada.toString());
		
	}
	
	

}
