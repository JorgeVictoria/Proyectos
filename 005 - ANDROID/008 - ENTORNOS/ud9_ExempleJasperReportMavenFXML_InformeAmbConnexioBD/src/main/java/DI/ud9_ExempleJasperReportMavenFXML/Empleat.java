package DI.ud9_ExempleJasperReportMavenFXML;

public class Empleat {
	private String cognoms;
	private String nom;
	private int vendes;
	
	Empleat (String cognoms, String nom, int vendes) {
		this.cognoms = cognoms;
		this.nom = nom;
		this.vendes = vendes;
	}

	public String getCognoms() {
		return cognoms;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVendes() {
		return vendes;
	}

	public void setVendes(int vendes) {
		this.vendes = vendes;
	}
	
}

