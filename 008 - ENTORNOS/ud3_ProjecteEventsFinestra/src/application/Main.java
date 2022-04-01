package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			EventHandler<WindowEvent> manejadorFinestra = (WindowEvent event) -> {
				switch (event.getEventType().getName()) {
		        	case "WINDOW_SHOWING":
		        		System.out.println("mostrant finestra");
		        		break;
		        	case "WINDOW_SHOWN":
		        		System.out.println("finestra mostrada");
		        		break;
		        	case "WINDOW_CLOSE_REQUEST":
	        			System.out.println("Vols tancar la ventana?");
	        			event.consume();
	        		break;
		        }
		        
			};
			
			primaryStage.addEventFilter(WindowEvent.WINDOW_SHOWING, manejadorFinestra);
			primaryStage.addEventFilter(WindowEvent.WINDOW_SHOWN, manejadorFinestra);
			primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, manejadorFinestra);
			
			BorderPane root = new BorderPane();
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
