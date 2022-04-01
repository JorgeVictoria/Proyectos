package empresas;

public class Repartidor extends Empleado {
	//variables del objeto
		private int numRepartos; //almacena la comision del empleado
		
		public Repartidor(String nom, String cumple, String contrat, double salari) {
			super(nom,cumple,contrat,salari);
		}
		
		//getters
		public double getRepartos() {
			return this.numRepartos;
		}
		
		//setters
		public void setRepartos(int repartos) {
			this.numRepartos = repartos;
		}
		
		//metodo que devuelve el salario, heredado de la clase padre
		public double getSalario() {
			//variables locales
			double total;
			
			total = this.getSalarioBase() + this.numRepartos;
			this.setNomina(total);
			this.setRepartos(0);
			
			return this.getNomina();
			
		}
	}

