package DI.ud9_ExempleJasperReportMavenFXML;

import java.io.InputStream;
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

public class SampleController {

    @FXML
    private TextField cognoms1;

    @FXML
    private TextField cognoms2;

    @FXML
    private Button btnInforme;

    @FXML
    private TextField nom1;

    @FXML
    private TextField nom2;
   
    @FXML
    private TextField vendes1;

    @FXML
    private TextField vendes2;

    @FXML
    void generarInforme(ActionEvent event) throws JRException {
    	Map<String, Object> parameters = new HashMap<String, Object>();

    	Empleat empleat1 = new Empleat (
        		cognoms1.getText(),
        		nom1.getText(),
        		Integer.parseInt(vendes1.getText())
		);
        
        
        Empleat empleat2 = new Empleat (
        		cognoms2.getText(),
        		nom2.getText(),
        		Integer.parseInt(vendes2.getText())
		);
        
        
        List<Empleat> empleats = Arrays.asList(empleat1, empleat2);


        InputStream is = Main.class.getResourceAsStream("informe.jrxml");
        

        JasperReport report = JasperCompileManager.compileReport(is);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleats);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(print, "vendes.pdf");
        

    }

}
