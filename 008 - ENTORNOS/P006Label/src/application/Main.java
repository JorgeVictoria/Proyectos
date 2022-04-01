package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//definimos un par de etiquetas
			Label label1 = new Label("Etiqueta 1");
			Label label2 = new Label("Etiqueta 2");
			
			//Definimos un VBox y metemos las etiquetas anteriores
			VBox vbox = new VBox(label1, label2);
			
			//creamos el objeto Scene
			Scene scene = new Scene(vbox,200,100);
			
			//mostramos pantalla
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
