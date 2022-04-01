package application;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListaController extends PrincipalController {
	
	//variables de los elemetos del layout
		//para el listView debemos indicar que tipo de dato u objeto va a mostrar
		//en este caso vamos a coger objetos Persona y sus parametros los pasamos a String
		@FXML
		private ListView<String> lvListado;
		
		/**
		 * Metodo para llamar a la carga de la lista de contactos al cargar el layout
		 * @param vendidas 
		 * @param vendidas 
		 */
		public void initialize() {
			
			//llamada a la funcion listar contactos
			listarContactos();
			
		}
		
		
		/**
		 * Metodo para msotrar en pantalla la lista de contactos de la agenda
		 * @param vendidas 
		 * @param vendidas 
		 */
		@FXML
		public void listarContactos() {
			
			
			
				//barra con los elementos de la lista
				lvListado.getItems().add(String.format("%-35s %-35s %-35s %-35s %-35s %-35s", "Sala", "Pelicula", "Hola", "Fila" , "Asiento", "Precio"));
			
			//si tenemos contactos en la agenda los mostramos
			if(entradasVendidas.size() >0 ) {
				for(int i = 0; i < entradasVendidas.size(); i++) {
					
					lvListado.getItems().add(entradasVendidas.get(i).listarEntradas());
				}
			}
			
			
			
		}
		
		

	}


