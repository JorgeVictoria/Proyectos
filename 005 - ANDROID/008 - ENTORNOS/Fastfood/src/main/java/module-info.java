module DAM.Fastfood {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires jasperreports;
	requires java.sql;

    opens DAM.Fastfood to javafx.fxml;
    exports DAM.Fastfood;
}
