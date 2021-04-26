package Models;


public class Avion {
	// Creation des variables
	private int numAvion;
	private String typeAvion;
	private String baseAeroport;
	
	// Creation des constructeur
	public Avion() {	
	}
	
	public Avion(int numAvion, String typeAvion, String baseAeroport) {
		this.setNumAvion(numAvion);
		this.setTypeAvion(typeAvion);
		this.setBaseAeroport(baseAeroport);
	}

	
	/** 
	 * @return int
	 */
	// Creation des getter et setter
	public int getNumAvion() {
		return numAvion;
	}
	
	/** 
	 * @param numAvion
	 */
	public void setNumAvion(int numAvion) {
		this.numAvion = numAvion;
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

	
	/** 
	 * @return String
	 */
	public String getBaseAeroport() {
		return baseAeroport;
	}

	
	/** 
	 * @param baseAeroport
	 */
	public void setBaseAeroport(String baseAeroport) {
		this.baseAeroport = baseAeroport;
	}
	
	
}


