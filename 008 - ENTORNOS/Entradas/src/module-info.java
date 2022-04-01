module Entradas {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens entradas to javafx.graphics, javafx.fxml;
}
