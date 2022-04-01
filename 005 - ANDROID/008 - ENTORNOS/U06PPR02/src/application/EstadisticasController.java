package application;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class EstadisticasController {
	
	@FXML
	private BarChart<String, Integer> graficaBarras;
	
	/**
	 * metodo para pintar la barra de graficos
	 * por algun motivo desconocido hay que redimensionar un poco para ver el texto de las columnas
	 * @param votos: objeto con los votos
	 */
	public void pintarGrafica(Votos votos) {
		
		String[] plataformas = {"netflix",  "hbo", "disney+", "otros"};
		
		XYChart.Series <String, Integer> serieDatos = new XYChart.Series<>();
		
		serieDatos.setName("Plataformas VOD");
		
		for(int i = 0; i < votos.getTotalVotos().length; i++) {
			
			serieDatos.getData().add(new Data<String, Integer>(plataformas[i], votos.getTotalVotos()[i]));
		}
		
		graficaBarras.getData().add(serieDatos);
	}
	
	

}
