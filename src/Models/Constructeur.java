package Models;

public class Constructeur {
	
	private Integer idConstructeur;
	private String nomConstructeur;

	public Constructeur() {
		super();
	}

	public Constructeur(Integer idConstructeur, String nomConstructeur) {
		super();
		this.idConstructeur = idConstructeur;
		this.nomConstructeur = nomConstructeur;
	}

	public Integer getIdConstructeur() {
		return idConstructeur;
	}

	public void setIdConstructeur(Integer idConstructeur) {
		this.idConstructeur = idConstructeur;
	}

	public String getNomConstructeur() {
		return nomConstructeur;
	}

	public void setNomConstructeur(String nomConstructeur) {
		this.nomConstructeur = nomConstructeur;
	}
}
