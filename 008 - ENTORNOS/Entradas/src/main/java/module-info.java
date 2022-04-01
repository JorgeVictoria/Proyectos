module jovian.entradas {
	requires javafx.controls;
    requires javafx.fxml;

    opens jovian.entradas to javafx.fxml;
    exports jovian.entradas;
}