module DAM.pruebaInformes {
    requires javafx.controls;
    requires javafx.fxml;
    requires jasperreports;
    requires java.sql;

    opens DAM.pruebaInformes to javafx.graphics, javafx.fxml;
    exports DAM.pruebaInformes;
}
