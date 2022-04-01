import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class Neodatis {

	public static void main(String[] args) {

		Cliente cliente = new Cliente("jvandreu@hotmail.com", "desarrollador");
		
		ODB odb = null;
		 
        try {
            // Open the database
            odb = ODBFactory.open("clientes.db");

            // Store the object
            odb.store(cliente);
        } finally {
            if (odb != null) {
                // Close the database
                odb.close();
            }
        }

	}

}
