package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//Construimos la imagen
			FileInputStream input = new FileInputStream("src/tree.jpg");
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			
			//si queremos comprobar el directorio
			System.out.println(System.getProperty("user.dir"));
			
			//insertamos la imagen en un HBOX
			HBox hb = new HBox(imageView);
			
			//creamos el o new Scene(hb,400,400);
			Scene scene = new Scene(hb, 400, 400);
			
			//imprimimos en pantalla
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
