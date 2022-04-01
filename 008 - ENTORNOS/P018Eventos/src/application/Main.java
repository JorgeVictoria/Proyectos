package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			/*//metodo eventHandler tradicional
			EventHandler<MouseEvent> manejador = new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Mensaje");
					alert.setHeaderText("Se ha capturado el event");
					alert.show();
					System.out.println("Manejador...");
				}
			};
			
			//metodo eventHandler lambda
			EventHandler<MouseEvent> manejador2 = (MouseEvent event) -> {
				System.out.println("manejador2...");
			};
			
			Button btn = new Button("Pulsa aqui");
			btn.addEventHandler(MouseEvent.MOUSE_CLICKED, manejador);
			btn.addEventFilter(MouseEvent.MOUSE_CLICKED, manejador2);
			
			//otro modo para controlar eventos
			btn.setOnMouseClicked(event -> {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Mensaje");
				alert.setHeaderText("Se ha capturado el eventum");
				alert.show();
			}
			);
			
			
			StackPane root = new StackPane(btn);
			Scene scene = new Scene(root,400,400);*/
			
			TextField tf1 = new TextField();
			PasswordField pf1 = new PasswordField();
			TextArea ta1 = new TextArea();
			
			VBox root = new VBox(tf1,pf1,ta1);
			//separacion entre elementos
			root.setSpacing(5.0);
			//espacio entre elementos y el elemento padre
			root.setPadding(new Insets(25.0));
			
			Scene scene = new Scene (root, 400, 400);
			
			scene.addEventFilter(KeyEvent.KEY_TYPED, e -> {
				
				String type = e.getEventType().getName();
				String source = e.getSource().getClass().getSimpleName();
				String target = e.getTarget().getClass().getSimpleName();
				
				System.out.println("filter: " + type + " ," + source + " ," + target);
				
				if(Character.isDigit(e.getCharacter().charAt(0))) {
					System.out.println("caracter: " + e.getCharacter() + " no permitido.");
					e.consume();
				}
				
			});
			
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
