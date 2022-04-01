package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	//variables globales. Por alguna razon el manejador de eventos solo la reconoce si es asi.
	public static VBox vbImages = new VBox();

	@Override
	public void start(Stage primaryStage) {
		try {
			
			//creamos los checbox
			CheckBox cbCafe = new CheckBox("Cafe solo");
			CheckBox cbLlet = new CheckBox("Leche");
			
			//creamos un button
			Button btDibuixa = new Button("Dibuixa");
			
			//creamos las imagenes
			FileInputStream input = new FileInputStream("src/cafe.jpg");
			Image cafe = new Image(input);
			ImageView imageCafe = new ImageView(cafe);
			imageCafe.setFitHeight(250);
			imageCafe.setFitWidth(380);
			input = new FileInputStream ("src/leche.jpg");
			Image leche = new Image(input);
			ImageView imageLeche = new ImageView(leche);
			imageLeche.setFitHeight(250);
			imageLeche.setFitWidth(380);
			input = new FileInputStream("src/tallaet.jpg");
			Image tallaet = new Image(input);
			ImageView imageTallaet = new ImageView(tallaet);
			imageTallaet.setFitHeight(250);
			imageTallaet.setFitWidth(380);
			
			//creamos el vbox;
			VBox vb = new VBox(cbCafe,cbLlet,btDibuixa,vbImages);
			
			//action even
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e)
					{
						//vemos si estan marcados los checbox
						boolean cafeSelected = (cbCafe.isSelected());
						boolean lecheSelected = (cbLlet.isSelected());
						boolean tallaetSelected = (cbCafe.isSelected() && cbLlet.isSelected());
						
						//en funcion de que checkBoxes tengamos marcados, borramos pantalla y ponemos la imagen
						if(tallaetSelected) {
							vbImages.getChildren().clear();
							vbImages.getChildren().add(imageTallaet);
						}
						else if(cafeSelected) {
							vbImages.getChildren().clear();
							vbImages.getChildren().add(imageCafe);
						}
						else if(lecheSelected) {
							vbImages.getChildren().clear();
							vbImages.getChildren().add(imageLeche);
						} else vbImages.getChildren().clear();				//no hay ninguno marcado
							
					}
		};
		
			//cuando pulsamos el boton
			btDibuixa.setOnAction(event);
			
			//creamos el objeto Scene
			Scene scene = new Scene(vb,380,300);
			
			//imprimimos por pantalla
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
