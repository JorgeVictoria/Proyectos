package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//creamos un boton y un label
			Button btn1 = new Button("Pulsa aqui");
			Label lab1 = new Label("Todavia no has pulsado");
			
			//accion que se realizara al pulsar el boton
			btn1.setOnAction(value ->{ lab1.setText("Boton pulsado");});
			
			//creamos el HBox
			HBox hb = new HBox(btn1,lab1);
			
			//creamos el objeto Scene
			Scene scene = new Scene(hb,300,100);
			
			//presentamos la pantalla
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
