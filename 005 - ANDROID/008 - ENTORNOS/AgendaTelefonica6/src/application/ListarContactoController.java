package application;


import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;

/**Clase para mostrar un listado de los diferentes contactos de la agenda telefonica
* La clase extiende de Main para poder hacer uso de los objetos Persona que aparecen en la agenda.
* En primer lugar aparecen todos los elementos del layout que se van a mostrar, en este caso un listView.
* Posteriormente aparecen los metodos que controlan las distintas funciones de este apartado, en este caso mostrar el listado
* @author Jorge Victoria Andreu
* @since 22dic2021
* @version 1.4
*/
public class ListarContactoController extends Main {
	
	//variables de los elemetos del layout
	//para el listView debemos indicar que tipo de dato u objeto va a mostrar
	//en este caso vamos a coger objetos Persona y sus parametros los pasamos a String
	@FXML
	private ListView<String> lvListado;
	
	/**
	 * Metodo para llamar a la carga de la lista de contactos al cargar el layout
	 */
	public void initialize() {
		
		//llamada a la funcion listar contactos
		listarContactos();
		
	}
	
	
	/**
	 * Metodo para msotrar en pantalla la lista de contactos de la agenda
	 */
	@FXML
	public void listarContactos() {
		
			//barra con los elementos de la lista
			lvListado.getItems().add(String.format("%-62s %-64s %-20s", "nombre", "email", "tfno movil"));
		
		//si tenemos contactos en la agenda los mostramos
		if(personas.size() >0 ) {
			for(int i = 0; i < personas.size(); i++) {
				
				lvListado.getItems().add(personas.get(i).toString());
			}
		}
		
		
		
	}
	
	

}
