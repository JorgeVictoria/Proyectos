package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class SampleController {
	
	//variables locales
	GaussianBlur blur = new GaussianBlur();
	
	Translate desplazamiento = new Translate();
	
	Timer timer = new Timer();
	
	//variables de los elementos del layout
	@FXML
	private ImageView camion;
	
	@FXML
	private Button marcha;
	
	@FXML
	private Button destinatario;
	
	//metodos de los elementos del layout
	
	@FXML
	void activarBoton(ActionEvent event) {
		
		//hacemos la imagen borrosa
		blur.setRadius(5);
		
		camion.setEffect(blur);
		
		//se deshabilita el boton destinatario y se activa el de marcha
		destinatario.setDisable(false);
		
		marcha.setDisable(true);
		
	}
	
	@FXML
	void moverImagen(ActionEvent event) throws InterruptedException {
		
		//se deshabilita el boton destinatario
		destinatario.setDisable(true);
		
		//creamos un timeline para simular el movimiento del camion 
		KeyValue keyValue = new KeyValue(camion.translateXProperty(), -500);
		
		KeyValue keyValue2 = new KeyValue(camion.translateYProperty(), 0);
		
		KeyFrame keyframe = new KeyFrame(Duration.seconds(1), keyValue, keyValue2);
		
		Timeline timeline = new Timeline(keyframe);
		
		timeline.play();
		
	}

	
	
}
