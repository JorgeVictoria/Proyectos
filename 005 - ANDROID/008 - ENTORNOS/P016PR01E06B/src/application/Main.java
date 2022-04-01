package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//creamos el combobox
			ComboBox<String> cb = new ComboBox<String>();
			
			//añadimos elementos al combobox
			cb.getItems().addAll("Windows","Linux","MacOS");
			
			//creamos el boton
			Button bt1 = new Button("Pulsar");
			
			//creamos un label donde mostraremos el Sistema Operativo seleccionado
			Label sistemaOperativo = new Label();
			
			//action even
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				String sisOper = cb.getValue();
				if (sisOper == null) sistemaOperativo.setText("No ha seleccionado ningun sistema operativo");
				else sistemaOperativo.setText("Ha seleccionado " + sisOper);
						
			}
		};
		
			//cuando pulsamos el boton
			bt1.setOnAction(event);
			
			//creamos el vbox
			VBox vb = new VBox(cb,bt1,sistemaOperativo);
	
			Scene scene = new Scene(vb,400,400);
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
