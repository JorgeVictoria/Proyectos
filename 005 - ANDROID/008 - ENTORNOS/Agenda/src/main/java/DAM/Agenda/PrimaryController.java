package DAM.Agenda;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PrimaryController extends App {

    @FXML
    private Button btnInsertar;
    
    @FXML
    private Button btnInforme;
    
    public void initialize() {
		
		//Contactos de ejemplo
		contactos.add(new Contacto("Domingo Fiesta Segura", "domise@hotmail.com", "123456789"));
		contactos.add(new Contacto("Aitor Tilla", "itilla@gmail.com", "987654321"));
		contactos.add(new Contacto("Ines Esario", "isario@gmail.com", "876543219"));
		contactos.add(new Contacto("Elvis Nieto", "wlnieto@gmail.com", "765432109"));
		contactos.add(new Contacto("Susana Oria", "susana@gmail.com", "565654542"));
		
	
	}
    
    @FXML
    private void insertarContacto(ActionEvent action) {
    	
    	try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("secondary.fxml"));
			Scene scene = new Scene(root,800,600);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    private void generarInforme(ActionEvent action) throws JRException {
    	
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	 
    	 //List<Empleado> empleados = Arrays.asList(empleat1, empleat2);
    	 InputStream is = App.class.getResourceAsStream("Agenda.jrxml");
    	 
    	 JasperReport report = JasperCompileManager.compileReport(is);
    	 JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(contactos);
    	 JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
    	 JasperExportManager.exportReportToPdfFile(print, "contactos.pdf");
    	
    }
}
