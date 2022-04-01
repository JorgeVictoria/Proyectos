/**
 * En la segunda pantalla de mi prototipo, debemos seleccionar nuestra plataforma favorita
 * Vamos a escenificar esta pantalla con el uso de Checbox, Label...
 */


package application;


import java.awt.Insets;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.CheckBox;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//creamos los 4 radioButton con las plataformas mas populares
			
			//creamos el grupo de radioButtons
			final ToggleGroup group = new ToggleGroup();
			
			//creamos los radioButton
			RadioButton netflix = new RadioButton("Netflix");
			RadioButton hbo = new RadioButton("HBO");
			RadioButton amazon = new RadioButton("Amazon");
			RadioButton disney= new RadioButton("Disney+");
			
			//añadimos los radio Button al grupo
			netflix.setToggleGroup(group);
			netflix.setSelected(true);
			hbo.setToggleGroup(group);
			amazon.setToggleGroup(group);
			disney.setToggleGroup(group);
			
			//metemos los radiobutton en un vbox
			VBox plataformas = new VBox(netflix,hbo,amazon,disney);
			plataformas.setStyle("-fx-padding: 0 0 0 120;");
			
			//creamos un par de Labels, para la cabecera y la pregunta.
			Label titulo = new Label("Plataformas VOD");
			titulo.setStyle("-fx-label-padding: 20 0 10 150;");
			
			Label pregunta = new Label("¿Cual es tu plataforma VOD favorita?");
			pregunta.setStyle("-fx-label-padding: 10 0 20 100;");
			
			//creamos un boton para enviar la seleccion.
			Button opcion = new Button("Votar");
			VBox boton = new VBox(opcion);
			boton.setStyle("-fx-padding: 30 0 0 120;");
			
			//creamos un label para que el boton de voto haga algo
			Label respuesta = new Label();
			respuesta.setStyle("-fx-label-padding: 20 0 10 120;");
			
			//action even
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e)
					{
						if(group.getSelectedToggle().equals(netflix)) respuesta.setText("Has seleccionado Netflix");
						else if(group.getSelectedToggle().equals(hbo)) respuesta.setText("Has seleccionado hbo");
						else if(group.getSelectedToggle().equals(amazon)) respuesta.setText("Has seleccionado Amazon Prime Video");
						else respuesta.setText("Has seleccionado Disney+");
						
					}
		};
		
			//cuando pulsamos el boton
			opcion.setOnAction(event);
			
			//creamos un VBox
			VBox caja = new VBox(titulo,pregunta,plataformas,boton, respuesta);			
			
			Scene scene = new Scene(caja,400,400);
		
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
