package empresas;

public class Comercial extends Empleado {
	//variables del objeto
	private double comision; //almacena la comision del empleado
	
	public Comercial(String nom, String cumple, String contrat, double salari) {
		super(nom,cumple,contrat,salari);
	}
	
	//getters
	public double getComision() {
		return this.comision;
	}
	
	//setters
	public void setComision(double comis) {
		this.comision = comis;
	}
	
	//metodo que devuelve el salario, heredado de la clase padre
	public double getSalario() {
		//variables locales
		double total = this.getSalarioBase() + this.getComision();
		this.setNomina(total);
		this.setComision(0);
		return this.getNomina();
	}
}
