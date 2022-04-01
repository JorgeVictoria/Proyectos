package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//creamos los botones
			Button btn1 = new Button("Boton 1");
			Button btn2 = new Button("Boton 2");
			
			//creamos un VBox con los botones dentro
			VBox vbox = new VBox(btn1, btn2);
			
			//Creamos una Scene con el VBox, el ancho y el alto
			Scene scene = new Scene(vbox, 300, 100);
			
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
