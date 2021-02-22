package models;

public class Aeroport {
	private String idAeroport;
	private String nomAeroport;
	private String nomVille;
	
	public Aeroport() {
	}
	public Aeroport(String idAeroport, String nomAeroport, String nomVille) {
		this.setIdAeroport(idAeroport);
		this.setNomAeroport(nomAeroport);
		this.setNomVille(nomVille);
	}
	
	/** 
	 * @return String
	 */
	public String getIdAeroport() {
		return idAeroport;
	}
	
	/** 
	 * @param idAeroport
	 */
	public void setIdAeroport(String idAeroport) {
		this.idAeroport = idAeroport;
	}
	
	/** 
	 * @return String
	 */
	public String getNomAeroport() {
		return nomAeroport;
	}
	
	/** 
	 * @param nomAeroport
	 */
	public void setNomAeroport(String nomAeroport) {
		this.nomAeroport = nomAeroport;
	}
	
	/** 
	 * @return String
	 */
	public String getNomVille() {
		return nomVille;
	}
	
	/** 
	 * @param nomVille
	 */
	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}
	

}
