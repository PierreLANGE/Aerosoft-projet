package models;

public class Pilote {
	private int idPilote;
	private String nomPilote;
	private String prenomPilote;
	private String matricule;
	
	public Pilote() {
	}
	
	public Pilote(int idPilote, String nomPilote, String prenomPilote, String matricule) {
		this.setIdPilote(idPilote);
		this.setNomPilote(nomPilote);
		this.setPrenomPilote(prenomPilote);
		this.setMatricule(matricule);
	}

	
	/** 
	 * @return int
	 */
	public int getIdPilote() {
		return idPilote;
	}

	
	/** 
	 * @param idPilote
	 */
	public void setIdPilote(int idPilote) {
		this.idPilote = idPilote;
	}

	
	/** 
	 * @return String
	 */
	public String getNomPilote() {
		return nomPilote;
	}

	
	/** 
	 * @param nomPilote
	 */
	public void setNomPilote(String nomPilote) {
		this.nomPilote = nomPilote;
	}

	
	/** 
	 * @return String
	 */
	public String getPrenomPilote() {
		return prenomPilote;
	}

	
	/** 
	 * @param prenomPilote
	 */
	public void setPrenomPilote(String prenomPilote) {
		this.prenomPilote = prenomPilote;
	}

	
	/** 
	 * @return String
	 */
	public String getMatricule() {
		return this.matricule;
	}

	
	/** 
	 * @param matricule
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
}
