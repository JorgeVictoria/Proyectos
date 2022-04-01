module DAM.Agenda {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires jasperreports;
	requires java.sql;


    opens DAM.Agenda to javafx.fxml;
    exports DAM.Agenda;
}
