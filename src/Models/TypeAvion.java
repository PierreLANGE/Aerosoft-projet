package models;

public class TypeAvion {
	private String typeAvion;
	private String capacite;
	private String idConstructeur;
	
	public TypeAvion() {
		super();	
	}

	public TypeAvion(String typeAvion, String capacite, String idConstructeur) {
		super();
		this.typeAvion = typeAvion;
		this.capacite = capacite;
		this.idConstructeur = idConstructeur;
	}

	public String getTypeAvion() {
		return typeAvion;
	}

	public void setTypeAvion(String typeAvion) {
		this.typeAvion = typeAvion;
	}

	public String getCapacite() {
		return capacite;
	}

	public void setCapacite(String capacite) {
		this.capacite = capacite;
	}

	public String getIdConstructeur() {
		return idConstructeur;
	}

	public void setIdConstructeur(String idConstructeur) {
		this.idConstructeur = idConstructeur;
	}
	
}
