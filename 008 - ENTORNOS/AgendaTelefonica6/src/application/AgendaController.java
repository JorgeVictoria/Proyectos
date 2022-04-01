package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Clase para controlar los diferentes elementos del menu principal de la aplicacion.
 * La clase extiende de Main para poder hacer uso de los objetos Persona que aparecen en la agenda.
 * En primer lugar aparecen todos los elementos del layout con los que se puede interactuar
 * Posteriormente aparecen los metodos que controlan las distintas funciones de la aplicacion
 * @author Jorge Victoria Andreu
 * @since 22dic2021
 * @version 1.4
 */

public class AgendaController extends Main {
	
	//variables globales
	//variable de tipo Document para la escritura y lectura de ficheros XML
	private static Document documento;
	//como manejamos dos hojas de estilo, con esta variable controlamos si usuamos el modo normal o el modo oscuro
	private static boolean contraste = false;
	
	//variables de los distintos elementos del layout
	@FXML
	private MenuItem miNuevoContacto;
	
	@FXML
	private MenuItem miListaContacto;
	
	@FXML 
	private MenuItem miEditarContacto;
	
	@FXML 
	private MenuItem miEliminarContacto;
	
	@FXML 
	private MenuItem miImportarContactos;
	
	@FXML 
	private MenuItem miExportarContactos;
	
	@FXML 
	private MenuItem miAcercaDe;
	
	@FXML 
	private MenuItem miAyuda;
	
	@FXML
	private Button btnNuevoContacto;
	
	@FXML
	private Button btnListaContacto;
	
	@FXML
	private Button btnEditarContacto;
	
	@FXML
	private Button btnBorrarContacto;
	
	@FXML
	private Button btnImportarContactos;
	
	@FXML
	private Button btnExportarContactos;
	
	@FXML
	private Button btnContraste;
	
	@FXML
	private WebView wvAyuda;
	
	/**
	 * Este metodo lo utilizamos para prueba. Por ejemplo, para aï¿½adir objetos Persona al cargar la aplicacion.
	 */
	public void initialize() {
		
		//aï¿½adimos objetos persona de ejemplo, para no tener vacia la agenda de inicio
		//personas.add(new Persona("Jorge Victoria Andreu", "jvandreu@hotmail.com", "123456789"));
		//personas.add(new Persona("Jordi Andreu Victoria", "kroneres@gmail.com", "987654321"));
	
	}
	
	//metodos de los elementos del layout
	
	/**
	 * Metodo para construir un tableView donde mostraremos el listado de los usuarios de nuestra agenda.
	 * @param event: Listener que recoge el click sobre el boton
	 */
	@FXML
	public void listarContactos(ActionEvent event) {
		
		//TableView donde mostramos el contenido de la tabla
		TableView<Persona> tableView = new TableView();
		
		//creamos las tres columnas necesarias
		//primero le damos el nombre de la columna
		//y para las celdas correspondientes indicamos el nombre del elemento de la clase que va a albergar
		TableColumn column1 = new TableColumn<>("Nombre");
		column1.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
		
		TableColumn column2 = new TableColumn<>("Email");
		column2.setCellValueFactory(new PropertyValueFactory<Persona, String>("email"));
		
		TableColumn column3 = new TableColumn<>("Movil");
		column3.setCellValueFactory(new PropertyValueFactory<Persona, String>("movil"));
		
		//dividimos la anchura de las columnas en la tabla. Como el total es 1, dividimos en 1/3 cada columna
		column1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.33));
		column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.33));
		column3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.33));
		
		//volcamos en la tabla la info del array personas
		tableView.setItems(personas);
		
		//aï¿½adimos las columnas a la tabla
		tableView.getColumns().addAll(column1, column2, column3);
		
		
		
		//para mostrar la tabla en pantalla
		//creamos un vbox donde ira almacenado el contenido del layout
		//definimos las carateristicas de la ventana: titulo, not resizable....
		//lanzamos el scene
		VBox vbox = new VBox(tableView);
		
		Scene scene = new Scene(vbox,800,600);
		//en funcion del modo que tengamos seleccionado, cargamos el css correcto
		if(contraste)scene.getStylesheets().add(getClass().getResource("contrast.css").toExternalForm());
		else scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage stage = new Stage();
		//aplicaamos el Modal para que la escena se cargue en una ventana independiente y no borre la ventana principal
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.setTitle("Agenda Jorge Victoria Andreu");
		stage.getIcons().add(new Image("file:barra.png"));
		stage.setResizable(false);
		stage.show();
		
	}
	
	/**
	 * Metodo para aï¿½adir contactos a la agenda.
	 * @param event: Listener que recoge el click sobre el boton
	 */
	@FXML
	public void  añadirContactos(ActionEvent event) {
		
		//llamada a la funcion para abrir el scene. Se le pasa el fichero fxml a cargar  
		abrirEscena("NuevoContacto.fxml");
		
	}
	
	/**
	 * Metodo para editar los contactos de la agenda
	 * @param event: Listener que recoge el click sobre el boton
	 *
	 */
	@FXML
	public void editarContactos(ActionEvent event) {
		
		//llamada a la funcion para abrir el scene. Se le pasa el fichero fxml a cargar  
		abrirEscena("EditarContacto.fxml");
		
	}
	
	/**
	 * metodo para borrar contactos de la agenda
	 * @param event: Listener que recoge el click sobre el boton
	 *
	 */
	@FXML
	public void borrarContactos(ActionEvent event) {
		
		//llamada a la funcion para abrir el scene. Se le pasa el fichero fxml a cargar 
		abrirEscena("EliminarContacto.fxml");
		
	}
	
	/**
	 * Metodo para mostrar informacion sobre la aplicacion
	 * @param event: Listener que recoge el click sobre el boton del menï¿½
	 *
	 */
	@FXML
	public void abrirInfo(ActionEvent event) {
		
		//llamada a la funcion para abrir el scene. Se le pasa el fichero fxml a cargar 
		abrirEscena("Info.fxml");
		
	}
	
	/**
	 * Metodo para la ayuda de la aplicacion
	 * @param event: Listener que recoge el click sobre el boton del menï¿½
	 *
	 */
	@FXML
	public void abrirAyuda(ActionEvent event) {
		
		//Cargamos la ayuda al WebView y la hacemos visible
		WebEngine webEngine = wvAyuda.getEngine();
		//URL url = this.getClass().getResource("file:///C:/Proyectos/008%20-%20ENTORNOS/AgendaTelefonica5/src/doc/U07PR01/module-summary.html");
		//webEngine.load(url.toString());
		//no me funciona el objeto url, asi que lo he puesto de esta forma
		webEngine.load("file:C:\\docs\\doc\\U07PR01\\module-summary.html");
		wvAyuda.setVisible(true);

	}
	
	/**
	 * Metodo para cambiar la hoja de estilos que debe cargar y leer la aplicacion, en funcion del modo de escritorio que desee el usuario.
	 * @param event: Listener que recoge el click sobre el boton
	 */
	@FXML
	public void cambiarColor(ActionEvent event) {
		
		if(!contraste) {
			 Main.stage.getScene().getStylesheets().clear();
	         Main.stage.getScene().setUserAgentStylesheet(null);
			 Main.stage.getScene().getStylesheets().add(getClass().getResource("contrast.css").toExternalForm());
			 btnContraste.setText("claro");
			 contraste = true;
		} else {
			Main.stage.getScene().getStylesheets().clear();
	         Main.stage.getScene().setUserAgentStylesheet(null);
			 Main.stage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 btnContraste.setText("oscuro");
			 contraste = false;
		}
	}
	
	/**
	 * Metodo para importar contactos desde un fichero XML
	 * @param event: Listener que recoge el click sobre el boton
	 */
	@FXML
	public void importarContactos(ActionEvent event) {
		
		//llamada a la funcion para pasar datos de un fichero xml a objetos Persona
		toObject();
		
		//TODO implementar funcionalidad
		boolean importado = true;
		
		//al final la importacion, se muestra mensaje en pantalla
		if(importado) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmaciï¿½n");
			alert.setContentText("El fichero ha sido importado");
			alert.show();
		}
	}
	
	/**
	 * Metodo para exportar los contactos a un fichero XML
	 * @param event: Listener que recoge el click sobre el boton
	 */
	@FXML
	public void exportarContactos(ActionEvent event) {
		
		//llamada a la funcion para pasar datos de objetos Persona a fichero XML
		toXML();
		
		//TODO implementar funcionalidad
		boolean exportado = true;
		
		//al final la exportacion, se muestra mensaje en pantalla
		if(exportado) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmaciï¿½n");
			alert.setContentText("El fichero ha sido exportado");
			alert.show();
		}
	}
	
	/**
	 * Metodo para cargar las distintas escenas segun el fichero FXML que le pasemos
	 * @param string: el fichero FXML a abrir
	 */
	public void abrirEscena(String string) {
		
		 //en funcion del valor del contraste, cargamos un css u otro
		 if (!contraste) {
			
			try {
				AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(string));
				Scene scene = new Scene(root,800,600);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.setTitle("Agenda Jorge Victoria Andreu");
				stage.getIcons().add(new Image("file:barra.png"));
				stage.setResizable(false);
				stage.show();
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		 }
		 else {
			 try {
					AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(string));
					Scene scene = new Scene(root,800,600);
					scene.getStylesheets().add(getClass().getResource("contrast.css").toExternalForm());
					Stage stage = new Stage();
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setScene(scene);
					stage.setTitle("Agenda Jorge Victoria Andreu");
					stage.getIcons().add(new Image("file:barra.png"));
					stage.setResizable(false);
					stage.show();
					
					
				} catch(Exception e) {
					e.printStackTrace();
				}
		 }
	 
	}
	
	/**
	 * Metodo para pasar los elementos de un array de objetos Persona a un fichero XML
	 */
    public void toXML() {

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //root element
            Element rootElement = doc.createElement("ANIMALS");
            doc.appendChild(rootElement);

            //corremos el array list con los objetos y vamos creando los tags
            if(personas.size() > 0) {

                for(int i = 0; i < personas.size();i++){
                    Element persona = doc.createElement("persona");
                    rootElement.appendChild(persona);
                    Element nombre = doc.createElement("Nombre");
                    nombre.appendChild((doc.createTextNode(personas.get(i).getNombre())));
                    persona.appendChild(nombre);
                    Element eMail = doc.createElement("eMail");
                    eMail.appendChild((doc.createTextNode(personas.get(i).getEmail())));
                    persona.appendChild(eMail);
                    Element telefono = doc.createElement("telefono");
                    telefono.appendChild((doc.createTextNode(personas.get(i).getMovil())));
                    persona.appendChild(telefono);
                  
                }

                // escribimos el contenido en un fichero XML
                //llamada a la funcion para coger el fichero donde queremos guardar
                File file = guardarComo();
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(String.valueOf(file)));
                transformer.transform(source, result);
            }

        } catch(Exception e){
            System.err.print("Error: " + e.getMessage());
        }
    }

    /**
     * Metodo para leer datos de un fichero XML y pasarlos a un array de objetos Persona
     */
    public void toObject(){
    	
    	boolean repetido = false;

        try{

            File file = buscarFile();
            //Crear una factoria que permita usar un parser:
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            //Crear un builder que permite crear documentos DOM usando un parser
            documento = builder.parse(new File(String.valueOf(file)));

            //los nodos de texto adyacentes los fusiona
            documento.getDocumentElement().normalize();

            //crear una lista con todos los nodos Character
            NodeList contactos = documento.getElementsByTagName("persona");

            //recorremos los diferentes
            for (int i = 0; i < contactos.getLength(); i++) {
                //Creamos un objeto nodo con cada elemento Character
                Node contacto = contactos.item(i);
                if (contacto.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) contacto;
                    //Creamos un nuevo objeto persona
                    Persona unaPersona = new Persona();
                    unaPersona.setNombre(((Element) contacto).getElementsByTagName("Nombre").item(0).getTextContent());
                    unaPersona.setEmail(((Element) contacto).getElementsByTagName("eMail").item(0).getTextContent());
                    unaPersona.setMovil(((Element) contacto).getElementsByTagName("telefono").item(0).getTextContent());
                    //corremos la coleccion para comprobar que no haya nada repetido.
            		
            			for (int j = 0; j < personas.size(); j++) {
            				if(unaPersona.getNombre().toLowerCase().equals(personas.get(j).getNombre().toLowerCase())) {
            					repetido = true;
            				}
            				else if(unaPersona.getEmail().toLowerCase().equals(personas.get(j).getEmail().toLowerCase())) {
            					repetido = true;
            				}
            				else if(unaPersona.getMovil().toLowerCase().equals(personas.get(j).getMovil().toLowerCase())) {
            					repetido = true;
            				}
            				
            			}
            		
            	//aï¿½adimos el contacto al array
        		if (!repetido) personas.add(i,unaPersona);
                else repetido = false;
                   
                   
                }

            }

        } catch(Exception e){
            System.err.print("Error: " + e.getMessage());
        }

    }

    /**
     * Meetodo para buscar un fichero XML, desde el cual cargaremos los datos
     * @return un fichero de tipo File
     */
    private File buscarFile() {

        Scanner entrada = null;
        File f = null;
        //Se crea el JFileChooser. Se le indica que la ventana se abra en el directorio actual
        JFileChooser fileChooser = new JFileChooser(".");
        //Se crea el filtro. El primer parï¿½metro es el mensaje que se muestra,
        //el segundo es la extensiï¿½n de los ficheros que se van a mostrar
        FileFilter filtro = new FileNameExtensionFilter("archivos XML (.xml)" , "xml");
        //Se le asigna al JFileChooser el filtro
        fileChooser.setFileFilter(filtro);
        //se muestra la ventana
        int valor = fileChooser.showOpenDialog(fileChooser);
        if (valor == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                f = new File(ruta);
                entrada = new Scanner(f);
                while (entrada.hasNext()) {
                    System.out.println(entrada.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                if (entrada != null) {
                    entrada.close();
                }
            }
        } else {
            System.out.println("No se ha seleccionado ningï¿½n fichero");
        }

        entrada.close();
        return f;
    }

    /**
     * Metodo para seleccionar el fichero XML donde queremos guardar los datos
     * @return archivo, que es un File
     */
    private File guardarComo(){

        //creamos el filtro para indicar el tipo de fichero que guardamos
        FileFilter filtro = new FileNameExtensionFilter("archivos XML (.xml)" , "xml");
        //creamos el jfileChoser
        JFileChooser guardar = new JFileChooser();
        //al jfilechooser le pasamos el filtro que queremos aplicar
        guardar.setFileFilter(filtro);
        //mostramos la ventana
        guardar.showSaveDialog(null);
        guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //creamos un File donde almacenamos el fichero creado/seleccionado
        File archivo = guardar.getSelectedFile();

        //puede ocurrir que el fichero se guarde sin extension. se la aï¿½adimos
        if(!archivo.getName().contains(".")) archivo = new File(archivo.toString() + ".xml");

        //devolvemos el fichero
        return archivo;

    }
	
}
