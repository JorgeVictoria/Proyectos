package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			//definimos los botones
			Button btn1 = new Button("Boton 1");
			Button btn2 = new Button("Boton 2");
			
			//creamos un hbox con los botones dentro
			HBox hbox = new HBox(btn1, btn2);
			
			//creamos escena
			Scene scene = new Scene(hbox,400,400);
			
			primaryStage.setTitle("Contenedor HBox");
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
