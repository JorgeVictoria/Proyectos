package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**Clase para controlar los diferentes elementos del apartado nuevo contacto de la aplicacion.
* La clase extiende de Main para poder hacer uso de los objetos Persona que aparecen en la agenda.
* En primer lugar aparecen todos los elementos del layout con los que se puede interactuar
* Posteriormente aparecen los metodos que controlan las distintas funciones de este apartado, en este caso una unica funcion.
* @author Jorge Victoria Andreu
* @since 22dic2021
* @version 1.4
*/
public class NuevoContactoController extends Main {
	
	//variables de los elementos del layout
	@FXML
	private TextField tfNombre;
	
	@FXML
	private TextField tfEmail;
	
	@FXML
	private TextField tfMovil;
	
	@FXML
	private Button btnAñadir;
	
	@FXML
	private Label lbMensajes;
	
	//metodos de los elementos del layout
	
	/**
	 * Metodo para aï¿½adir un contacto en la agenda
	 * @param event: Listener para el boton
	 */
	@FXML
	public void añadirContacto(ActionEvent event) {
		
		Boolean datoValido = true;		//para ver si los datos son correctos
		Boolean repetido = false;		//para controlar que no exista el contacto en la agenda
		
		//comprobamos que el campo nombre no este vacio
		if(tfNombre.getText().length() == 0) {
			lbMensajes.setText("Debe insertar un nombre");
			datoValido = false;
		}
		
		//comprobamos que el campo email no este vacio
		else if(tfEmail.getText().length() == 0) {
			lbMensajes.setText("Debe insertar un correo electronico");
			datoValido = false;
		}
		
		//comprobamos que el campo tfno movil no este vacio
		else if(tfMovil.getText().length()== 0) {
			lbMensajes.setText("Debe insertar un telefono movil");
			datoValido = false;
		}
		
		//comprobamos que el campo tfno movil tenga 9 digitos
		else if(tfMovil.getText().length() != 9) {
			lbMensajes.setText("Faltan digitos en el telefono movil");
			datoValido = false;
		}
		
		//comprobamos que el campo tfno movil este compuesto por numeros. Este dato lo tengo como String
		try {
			 Integer.parseInt(tfMovil.getText());
		}catch (Exception ex) {
			lbMensajes.setText("El numero no es correcto");
			datoValido = false;
		}
		
		//TODO controlar que el campo email tenga el formato correcto
		
		//si los datos son correctos, corremos la coleccion para comprobar que no haya nada repetido.
		if(datoValido) {
			for (int i = 0; i < personas.size(); i++) {
				if(tfNombre.getText().toString().toLowerCase().equals(personas.get(i).getNombre().toLowerCase())) {
					repetido = true;
					lbMensajes.setText("el nombre de usuario ya existe");
				}
				else if(tfEmail.getText().toString().toLowerCase().equals(personas.get(i).getEmail().toLowerCase())) {
					repetido = true;
					lbMensajes.setText("la direccion de correo electronico ya existe");
				}
				else if(tfMovil.getText().toString().toLowerCase().equals(personas.get(i).getMovil().toLowerCase())) {
					repetido = true;
					lbMensajes.setText("el numero de telefono ya existe");
				}
			}
		}
		
		//si los datos son validos, y no hay nada repetido, aï¿½adimos un nuevo objeto Persona a la coleccion
		//posteriormente limpiamos las celdas para seguir con la introduccion de datos
		if(datoValido && !repetido) {
			personas.add(new Persona(tfNombre.getText(), tfEmail.getText(), tfMovil.getText()));
			lbMensajes.setText("Contacto registrado");
			tfNombre.setText("");
			tfEmail.setText("");
			tfMovil.setText("");
		}
		
		
		
	}

}
