package application;
	
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//creamos un datePicker para mostrar un calendario
			DatePicker fecha = new DatePicker();
			
			//creamos un boton
			Button bt1 = new Button("Ver Fecha");
			
			//creamos la etiqueta donde mostraremos la fecha
			Label fechaPantalla = new Label();
			
			//action even
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e)
					{
						
						//cogemos la fecha seleccionada
						LocalDate date = fecha.getValue();
						
						//mostramos la fecha en el formato indicado. Si no se ha seleccionado fecha, lo indicamos.
						if (date == null) fechaPantalla.setText("Ninguna fecha seleccionada");
						else fechaPantalla.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date));
						
					}
		};
		
			//cuando pulsamos el boton
			bt1.setOnAction(event);
			
			//creamos el vbox con todos los elementos
			VBox vb = new VBox(fecha,bt1,fechaPantalla);
			
			//creamos el objeto Scene
			Scene scene = new Scene(vb,400,400);
			
			//mostramos la pantalla
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
