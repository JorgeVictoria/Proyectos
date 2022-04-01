package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class SampleController {
	
	//variables de los elementos FXML
	
	@FXML
	private Button btnRebotar;
	
	@FXML
	private Circle cCirculo;
	
	//metodos de los elementos FXML
	
	@FXML
	void dibujarBola(MouseEvent event) {
		
		//hacemos invisible el boton
		btnRebotar.setVisible(false);

		//hacemos la pelota visible, ya ha sido dibujada en el fichero FXML
		cCirculo.setVisible(true);
		
		//creamos un timeline para simular el movimiento de la pelota
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
			
			//ponemos la velocidad de la pelota en cada movimiento, que seran el numero de posiciones que se desplaza el centro del circulo
			double dx = 3; 
        	double dy = 3; 
        	
        	@Override
            public void handle(ActionEvent t) { 
        		
        		//movemos la pelota
        		cCirculo.setLayoutX(cCirculo.getLayoutX() + dx);
        		cCirculo.setLayoutY(cCirculo.getLayoutY() + dy);
        		
        		//como la pelota tiene un radio de 20 pixels, cada vez que este a 20 pixels del borde cambiara la direccion
        		//para cualquiera de los 2 ejes
        		if(cCirculo.getLayoutX() <= 20 || cCirculo.getLayoutX() >= 480) dx = -dx;
        		
        		if(cCirculo.getLayoutY() <= 20 || cCirculo.getLayoutY() >= 380) dy = -dy;

        	}
        	
			
		}));
		
		//ponemos tiempo ilimitado al timeline y lo iniciamos
		timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        

		
	
	}
	
}
