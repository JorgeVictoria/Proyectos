package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	//creamos un objeto votos que usaremos en los distintos controladores
	public static Votos votos = new Votos();
			
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("pantalla1.fxml"));
			Scene scene = new Scene(root,500,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			//en principio habia quitado que la escena se pudiese redimensionar, pero debido´
			//a que hay que hacerlo para que salgan las plataformas VOD debajo de sus columnas,
			//lo he comentado hasta dar con el error
			//primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
