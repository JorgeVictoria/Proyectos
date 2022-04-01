package jovian.entradas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * aplicacion que simula la compra de una entrada para el cine
 * @author Jorge Victoria Andreu
 * @version 1.0
 * @since 20Jan2022
 */
public class App extends Application {

	//variables miembro de la clase
    private static Scene scene;

    /**
     * metodo para iniciar la carga de la aplicacion
     * @param stage
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        scene.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * metodo para indicar establecer el nombre del fichero fxml inicial
     * LLama a la funcion loadFXML para construir el string completo
     * @param fxml: el nombre del fichero fxml, sin extension
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * metodo para leer el fichero fxml inicial
     * @param fxml nombre del fichero fxml sin extension
     * @return la lectura del fichero fxml
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * metodo de inicio de la aplicacion
     * @param args argumentos externos
     */
    public static void main(String[] args) {
        launch();
    }

}