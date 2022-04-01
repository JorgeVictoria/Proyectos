package application;

import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AgendaController extends Main {
	
	private static Document documento;
	
	//variables de los elementos
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
	
	//metodo para inicializar variables y demas al leer el fxml
	public void initialize() {
		
		//añadimos objetos persona de ejemplo, para no tener vacia la agenda de inicio
		//personas.add(new Persona("Jorge Victoria Andreu", "jvandreu@hotmail.com", "123456789"));
		//personas.add(new Persona("Jordi Andreu Victoria", "kroneres@gmail.com", "987654321"));
	
	}
	
	//metodos de los elementos del layout
	//para los botonoes y los elementos del menu se llama al metodo que carga 
	//las escenas pasando un String con el nombre del fichero FXML a cargar
	
	@FXML
	void listarContactos(ActionEvent event) {
		
		abrirEscena("ListarContacto.fxml");
		
	}
	
	@FXML
	void añadirContactos(ActionEvent event) {
		
		abrirEscena("NuevoContacto.fxml");
		
	}
	
	@FXML
	void editarContactos(ActionEvent event) {
		
		abrirEscena("EditarContacto.fxml");
		
	}
	
	@FXML
	void borrarContactos(ActionEvent event) {
		
		abrirEscena("EliminarContacto.fxml");
		
	}
	
	@FXML
	void abrirInfo(ActionEvent event) {
		
		abrirEscena("Info.fxml");
		
	}
	
	@FXML
	void abrirAyuda(ActionEvent event) {
		
		abrirEscena("Ayuda.fxml");
	}
	
	
	//metodo para implementar la importacion de datos
	@FXML
	void importarContactos(ActionEvent event) {
		
		toObject();
		
		//TODO implementar funcionalidad
		boolean importado = true;
		
		if(importado) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmación");
			alert.setContentText("El fichero ha sido importado");
			alert.show();
		}
	}
	
	//metodo para implementar la exportacion de datos
	@FXML
	void exportarContactos(ActionEvent event) {
		
		toXML();
		
		//TODO implementar funcionalidad
		boolean exportado = true;
		
		if(exportado) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmación");
			alert.setContentText("El fichero ha sido exportado");
			alert.show();
		}
	}
	
	/**
	 * metodo para cargar las distintas escenas
	 * @param string: el fichero FXML a abrir
	 */
	void abrirEscena(String string) {
		
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
	
	 /**
     * metodo pasa los objetos a un fichero XML
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
     * metodo para cargar en el programa datos a partir de un fichero xml
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
            		
            	//añadimos el contacto al array
        		if (!repetido) personas.add(i,unaPersona);
                else repetido = false;
                   
                   
                }

            }

        } catch(Exception e){
            System.err.print("Error: " + e.getMessage());
        }

    }

    //metodo para buscar y filtrar un fichero
    private File buscarFile() {

        Scanner entrada = null;
        File f = null;
        //Se crea el JFileChooser. Se le indica que la ventana se abra en el directorio actual
        JFileChooser fileChooser = new JFileChooser(".");
        //Se crea el filtro. El primer parámetro es el mensaje que se muestra,
        //el segundo es la extensión de los ficheros que se van a mostrar
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
            System.out.println("No se ha seleccionado ningún fichero");
        }

        entrada.close();
        return f;
    }

    /**
     * fichero para seleccionar el fichero donde queremos guardar los datos
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

        //puede ocurrir que el fichero se guarde sin extension. se la añadimos
        if(!archivo.getName().contains(".")) archivo = new File(archivo.toString() + ".xml");

        //devolvemos el fichero
        return archivo;

    }
	
}
