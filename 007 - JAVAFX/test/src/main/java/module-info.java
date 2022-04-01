module test.test {
    requires javafx.controls;
    requires javafx.fxml;

    opens test.test to javafx.fxml;
    exports test.test;
}
