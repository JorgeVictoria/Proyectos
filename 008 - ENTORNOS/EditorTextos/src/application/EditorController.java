package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditorController implements Initializable {
	
	//variables locales
	boolean salvado = false;									//para saber si el documento ha sido salvado al menos una vez
	boolean salvable = true;									//un fichero que ha sido modificado o creado desde cero
	int contador = 1;											//para contar los documentos que vamos creando
	String nombreDocumento = "Documento " + contador;     		//nombre del documento. Inicialmente le damos el valor 1
	File file;											    	//fichero donde trabajaremos
	FileChooser fileChooser;							    	//para crear un filechooser
	String textoInicial = "";									//copia del texto cuando abrimos un documento. Usado para ver cambios.
	Clipboard systemClipboard = Clipboard.getSystemClipboard(); //portapapeles de javafx
	ClipboardContent content = new ClipboardContent();			//para copiar texto al portapapeles
	
	//variables de los elementos del Scene
	@FXML
	private MenuItem miNuevo;
	
	@FXML
	private MenuItem Abrir;
	
	@FXML
	private MenuItem Guardar;
	
	@FXML
	private MenuItem GuardarComo;
	
	@FXML
	private MenuItem Salir;
	
	@FXML
	private MenuItem Copiar;
	
	@FXML
	private MenuItem Pegar;
	
	@FXML
	private MenuItem Cortar;
	
	@FXML
	private MenuItem AcercaDe;
	
	@FXML
	private TextArea taTexto;
	
	@FXML
	private ImageView ivSalvable;
	
	@FXML
	private Label lbNombreArchivo;
	
	@FXML
	private Button btnNuevo;
	
	@FXML
	private Button btnAbrir;
	
	@FXML
	private Button btnGuardar;
	
	@FXML
	private Button btnCortar;
	
	@FXML
	private Button btnCopiar;
	
	@FXML
	private Button btnPegar;
	
	@FXML
	private Button btnSalir;
	
	/**
	 * con este metodo controlamos los cambios en el text area comparando el texto original con los cambios
	 */
		public void initialize(URL url, ResourceBundle resourceBundle) {
	        taTexto.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
					
					//si cambiamos el texto, mostramos el diskette e indicamos que el documento es salvable
					ivSalvable.setVisible(true);
					salvable = true;
					
					//si no hay cambios, quitamos el diskette e indicamos que el documento no es salvable (porque no hay cambios)
					if(textoInicial.equals(arg2)) {
						ivSalvable.setVisible(false);
						salvable = false;
					}
					
				}
	        });
	    }
	
	
	//funciones de los distintos eventos de los elementos del scene
	
	/**
	 * metodo para crear nuevos documentos
	 * asociado al menu y boton correspondientes
	 * @param event
	 */
	@FXML
	void crearDocumento(ActionEvent event) {
		
		//mostramos una alerta para guardar el documento abierto, en caso necesario
		mostrarAlert(event);
		
		//limpiamos el area del texto
		taTexto.setText("");
			
		//incrementamos en uno el contador
		contador++;
			
		//modificamos el nombre del documento
		nombreDocumento = "Documento " + contador;
			
		//ponemos el nombre del documento en la parte inferior
		lbNombreArchivo.setText(nombreDocumento);
			
		//ponemos el indicador de salvado a false y el documento en salvable, porque es nuevo y ocultamos el diskette
		salvado = false;
		ivSalvable.setVisible(false);
		salvable = true;
		
	}
	
	/**
	 * metodo para abrir documentos
	 * asociado al menu y boton correspondientes
	 * @param event
	 */
	@FXML
	void abrirDocumento(ActionEvent event) {
		
		
		 fileChooser = new FileChooser();
	     fileChooser.setTitle("Abrir Documento");

	        // Agregar filtros para facilitar la busqueda
	        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("archivos TXT (.txt)", "*txt*"));

	        // Obtener el documento seleccionado
	        file = fileChooser.showOpenDialog(new Stage());
	        
	        //si el fichero no es nulo
	        if(file!=null){
	        	
	        	//creamos un FileReader para leer el texto del documento cargado
				FileReader fr = null;
				
				//creamos un bufferedReader para pasar los datos
				BufferedReader br = null;
				
				//creamos una cadena donde volcaremos temporalmente los datos. Ponemos en blanco el area de texto
				String texto = "";
				taTexto.setText("");
				
				//creamos los objetos
				//leemos del bauffer y volcamos el contenido en una cadena
				//si hay excepciones lo mostramos en el editor de texto
				//si no hay error, volcamos el texto en nuestro editor
				try {
					fr = new FileReader(file);
					br = new BufferedReader(fr);
					String st = br.readLine();
					while (st != null) {
						texto = texto + st + "\n";
						st = br.readLine();
					}
				} catch (Exception e) {
					taTexto.appendText(e.toString());
				} finally {
					try {
						fr.close();
					} catch (Exception e2) {
						taTexto.appendText(e2.toString());
					}
				}
				taTexto.appendText(texto);
				textoInicial = texto;
				
				//ponemos el nombre del fichero en el label inferior
				lbNombreArchivo.setText(file.getName().toString());
				
				//ocultamos el diskette y ponemos el documento como salvado y no salvable(nuevo, no hay cambios)
				salvado = true;
				ivSalvable.setVisible(false);
				salvable = false;
			}

	        
	}
	
	/**
	 * metodo para guardar nuevos documentos
	 * asociado al menu y boton correspondientes
	 * @param event
	 */
	@FXML
	void guardarDocumento(ActionEvent event) {
		
		//si el fichero no ha sido guardado todavia, llamamos al metodo guardarDocumentoComo
		if(!salvado) guardarDocumentoComo(event);
		//si ha sido guardado con anterioridad, vamos directos al metodo para guardar ficheros
		else guardarFicheros();
	}
	
	/**
	 * metodo para guardar documentos
	 * asociado al menu y correspondiente
	 * @param event
	 */
	@FXML
	void guardarDocumentoComo(ActionEvent event) {
		
		fileChooser = new FileChooser();
	    fileChooser.setTitle("Guardar Documento Como: ");

	    // Agregar filtros para facilitar la busqueda
	    fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("archivos TXT", "*txt*"));

	    // Abrir el cuadro de dialogo
	    file = fileChooser.showSaveDialog(new Stage());
	    
	    //puede ocurrir que el fichero se guarde sin extension. se la añadimos
	    if(!file.getName().contains(".")) file = new File(file.toString() + ".txt");
	        
	    //llamamos al metodo guardarFicheros
	    guardarFicheros();
	    
	}
	
	/**
	 * metodo para salir del programa
	 * asociado al menu y boton correspondientes
	 * @param event
	 */
	@FXML
	void salirPrograma(ActionEvent event) {
		
		//comprobamos si el usuario quiere  guardar el documento
		mostrarAlert(event);
		
		//salimos del programa
		System.exit(0);
		
	}
	
	/**
	 * metodo para copiar texto
	 * asociado al menu y boton correspondientes
	 * @param event
	 */
	@FXML
	void copiarTexto(ActionEvent event) {
		
		//guardamos en una cadena  el texto seleccionado
		String copia = taTexto.getSelectedText();
		
		//si la copia tiene mas de 0 caracteres, pasamos la copia al Clipboard.
		if(copia.length() > 0) {
			content.putString(copia);
			systemClipboard.setContent(content);
		}
		
		
	}
	
	/**
	 * metodo para pegar texto
	 * asociado al menu y boton correspondientes
	 * @param event
	 */
	@FXML
	void pegarTexto(ActionEvent event) {
		
		  //cogemos el rango de posiciones del texto seleccionado
		  IndexRange range = taTexto.getSelection();
		  
		  //cogemos el texto completo
		  String origText = taTexto.getText();
		  
		  //debemos crear 2 subcadenas, con el texto anterior a la seleccion y el texto posterior a la seleccion
		  String firstPart = taTexto.getText().substring( 0, range.getStart() );
		  String lastPart = taTexto.getText().substring( range.getEnd(), origText.length() );
		  
		  //una vez creada la cadena, unimos la primera parte con el texto del clipbard y la segunda parte
		  taTexto.setText( firstPart + systemClipboard.getString() + lastPart );
		  
		  //dejamos el cursor en la posicion  donde estabamos
		  taTexto.positionCaret( range.getStart() );
		
		
	}
	
	/**
	 * metodo para cortar texto
	 * asociado al menu y boton correspondientes
	 * @param event
	 */
	
	@FXML
	void cortarTexto(ActionEvent event) {
		
		  
		  //almacenamos el texto seleccionado
		  String text = taTexto.getSelectedText();
		  
		  //copiamos la seleccion en el clipboard
		  content.putString(text);
		  systemClipboard.setContent(content);
		  
		  //cogemos el rango de posiciones de la seleccion
		  IndexRange range = taTexto.getSelection();
		  
		  //cogemos el texto original
		  String origText = taTexto.getText();
		  
		  //cogemos la parte anterior a la seleccion y la posterior
		  String firstPart = taTexto.getText().substring( 0, range.getStart() );
		  String lastPart = taTexto.getText().substring( range.getEnd(), origText.length() );
		  
		  //rellenamos en el text area con las 2 subcadenas creadas y ya no aparece el texto seleccionado
		  taTexto.setText( firstPart + lastPart );
		  
		  //ponemos el cursos donde estaba
		  taTexto.positionCaret( range.getStart() );
		
	}
	
	/**
	 * metodo para el menititem acerca de. Solo muestra informacion de la aplicacion
	 * @param event
	 */
	@FXML
	void mostrarInfo(ActionEvent event) {
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Notepad");
		alert.setHeaderText("Notepad Version: v1 nov2021");
		alert.setContentText("Autor: Jorge Victoria Andreu");
		alert.show();
		
	}
	
	/**
	 * Con este metodo controlamos que cuando seleccionemos texto, se activen los botones y los menuItem de cortar y copiar
	 * @param event
	 */
	@FXML
	void cogerTexto (MouseEvent event) {
		
		if(taTexto.getSelectedText().length() > 0) {
			btnCopiar.setDisable(false);
			btnCortar.setDisable(false);
			Copiar.setDisable(false);
			Cortar.setDisable(false);
		} else {
			btnCopiar.setDisable(true);
			btnCortar.setDisable(true);
			Copiar.setDisable(true);
			Cortar.setDisable(true);
		}
	}
	
	/**
	 * metodo para guardar ficheros
	 */
	private void guardarFicheros() {
		
		//si el fichero no es nulo
	    if(file!=null){
	        	
	    //creamos un FileWriter para leer el texto del documento cargado
	    FileWriter fw = null;
				
		//creamos un bufferedReader para pasar los datos
		BufferedWriter bw = null;
				
		try {
			
			//creamos los objetos		
			fw = new FileWriter(file, false);	//false porque vamos a sobreescribir el documento
		    bw = new BufferedWriter(fw);
		    
		    //cogemos el texto del area de texto. Ademas creamos una copia
			String st = taTexto.getText();
			textoInicial = st;
			
			//volcamos la informacion
			bw.write(st, 0, st.length());
			
			//si hay errores lo mostramos en el notepad
			} catch (Exception e) {
				taTexto.appendText(e.toString());
			} finally {
				try {
					bw.close();
				} catch (Exception e2) {
					taTexto.appendText(e2.toString());
				}
			}
				
				
		    //ponemos el indicador de salvado, ocultamos el diskette y cambiamos el nombre del fichero
			salvado = true;
			ivSalvable.setVisible(false);
			lbNombreArchivo.setText(file.getName().toString());
		
	    }
	}
	
	/**
	 * metodo para mostrar un alert por si el usuario quiere guardar el documento
	 * @param event
	 */
	private void mostrarAlert(ActionEvent event) {
		
		//debemos guardar el fichero si no ha sido guardado o ha sido modificado
				if(!salvado || salvable) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setTitle("Confirmación");
					alert.setContentText("¿Desea guardar el documento?");
					Optional<ButtonType> action = alert.showAndWait();
					// Si hemos pulsado en aceptar
					if (action.get() == ButtonType.OK) {
						//si el documento ha sido salvado, directamente lo guardamos
					    if(salvado)guardarFicheros();
					    //si el documento no ha sido guardado, nos vamos a guardar como
					    else guardarDocumentoComo(event);
					} 
				}
				
	}
	
	
	
	
	
}
