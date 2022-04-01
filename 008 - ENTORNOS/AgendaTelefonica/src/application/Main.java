package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	static ObservableList<Persona> personas = FXCollections.observableArrayList();
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			//montamos el escenario
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Agenda.fxml"));
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Agenda Jorge Victoria Andreu");
			primaryStage.getIcons().add(new Image("file:barra.png"));
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
