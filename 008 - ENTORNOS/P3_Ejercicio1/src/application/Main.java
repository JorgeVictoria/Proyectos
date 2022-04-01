package application;
	
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//creamos un label con la cabecera del ejercicio
			Label info = new Label("Ejercicio de 100 caracteres");
			
			//creamos un textField donde iremos escribiendo
			TextArea tArea = new TextArea();
			
			//creamos un boton 
			Button btn = new Button("He terminado");
						
			//creamos un nuevo label donde mostraremos mensajes
			Label mensajes = new Label();
			
			//creamos el layout con sus elementos
			VBox root = new VBox(info, tArea, btn, mensajes);
			
			//le damos un poco de espacio a los elementos, por el Covid19
			root.setSpacing(10.0);
			//aplicamos margen a los elementos
			VBox.setMargin(info, new Insets(10,0,0,130));
			VBox.setMargin(btn, new Insets(10,0,0,160));
			VBox.setMargin(tArea, new Insets(0,10,0,10));
			VBox.setMargin(mensajes, new Insets(10,0,0,130));
			
			Scene scene = new Scene(root,400,400);
			
			scene.addEventFilter(KeyEvent.KEY_TYPED, e -> {
				
				if(tArea.getLength() > 0 && tArea.getLength() < 50) {
					mensajes.setText("Todavia te queda bastante.");
				}
				else if(tArea.getLength() >= 51 && tArea.getLength() < 100) {
					mensajes.setText("Te queda menos de la mitad.");
				}
				else if(tArea.getLength() >= 100) {
					mensajes.setText("Has acabado el trabajo.");
				} 
				else mensajes.setText("");
				
			});
			
			btn.setOnMouseClicked(event -> {
				
				//si no hemos tecleado 100 o mas caracteres
				//borramos el text area
				//borramos el label de mensajes
				//mostramos un warnig avisando al usuario
				if(tArea.getLength() < 100) {
					tArea.setText("");
					mensajes.setText("");
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setHeaderText("No trates de engañarme");
					alert.show();
				}
				else {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setTitle("Congrats");
					alert.setHeaderText("Trabajo acabado");
					alert.show();
		
					PauseTransition termination = new PauseTransition(Duration.seconds(5));
					termination.setOnFinished(evento -> Platform.exit());
					termination.play();
				}
			}
			);
			
			info.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
				
				mensajes.setText("No te entretengas!!!");
				
			});
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Pracica Tema 3");
			//anulamos que se pueda extender la pantalla
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
