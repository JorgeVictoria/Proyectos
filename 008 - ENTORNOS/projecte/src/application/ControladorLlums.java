package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorLlums {
	
	//variables globales
	private static boolean interiorEncendida = false;
	private static boolean jardinEncendida = false;
	private static String cadena = "";
	private static String tempGlobal = "";
	

    @FXML
    private CheckBox cmbInterior;

    @FXML
    private CheckBox cmbJardi;

    @FXML
    private Label lblRespuesta;
    
    @FXML
    private Button btnAceptar;

    @FXML
    void btnAceptar(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("VistaPrincipal.fxml"));
        Stage stage = (Stage) btnAceptar.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
		
        ControladorVistaPrincipal control = (ControladorVistaPrincipal) fxmlLoader.getController();
		control.setLlumns(lblRespuesta.getText());
		control.setTemperatura(tempGlobal);
    }

    @FXML
    void cmbLlumInterior(ActionEvent event) {
    	interiorEncendida = cmbInterior.isSelected() ? true : false;
    	comprobarLuces();

    }

    @FXML
    void cmbLlumJardi(ActionEvent event) {
    	jardinEncendida= cmbJardi.isSelected() ? true : false;
    	comprobarLuces();

    }
    
    void comprobarLuces() {
    	cadena = "";
    	if(interiorEncendida) cadena = cadena + "INTERIOR";
    	if(jardinEncendida) cadena = cadena + " JARDÍ";
    	if(!interiorEncendida && !jardinEncendida) cadena = "CAP";
    	lblRespuesta.setText(cadena);
    }

	public void enviaDatos(String llum, String temp) {
		
		//mostramos las luces en su label
		lblRespuesta.setText(llum);
		//alamcenamos la temperatura en una variable global
		tempGlobal = temp;
		
	}

}

