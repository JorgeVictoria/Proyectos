module ProyectoFinal_JorgeVictoria {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires org.junit.jupiter.api;
	requires junit;
	requires javafx.web;
	
	opens application to javafx.graphics, javafx.fxml;
}
