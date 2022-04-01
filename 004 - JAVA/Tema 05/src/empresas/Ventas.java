package empresas;

public class Ventas {
	//variables del objeto
	private Comercial comercial; //comercial que crea la venta
	private Repartidor repartidor; //repartidor que realiza la venta
	private Producto producto; //producto que se vende
	private int cantidad; //cantidad de productos que se venden 
	private boolean entregado; //si el producto ha sido entregado
	
	//constructor
	public Ventas(Comercial com, Repartidor rep, Producto prod, int cant) {
		this.comercial = com;
		this.repartidor = rep;
		this.producto = prod;
		this.cantidad = cant;
		setEntregado(false); //inicialmente ponemos la venta en no entregado
	}
	
	//getters
	public int getCantidad() {
		return this.cantidad;
	}
	
	public boolean getEntregado() {
		return this.entregado;
	}
	
	//setters
	private void setCantidad(int cant) {
		this.cantidad = cant;
	}
	
	public void setEntregado(boolean entreg) {
		
		this.entregado = entreg;
		
		//si entregada is true, calcularemos la comision y pasamos el reparto al repartidor para su nomina
		if (this.getEntregado()) {
			calculaComision();
			this.repartidor.setRepartos(1);
		}
	}
	
	//metodos para calcular la nueva comision
	public void calculaComision() {
		//variables locales
		double nuevaComision;
		 
		//calculamos la nueva comision del comercial
		nuevaComision = (this.producto.getPrecioUnidad() * this.producto.getPorcentajeComision()) * this.cantidad;
		
		//pasamos la comision al comercial
		this.comercial.setComision(nuevaComision);
		
	}
}
