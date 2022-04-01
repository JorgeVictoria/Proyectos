module EditorTextos {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
