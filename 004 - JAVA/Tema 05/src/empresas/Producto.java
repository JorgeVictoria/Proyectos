package empresas;

public class Producto {
	//variables de los objetos
	private String nombre; //nombre del producto
	private double precioUnidad; //precio de la Unidad
	private double porcentajeComision; //porcentaje de comision por producto
	
	//constructor
	public Producto (String nom, double preUni, double porCom) {
		this.nombre = nom;
		this.precioUnidad = preUni;
		this.porcentajeComision = porCom;
	}
	
	//getters
	public String getNombre() {
		return this.nombre;
	}
	
	public double getPrecioUnidad() {
		return this.precioUnidad;
	}
	
	public double getPorcentajeComision() {
		return this.porcentajeComision;
	}
	
	//setters
	public void setNombre(String nom) {
		this.nombre = nom;
	}
	
	public void setPrecioUnidad(double preUni ) {
		this.precioUnidad = preUni;
	}
	
	public void setPorcentajeComision(double porCom) {
		this.porcentajeComision = porCom;
	}
}
