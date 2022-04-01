package veterinaria;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class veterinaria {

	public static void main(String[] args) {
		
		Connection connection = null;
		
		try {
			//establecemos la conexion. 
			connection = DriverManager.getConnection("jdbc:sqlite:veterinaria.db");
			
			//Si la conexion es exitosa, lo indicamos y empezamos la lectura y escritura de los metadatos
			if ( connection != null) {
				System.out.println("Conexion exitosa");
			
			//obtenemos los metadatos
				DatabaseMetaData dbmd = connection.getMetaData();
				
				//cogemos los metadatos de la tabla
				ResultSet resul = dbmd.getTables(null, "veterinaria.db",null , null);
				
				//mientras haya datos, vamos leyendo. 
				while(resul.next()) {
					//cogemos metadatos de la tabla
					String catalogo = resul.getString("TABLE_CAT");
					String esquema = resul.getString("TABLE_SCHEM");
					String tabla = resul.getString("TABLE_NAME");
					String tipo = resul.getString("TABLE_TYPE");
					
					//mostramos los metadatos de la tabla
					System.out.println("    TABLA    ");
					System.out.println("Catalogo: " + catalogo);
					System.out.println("Esquema: " + esquema);
					System.out.println("Tabla:" + tabla);
					System.out.println("Tipo: " + tipo);
					
					
					ResultSet columnas = dbmd.getColumns(null, "veterinaria.db", tabla, null);
					while (columnas.next()) {
						//cogemos metadatos de las columnas
						String nomCol = columnas.getString("COLUMN_NAME");
						String tipoCol = columnas.getString("TYPE_NAME");
						String tamCol = columnas.getString("COLUMN_SIZE");
						String nula = columnas.getString("IS_NULLABLE");
						
						//mostramos metadatos de las columnas
						System.out.println("    COLUMNA    ");
						System.out.println("Nombre Columna: " + nomCol);
						System.out.println("Tipo Columna: " + tipoCol);
						System.out.println("Tamaño Columna: " + tamCol);
						System.out.println("Admite nulls:" + nula);
					}
					System.out.println("------------------------");
				}
			
			}
			
			//control de errores en la conexion
		} catch (Exception ex) {
			System.err.println( ex.getClass().getName() + ": " + ex.getMessage());
			System.out.println("Error en la conexion");
		}

	}

}
