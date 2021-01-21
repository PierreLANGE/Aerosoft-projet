package Models;

public class Aeroport {

	private String idAeroport;
	private String nomAeroport;
	private String nomVilleDesservie;
	
	public Aeroport() {
		super();
	}
	
	public Aeroport(String idAeroport, String nomAeroport, String nomVilleDesservie) {
		super();
		this.idAeroport = idAeroport;
		this.nomAeroport = nomAeroport;
		this.nomVilleDesservie = nomVilleDesservie;
	}

	public String getIdAeroport() {
		return idAeroport;
	}

	public void setIdAeroport(String idAeroport) {
		this.idAeroport = idAeroport;
	}

	public String getNomAeroport() {
		return nomAeroport;
	}

	public void setNomAeroport(String nomAeroport) {
		this.nomAeroport = nomAeroport;
	}

	public String getNomVilleDesservie() {
		return nomVilleDesservie;
	}

	public void setNomVilleDesservie(String nomVilleDesservie) {
		this.nomVilleDesservie = nomVilleDesservie;
	}

}
