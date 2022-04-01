package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController {
	@FXML
    private TextField txtA;

    @FXML
    private TextField txtB;

    @FXML
    private Label lblRes;
    
    @FXML
    void sumar(ActionEvent event) {
    	calculadora calc = new calculadora(Integer.parseInt(txtA.getText()), Integer.parseInt(txtB.getText()));
    	lblRes.setText(String.valueOf(calc.suma()));  
    }
    
    @FXML
    void restar(ActionEvent event) {
    	calculadora calc = new calculadora(Integer.parseInt(txtA.getText()), Integer.parseInt(txtB.getText()));
    	lblRes.setText(String.valueOf(calc.resta())); 
    }
    
    @FXML
    void mult(ActionEvent event) {
    	calculadora calc = new calculadora(Integer.parseInt(txtA.getText()), Integer.parseInt(txtB.getText()));
    	lblRes.setText(String.valueOf(calc.multiplica())); 
    }
    
    @FXML
    void dividir(ActionEvent event) {
    	calculadora calc = new calculadora(Integer.parseInt(txtA.getText()), Integer.parseInt(txtB.getText()));
    	lblRes.setText(String.valueOf(calc.divide())); 
    }
	    
	
}
