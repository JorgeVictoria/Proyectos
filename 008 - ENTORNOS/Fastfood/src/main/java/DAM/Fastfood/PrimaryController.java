package DAM.Fastfood;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class PrimaryController {

    @FXML
    private Button btnArticulos;
    
    @FXML
    private Button btnCategoria;
    
    @FXML
    private Button btnPedidos;
    
    @FXML
    private void listarCategoria(ActionEvent event) throws ClassNotFoundException, SQLException, JRException {
    	
    	generarInformes("categoria.jrxml");
    	
    }
    
    @FXML
    private void listarArticulos(ActionEvent event) throws ClassNotFoundException, JRException, SQLException {
    	
    	generarInformes("Productos.jrxml");
    }
    
    @FXML
    private void listarPedidos(ActionEvent event) throws ClassNotFoundException, SQLException, JRException {
    	
    	generarInformes("Pedidos.jrxml");
    	
    }
    
    private static void generarInformes(String fichero) throws ClassNotFoundException, SQLException, JRException {
    	
    	Map<String, Object> parameters = new HashMap<String, Object>();
	   	 // Nombre de la BD
	   	 String databaseName = "fastfood";
	   	 // Usuario de la BD
	   	 String databaseUser = "root";
	   	 // Contraseña de la BD
	   	 String databasePassword = "";
	   	 // Generamos la dirección URL de conexión con la BD
	   	 String url = "jdbc:mysql://localhost/" + databaseName;
	   	 // Cargamos la clase adecuada al controlador JDBC para la conexión con MySQL
	   	 Class.forName("com.mysql.cj.jdbc.Driver");
	   	 // Realizamos la conexión con la BD MySQL
	   	 
	   	 Connection databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
	   	 
	   	 //Lanzamos el informe
	   	 
	   	 InputStream is = App.class.getResourceAsStream(fichero);
	   	 
	   	 JasperReport report = JasperCompileManager.compileReport(is);
	   	 JasperPrint print = JasperFillManager.fillReport(report, parameters, databaseLink);
	   	 //En esta ocasión lo visualizaremos en lugar de exportarlo
	   	 JasperViewer.viewReport(print);
    	
    }
    
    
}
