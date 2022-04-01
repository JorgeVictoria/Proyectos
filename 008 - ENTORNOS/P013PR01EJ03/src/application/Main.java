package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//creamos los label
			Label lbNom = new Label("Nom:");
			Label lbCognom = new Label("Cognoms:");
			Label lbSaludo = new Label();
			
			//Creamos los text Label
			TextField tlNom = new TextField();
			TextField tlCognom = new TextField();
			
			//definimos un boton
			Button btPolsa = new Button("Polsa ací:");
			
			//accion que se realizara al pulsar el boton
			btPolsa.setOnAction(value ->{ lbSaludo.setText("Bienvenido " + tlNom.getText() + " " + tlCognom.getText());});
			 
			//creamos un VBox
			VBox vb = new VBox(lbNom,tlNom,lbCognom,tlCognom,btPolsa,lbSaludo);
			
			//creamos el objeto Scene
			Scene scene = new Scene(vb,400,400);
			
			//mostramos por pantalla
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
