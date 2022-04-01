package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//creamos el border pane
			BorderPane root = new BorderPane();
			
			//creamos los botones y los vamos colocando donde toca
			
			//TOP
			Button btnTop = new Button("Top");
			root.setTop(btnTop);
			
			//LEFT
			Button btnLeft = new Button("Left");
			root.setLeft(btnLeft);
			
			//CENTER
			Button btnCenter = new Button("Center");
			root.setCenter(btnCenter);
			
			//RIGHT
			Button btnRight = new Button("Right");
			root.setRight(btnRight);
			
			//BOTTOM
			Button btnBottom = new Button("Bottom");
			root.setBottom(btnBottom);
			
			//creamos el objeto Scene
			Scene scene = new Scene(root,400,400);
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//imprimimos el escenario
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
