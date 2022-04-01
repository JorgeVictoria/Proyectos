package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * @name Main
 * @description clase inicial para el arranque de la aplicacion
 * @author jvand
 * @version 1.4
 * @since 22dic2021
 */
public class Main extends Application {
	
	//variables locales
	//arrayList de personas que usaremos para mostrar los contactos en una tabla
	static ObservableList<Persona> personas = FXCollections.observableArrayList();
	//objeto stage
	public static Stage stage;
	
	
	/**
	 * @name start: metodo que genera el scene inicial
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		
		
		try {
			//creamos un anchorPane que en su interior alberga los elementos que contiene el fichero fxml
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Agenda.fxml"));
			//creamos el scene donde cargaremos el anchorPane y su contenido
			Scene scene = new Scene(root,800,600);
			//declaramos cual va a ser el fichero css fuente
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//definimos varios elementos de la ventana, como el titulo o el icono
			primaryStage.setScene(scene);
			primaryStage.setTitle("Agenda Jorge Victoria Andreu");
			primaryStage.getIcons().add(new Image("file:barra.png"));
			//bloqueo de la redimension de la ventana. Hay pocos elementos y si se aumenta el tamaño, se crea una sensacion de vacio de elementos
			primaryStage.setResizable(false);
			//mostramos la ventana
			primaryStage.show();
			//tratamiento de errores en caso de que no se cargue o se cree bien el scene
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @name: main: metodo para arrancar la aplicacion
	 * @param args: podemos intrdoucir argumentos desde la linea de comandos.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
