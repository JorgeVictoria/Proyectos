module P011PR01EJ01 {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
