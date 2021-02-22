package models;

public class Utilisateur {
    // Creation des variables
	private int idUtilisateur;
    private String mail;
    private String motDePasse;
    private boolean statut = false;
    private String idRole;
	
	// Creation des constructeur
	public Utilisateur() {
	}

	public Utilisateur(int idUtilisateur, String mail, String motDePasse, boolean statut, String idRole) {
        this.setIdUtilisateur(idUtilisateur);
        this.setMail(mail);
        this.setMotDePasse(motDePasse);
        this.setStatut(statut);
        this.setIdRole(idRole);
	}
	
    /** 
     * @return int
     */
    //Creation des getter et setter
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	
    /** 
     * @param idUtilisateur
     */
    public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

    
    /** 
     * @return String
     */
    public String getMail() {
        return this.mail;
    }

    
    /** 
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    
    /** 
     * @return String
     */
    public String getMotDePasse() {
        return this.motDePasse;
    }

    
    /** 
     * @param motDePasse
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    
    /** 
     * @return boolean
     */
    public boolean getStatut() {
        return this.statut;
    }

    
    /** 
     * @param statut
     */
    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    
    /** 
     * @return String
     */
    public String getIdRole() {
        return this.idRole;
    }

    
    /** 
     * @param idRole
     */
    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }
}
