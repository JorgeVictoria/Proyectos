package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	//creamos 2 objetos producto. Uno para la bebida y otro para la burguer
	public static Producto beguda = new Producto();
	public static Producto burguer = new Producto();
	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Principal.fxml"));
			Scene scene = new Scene(root,300,450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
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
