package models;

public class DetailAvion {
	// Creation des variables
	private String typeAvion;
	private int capaciteAvion;
	private int idConstructeur;
	
	// Creation des constructeur
	public DetailAvion() {
	}
	public DetailAvion(String typeAvion, int capaciteAvion, int idConstructeur) {
			this.setTypeAvion(typeAvion);
			this.setCapaciteAvion(capaciteAvion);
			this.setIdConstructeur(idConstructeur);
	}
	
	/** 
	 * @return int
	 */
	//Creation des getter et setter
	public int getIdConstructeur() {
		return idConstructeur;
	}
	
	/** 
	 * @param idConstructeur
	 */
	public void setIdConstructeur(int idConstructeur) {
		this.idConstructeur = idConstructeur;
	}
	
	/** 
	 * @return int
	 */
	public int getCapaciteAvion() {
		return capaciteAvion;
	}
	
	/** 
	 * @param capaciteAvion
	 */
	public void setCapaciteAvion(int capaciteAvion) {
		this.capaciteAvion = capaciteAvion;
	}
	
	/** 
	 * @return String
	 */
	public String getTypeAvion() {
		return typeAvion;
	}
	
	/** 
	 * @param typeAvion
	 */
	public void setTypeAvion(String typeAvion) {
		this.typeAvion = typeAvion;
	}
}
