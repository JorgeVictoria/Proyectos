package DI.ud9_ExempleJasperReportMavenFXML;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class SampleController {


    @FXML
    void generarInforme(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
    	Map<String, Object> parameters = new HashMap<String, Object>();
 
    	// Nom de la BD
    	String databaseName = "fastfood";
    	// Usuari de la BD
    	String databaseUser = "root";
    	// Contrasenya de la BD
    	String databasePassword = "";
    	// Genermrem la direcció URL de conexió a la BD
    	String url = "jdbc:mysql://localhost/" + databaseName;
 
    	// Carregem la classe adequada al controlador JDBC per a la connexió amb MySQL
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	// Realitzem la connexió amb la BD MySQL
    	Connection databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
    	
    	//Llançem l'informe
    	
        InputStream is = Main.class.getResourceAsStream("Cherry.jrxml");
        
        JasperReport report = JasperCompileManager.compileReport(is);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, databaseLink);

        //En aquesta ocasió el visualitzarem en lloc de exportar-lo
        JasperViewer.viewReport(print);
        

    }
    
    @FXML
    void generarInformeSQL(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
    	Map<String, Object> parameters = new HashMap<String, Object>();
 
    	// Nom de la BD
    	String databaseName = "fastfood";
    	// Usuari de la BD
    	String databaseUser = "root";
    	// Contrasenya de la BD
    	String databasePassword = "";
    	// Genermrem la direcció URL de conexió a la BD
    	String url = "jdbc:mysql://localhost/" + databaseName;
 
    	// Carregem la classe adequada al controlador JDBC per a la connexió amb MySQL
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	// Realitzem la connexió amb la BD MySQL
    	Connection databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
    	
    	//Llançem l'informe, ara ho farem amb l'objecte JDesign
    	InputStream is = Main.class.getResourceAsStream("Cherry.jrxml");
    	JasperDesign jd=JRXmlLoader.load(is);
        JRDesignQuery newQuery=new JRDesignQuery();

        newQuery.setText("Select * from empleat order by NomEmpleat");
        jd.setQuery(newQuery);
    	
        JasperReport report = JasperCompileManager.compileReport(jd);      

        JasperPrint print = JasperFillManager.fillReport(report, parameters, databaseLink);

        //En aquesta ocasió el visualitzarem en lloc de exportar-lo
        JasperViewer.viewReport(print);
        

    }

}
