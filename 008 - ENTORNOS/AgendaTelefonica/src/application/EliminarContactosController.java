package application;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EliminarContactosController extends Main {
	
	//variables globales
	int pos;
	
	//variables de los elementos del layout
	@FXML
	private TextField tfNombre;
	
	@FXML
	private TextField tfEmail;
	
	@FXML
	private TextField tfMovil;
	
	@FXML
	private TextField tfBuscar;
	
	@FXML
	private Label lbMensajes;
	
	@FXML
	private Button btnBuscar;
	
	@FXML
	private Button btnAnterior;
	
	@FXML
	private Button btnSiguiente;
	
	@FXML
	private Button btnEliminar;
	
	//metodo para la "configuracion inicial" al leer el FXML
	public void initialize() {
		
		//posicion inicial que vamos a cargar
				pos=0;
				
				//si la coleccion tiene datos, imprimimos la primera posicion, y sino los campos deben estar en blanco y los botones desactivados
				if(personas.size() > 0) {
					tfNombre.setText(personas.get(0).getNombre());
					tfEmail.setText(personas.get(0).getEmail());
					tfMovil.setText(personas.get(0).getMovil());
					//como partimos de la primera posicion de la coleccion, el boton para retroceder en la coleccion lo desactivamos
					btnAnterior.setDisable(true);
					
				} else {
					tfNombre.setText("");
					tfEmail.setText("");
					tfMovil.setText("");
					btnSiguiente.setDisable(true);
					btnAnterior.setDisable(true);
					btnBuscar.setDisable(true);
					btnEliminar.setDisable(true);
				}
		
		
		
	}
	
	/**
	 * metodo para buscar un usuario de la agenda
	 * comparamos el dato introducido con la coleccion de datos
	 * si encontramos un dato igual, mostramos los datos y podemos modificar
	 * en caso contrario, mostramos un warning
	 * @param event
	 */
	@FXML
	void buscarContacto(ActionEvent event) {
		
		boolean encontrado = false;
		
		for(int i = 0; i < personas.size(); i++) {
			if (tfBuscar.getText().toLowerCase().equals(personas.get(i).getNombre().toLowerCase()) ||   
				tfBuscar.getText().toLowerCase().equals(personas.get(i).getEmail().toLowerCase())  ||
				tfBuscar.getText().toLowerCase().equals(personas.get(i).getMovil().toLowerCase())) {
					encontrado = true;
					tfNombre.setText(personas.get(i).getNombre());
					tfEmail.setText(personas.get(i).getEmail());
					tfMovil.setText(personas.get(i).getMovil());
					lbMensajes.setText("contacto encontrado");
					
					
			}
		}
		
		if(!encontrado) lbMensajes.setText("contacto no encontrado");
		
	}
	
	/**
	 * metodo para gestionar el boton siguiente y correr la coleccion hacia posiciones superiores
	 * si llega a la ultima posicion del array, se desactiva
	 * @param event
	 */
	@FXML
	void anteriorContacto(ActionEvent event) {
		
		if(pos > 0) pos--;
		if(pos < personas.size()-1) btnSiguiente.setDisable(false);
		if(pos == 0) btnAnterior.setDisable(true);
		
		tfNombre.setText(personas.get(pos).getNombre());
		tfEmail.setText(personas.get(pos).getEmail());
		tfMovil.setText(personas.get(pos).getMovil());
		
		
	}
	
	/**
	 * metodo para gestionar el boton anterior y correr la coleccion hacia posiciones inferiores
	 * si llega a la primers posicion del array, se desactiva
	 * @param event
	 */
	@FXML
	void siguienteContacto(ActionEvent event) {
		
		if(pos < personas.size()-1) pos++;
		if(pos > 0) btnAnterior.setDisable(false);
		if(pos == personas.size()-1) btnSiguiente.setDisable(true);
		
		tfNombre.setText(personas.get(pos).getNombre());
		tfEmail.setText(personas.get(pos).getEmail());
		tfMovil.setText(personas.get(pos).getMovil());
		
		
	}
	
	//metodo para eliminar un contacto de la agenda
	@FXML
	void borrarContacto(ActionEvent event) {
		
		//en primer lugar mostramos un alert para confirmar el borrado
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmaci�n");
		alert.setContentText("�Estas seguro que desea borrar el contacto?");
		Optional<ButtonType> action = alert.showAndWait();
		
		//si el usuario pulsa borramos el contacto
		if (action.get() == ButtonType.OK) {
			lbMensajes.setText("Has pulsado en aceptar");
			personas.remove(pos);
			
			if(personas.size() > 0) {
				//si quedan registros en la agenda, mostramso el primero
				pos = 0;
				tfNombre.setText(personas.get(pos).getNombre());
				tfEmail.setText(personas.get(pos).getEmail());
				tfMovil.setText(personas.get(pos).getMovil());
				//y desactivamos el boton anterior
				btnAnterior.setDisable(true);
			
				//si no quedan registros, mostramos los campos en blanco y desactivamos los botones
			} else {
				tfNombre.setText("");
				tfEmail.setText("");
				tfMovil.setText("");
				btnSiguiente.setDisable(true);
				btnAnterior.setDisable(true);
				btnBuscar.setDisable(true);
				btnEliminar.setDisable(true);
			}
			
		}
	}
	

}
