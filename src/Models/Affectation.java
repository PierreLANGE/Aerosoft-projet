package Models;

public class Affectation {
	private String numVol;
	private java.sql.Date dateVol;
	private String numAvion;
	private Pilote pilote;
	
	public Affectation() {
		super();
	}

	public Affectation(String numVol, java.sql.Date dateVol, String numAvion, Pilote pilote) {
		super();
		this.numVol = numVol;
		this.dateVol = dateVol;
		this.numAvion = numAvion;
		this.pilote = pilote;
	}

	public String getNumVol() {
		return numVol;
	}

	public void setNumVol(String numVol) {
		this.numVol = numVol;
	}

	public java.sql.Date getDateVol() {
		return dateVol;
	}

	public void setDateVol(java.sql.Date dateVol) {
		this.dateVol = dateVol;
	}

	public String getNumAvion() {
		return numAvion;
	}

	public void setNumAvion(String numAvion) {
		this.numAvion = numAvion;
	}

	public Pilote getPilote() {
		return pilote;
	}

	public void setPilote(Pilote idPilote) {
		this.pilote = idPilote;
	}

}
