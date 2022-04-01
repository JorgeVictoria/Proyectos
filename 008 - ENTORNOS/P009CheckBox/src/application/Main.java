package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//creamos el CheckBox
			CheckBox cb = new CheckBox("Acepta los terminos."); 
			
			//Creamos un HBox
			HBox hb = new HBox(cb);
			
			//para comprobar que el checkBox esta seleccionado
			boolean isSelected = cb.isSelected();
			
			//Creamos el objeto Scene
			Scene scene = new Scene(hb,400,100);
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
