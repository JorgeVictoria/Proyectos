module DI.ud9_ExempleJasperReportMavenFXML {
    requires javafx.controls;
    requires javafx.fxml;
	requires jasperreports;
	requires java.sql;

    opens DI.ud9_ExempleJasperReportMavenFXML to javafx.fxml;
    exports DI.ud9_ExempleJasperReportMavenFXML;
}
