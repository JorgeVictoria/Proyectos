package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Button btn = new Button("Polsa ací");
			
			EventHandler<MouseEvent> manejadorRatoli = (MouseEvent event) -> {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setTitle("Missatge");
		        switch (event.getEventType().getName()) {
		        	case "MOUSE_ENTERED":
		        		alert.setHeaderText(event.getEventType().getName() + " El cursor ha pasado sobre el ratón");
		        		break;
		        	case "MOUSE_RELEASED":
		        		alert.setHeaderText(event.getEventType().getName() + " Has soltado el botón del ratón");
		        		break;
		        	default:
		        		alert.setHeaderText(event.getEventType().getName() );
		        		break;
		        }
		        
		        alert.show();
			};
			
			btn.addEventFilter(MouseEvent.MOUSE_ENTERED, manejadorRatoli);
			btn.addEventFilter(MouseEvent.MOUSE_RELEASED, manejadorRatoli);
			
			BorderPane root = new BorderPane(btn);
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
