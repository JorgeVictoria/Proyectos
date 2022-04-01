package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	//objeto stage
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Principal.fxml"));
			Scene scene = new Scene(root,640,480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
