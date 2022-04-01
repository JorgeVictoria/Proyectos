package biblioteca;

import java.util.Calendar;
import java.util.Date;

public class Prestamo {
	
	//variables del objeto
	private Date desde; //fecha de prestamo
	private Date hasta; //fecha de fin de prestamo
	private Date devuelto; //fecha de devolucion
	protected Ejemplar unEjemplar; //cambio obra por ejemplar
	private Socio unSocio; //socio del prestamo

	//****************************************CONSTRUCTOR ZONE**********************************************************
	//metodo constructor del objeto
	public Prestamo (Ejemplar ejem, Socio socio) {
		this.unEjemplar = ejem;
		this.unSocio = socio;
		this.setDesde();
		this.setHasta();
		
	}
	
	
	//metodo constructor del objeto. Para las copias
	public Prestamo() {
		
	}
	
	//****************************************GETTERS ZONE**********************************************************
	//metodo que devuelve la fecha desde que fue alquilada la obra
	public Date getDesde() {
		return this.desde;
	}
	
	//metodo que devuelve la fecha que finaliza el prestamo
	public Date getHasta() {
		return this.hasta;
	}
	
	//metodo que devuelve la fecha que ha sido devuelto el ejemplar
	public Date getDevuelto() {
		return this.devuelto;
	}
	
	//****************************************SETTERS ZONE**********************************************************
	//metodo para definir la fecha que fue alquilada la obra
	public void setDesde() {
		
		//creamos un nuevo objeto fecha
		this.desde = new Date();
		
	}
	
	//metodo para definir la fecha de devolucion de la obra
	public void setHasta() {
	
		this.hasta = this.getDesde();
		Calendar c = Calendar.getInstance();
		c.setTime(this.hasta);
        c.add(Calendar.DATE, 15); //añadimos quince dias
        this.hasta = c.getTime();
	}
	
	public void setDevuelto() {
		this.devuelto = new Date();
		this.unEjemplar.setPrestadoA(null);
	}
}
