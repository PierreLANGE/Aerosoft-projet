package Models;

public class Pilote {
	private Integer idPilote;
	private String NomPilote;
	private String PrenomPilote;
	
	public Pilote() {
		super();	
	}

	public Pilote(Integer idPilote, String nomPilote, String prenomPilote) {
		super();
		this.idPilote = idPilote;
		NomPilote = nomPilote;
		PrenomPilote = prenomPilote;
	}

	public Integer getIdPilote() {
		return idPilote;
	}

	public void setIdPilote(Integer idPilote) {
		this.idPilote = idPilote;
	}

	public String getNomPilote() {
		return NomPilote;
	}

	public void setNomPilote(String nomPilote) {
		NomPilote = nomPilote;
	}

	public String getPrenomPilote() {
		return PrenomPilote;
	}

	public void setPrenomPilote(String prenomPilote) {
		PrenomPilote = prenomPilote;
	}
	
}
