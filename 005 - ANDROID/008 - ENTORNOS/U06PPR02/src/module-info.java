module U06PPR02 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
