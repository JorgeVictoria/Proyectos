package application;

//clase para almacenar el nombre del producto y el precio

public class Producto {

	//variables miembro
	private String nombreProducto;	//nombre del producto
	private int precioProducto;		//precio del producto
	
	//constructor vacio para crear un objeto. Usaremos setters para establecer el nombre e importe
	public Producto() {
		
	}

	//Getters
	public String getNombreProducto() {
		return nombreProducto;
	}

	public int getPrecioProducto() {
		return precioProducto;
	}

	//Setters
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public void setPrecioProducto(int precioProducto) {
		this.precioProducto = precioProducto;
	}
	
	
	
	
}
