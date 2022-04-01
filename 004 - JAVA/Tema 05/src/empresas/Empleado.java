package empresas;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Empleado {
	//variables locales
	private static DateTimeFormatter formatEurope = DateTimeFormatter.ofPattern("dd/LL/yyyy"); //fechas en formato europeo
	private static Period  periodo; //para calcular la diferencia entre fechas
	
	//variables del objeto
	private String nombre; //nombre del empleado
	private LocalDate nace; //fecha de nacimiento
	private LocalDate contrato; //fecha de inicio del contrato
	private double salarioBase; //salario base del empleado
	private double nomina; //nomina del empleado
	
	//constructor
	public Empleado(String nom, String cumple, String contrat, double salari) {
		this.nombre =nom;
		this.nace = LocalDate.parse(cumple, formatEurope);
		this.contrato = LocalDate.parse(contrat, formatEurope);
		this.salarioBase = salari;
		
		//vemos si es el empleado está en edad legal de ser contratado
		esMayor(this.nace, this.contrato);
	}
	
	//getters
	public String getNombre() {
		return this.nombre;
	}
	
	public LocalDate getNace() {
		return this.nace;
	}
	
	public LocalDate getContrato() {
		return this.contrato;
	}
	
	public double getSalarioBase() {
		return this.salarioBase;
	}
	
	public double getNomina() {
		return this.nomina;
	}
	
	//setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setNace(String fecha) {
		this.nace = LocalDate.parse(fecha, formatEurope);
	}
	
	public void setContrato(String fecha) {
		this.contrato = LocalDate.parse(fecha, formatEurope);
	}
	
	public void setSalarioBase (double salario) {
		this.salarioBase = salario;
	}
	
	public void setNomina(double nomina) {
		this.nomina = nomina;
	}
	
	//subrutinas y metodos varios
	
	//subrutina para comprobar si el empleado es mayor de edad
	private static void esMayor(LocalDate nacim, LocalDate contrato) {
		periodo = Period.between(nacim, contrato );
		assert (periodo.getYears() >= 18): "Es menor de edad. No puede ser contratado";
	}
	
	//metodo para imprimir el valor y palo de una carta
		@Override
		public String toString() {
			//variables locales
			String cadena="";
			
			cadena = cadena + "Nombre: " + this.getNombre() 
							+ "  Fecha Nacimiento: "+ this.getNace() 
							+ "  Fecha de Contrato: " + this.getContrato()
							+ "  Salario Base: " + this.getSalarioBase()
							+ "  Nomina:  " + Math.round (this.getSalario()*100d)/100d;
			
			return cadena;
			
		}
	
	//metodo abstracto para calcular el salario
	 public abstract double getSalario();
}
