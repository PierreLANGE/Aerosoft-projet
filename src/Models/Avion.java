package Models;

public class Avion {
	private Integer nunAvion;
	private String TypeAvion;
	private String BaseAeroport;
	
	public Avion() {
		super();
	}
	
	public Avion(Integer nunAvion, String typeAvion, String baseAeroport) {
		super();
		this.nunAvion = nunAvion;
		TypeAvion = typeAvion;
		BaseAeroport = baseAeroport;
	}

	public Integer getNunAvion() {
		return nunAvion;
	}

	public void setNunAvion(Integer nunAvion) {
		this.nunAvion = nunAvion;
	}

	public String getTypeAvion() {
		return TypeAvion;
	}

	public void setTypeAvion(String typeAvion) {
		TypeAvion = typeAvion;
	}

	public String getBaseAeroport() {
		return BaseAeroport;
	}

	public void setBaseAeroport(String baseAeroport) {
		BaseAeroport = baseAeroport;
	}
	
}
