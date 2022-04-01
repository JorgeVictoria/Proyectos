module U07PR01 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.xml;
	requires java.desktop;
	requires javafx.web;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
