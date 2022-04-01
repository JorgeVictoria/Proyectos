package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			TextField tf1 = new TextField();
			PasswordField tf2 = new PasswordField();
			TextArea tf3 = new TextArea();

			VBox root = new VBox(tf1, tf2, tf3);
			root.setSpacing(5.0);
			root.setPadding(new Insets(5.0));
			
			Scene scene = new Scene(root,400,400);
			
			scene.addEventFilter(KeyEvent.KEY_TYPED, e -> {

			    String type = e.getEventType().getName();
			    String source = e.getSource().getClass().getSimpleName();
			    String target = e.getTarget().getClass().getSimpleName();

			    System.out.println("filter: " + type + ", " + source + ", " + target);
			    
			    if (Character.isDigit(e.getCharacter().charAt(0))) {
			        System.out.println("caracter: " + e.getCharacter() + ", no permés.");
			        e.consume();
			    }
			});
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Events a l'escena");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
