package application;


import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;


public class ListarContactoController extends Main {
	
	//variables de los elemetos del layout
	//para el listView debemos indicar que tipo de dato u objeto va a mostrar
	//en este caso vamos a coger objetos Persona y sus parametros los pasamos a String
	@FXML
	private ListView<String> lvListado;
	
	//como esta escena no tiene botones ni nada para interactuar
	//usamos este metodo para poder cargar el listado de contactos
	public void initialize() {
		
		listarContactos();
		
	}
	
	
	/**
	 * metodo para cargar el listado con los datos
	 */
	@FXML
	void listarContactos() {
		
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
