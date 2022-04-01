package biblioteca;

public interface Prestable {
	
	//metodos de la interfaz
	
	public Prestamo solicitaPrestamo(Ejemplar e) throws Exception;
	
	public void devolverPrestamos(Prestamo p);
	
	public void pagaSancion(int importe);

}
