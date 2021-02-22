package models;
import java.sql.Date;

public class Affectation {
	private String id;
	private String numVol;
	private Date dateVol;
	private Boolean affectationCode;
	private int numAvion;
	private Pilote pilote;
	
	public Affectation() {
	}

	public Affectation(String id, String numVol, Boolean affectationCode, Date dateVol, int numAvion, Pilote pilote) {
		this.id = id;
		this.setNumVol(numVol);
		this.setDateVol(dateVol);
		this.setAffectationCode(affectationCode);
		this.setNumAvion(numAvion);
		this.setPilote(pilote);
	}

	
	/** 
	 * @return String
	 */
	public String getId() {
		return this.id;
	}

	
	/** 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	
	/** 
	 * @return Boolean
	 */
	public Boolean getAffectationCode() {
		return this.affectationCode;
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
	 * @return Date
	 */
	public Date getDateVol() {
		return dateVol;
	}
	
	/** 
	 * @param dateVol
	 */
	public void setDateVol(Date dateVol) {
		this.dateVol = dateVol;
	}
	
	/** 
	 * @return int
	 */
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
	 * @return Pilote
	 */
	public Pilote getPilote() {
		return pilote;
	}

	
	/** 
	 * @param pilote
	 */
	public void setPilote(Pilote pilote) {
		this.pilote = pilote;
	}

	
	/** 
	 * @return Boolean
	 */
	public Boolean isAffectationCode() {
		return this.affectationCode;
	}

	
	/** 
	 * @param affectationCode
	 */
	public void setAffectationCode(Boolean affectationCode) {
		this.affectationCode = affectationCode;
	}
	
}
