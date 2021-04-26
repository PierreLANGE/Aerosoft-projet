package Models;

public class Vol {
	private String numVol;
	private String aeroportDepart;
	private String heureDepart;
	private String aeroportArrive;
	private String heureArrive;
	
	public Vol() {
	}
	public Vol(String numVol, String aeroportDepart, String heureDepart, String aeroportArrive, String heureArrive) {
		this.setNumVol(numVol);
		this.setAeroprtDepart(aeroportDepart);
		this.setHeureDepart(heureDepart);
		this.setAeroportArrive(aeroportArrive);
		this.setHeureArrive(heureArrive);
	}
	
	/** 
	 * @return String
	 */
	public String getNumVol() {
		return numVol;
	}
	
	/** 
	 * @param numVol
	 */
	public void setNumVol(String numVol) {
		this.numVol = numVol;
	}
	
	/** 
	 * @return String
	 */
	public String getAeroportDepart() {
		return aeroportDepart;
	}
	
	/** 
	 * @param aeroportDepart
	 */
	public void setAeroprtDepart(String aeroportDepart) {
		this.aeroportDepart = aeroportDepart;
	}
	
	/** 
	 * @return String
	 */
	public String getHeureDepart() {
		return heureDepart;
	}
	
	/** 
	 * @param heureDepart
	 */
	public void setHeureDepart(String heureDepart) {
		this.heureDepart = heureDepart;
	}
	
	/** 
	 * @return String
	 */
	public String getAeroportArrive() {
		return aeroportArrive;
	}
	
	/** 
	 * @param aeroportArrive
	 */
	public void setAeroportArrive(String aeroportArrive) {
		this.aeroportArrive = aeroportArrive;
	}
	
	/** 
	 * @return String
	 */
	public String getHeureArrive() {
		return heureArrive;
	}
	
	/** 
	 * @param heureArrive
	 */
	public void setHeureArrive(String heureArrive) {
		this.heureArrive = heureArrive;
	}
	
}
