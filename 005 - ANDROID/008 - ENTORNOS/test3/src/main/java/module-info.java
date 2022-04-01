module jovian.test3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens jovian.test3 to javafx.fxml;
    exports jovian.test3;
}
