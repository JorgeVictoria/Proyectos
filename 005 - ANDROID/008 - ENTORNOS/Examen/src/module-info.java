module Examen {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	opens reloj to javafx.graphics, javafx.fxml;
}
