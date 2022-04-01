package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			EventHandler<MouseEvent> manejador = new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent event) {
			    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
			        alert.setTitle("Missatge");
			        alert.setHeaderText("S'ha capturat l'event");
			        alert.show();
			        System.out.println("manejador ...");
			    }
			};
			
			EventHandler<MouseEvent> manejador2 = (MouseEvent event) -> {
			    System.out.println("manejador 2...");
			};

			
			Button btn = new Button("Polsa ací");
			btn.addEventHandler(MouseEvent.MOUSE_CLICKED, manejador);
			btn.addEventFilter(MouseEvent.MOUSE_CLICKED, manejador2);
			
			StackPane root = new StackPane(btn);
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
