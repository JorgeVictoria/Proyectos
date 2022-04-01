package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//definimos una etiqueta
			Label lbNombre = new Label("Introduce tu nombre");
			
			//Definimos un campo de texto
			TextField txtNombre = new TextField();
			
			//definimos un HBox
			HBox hbox = new HBox(lbNombre, txtNombre);
			
			//creamos el escenario
			Scene scene = new Scene(hbox,400,400);
			
			//mostramos el escenario
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
