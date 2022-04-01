module ud10_ExempleJUnitUnitaries {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.junit.jupiter.api;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
