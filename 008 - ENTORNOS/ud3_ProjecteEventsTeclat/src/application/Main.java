package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			TextField txtNom = new TextField("Polsa ací");
			
			EventHandler<KeyEvent> manejadorTeclat = (KeyEvent event) -> {
				switch (event.getEventType().getName()) {
		        	case "KEY_TYPED":
		        		System.out.println("tecla polsada");
		        		System.out.println("caracter: "+event.getCharacter().toString());
		        		break;
		        	case "KEY_RELEASED":
		        		System.out.println("tecla soltada");
		        		break;
		        }
		        
			};
			
			txtNom.addEventFilter(KeyEvent.KEY_TYPED, manejadorTeclat);
			txtNom.addEventFilter(KeyEvent.KEY_RELEASED , manejadorTeclat);
			
			BorderPane root = new BorderPane(txtNom);
			Scene scene = new Scene(root,400,400);
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
