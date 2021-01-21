package Models;

import java.sql.Time;
import java.time.LocalTime;

public class Vol {
	private String numVol;
	private String aeroportDept;
	private LocalTime hDepart;
	private String aeroportArr;
	private LocalTime hArrivee;

	public Vol() {
		super();
	}

	public Vol(String numVol, String aeroportDept, LocalTime hDepart, String aeroportArr, LocalTime hArrivee) {
		super();
		this.numVol = numVol;
		this.aeroportDept = aeroportDept;
		this.hDepart = hDepart;
		this.aeroportArr = aeroportArr;
		this.hArrivee = hArrivee;
	}

	public String getNumVol() {
		return numVol;
	}

	public void setNumVol(String numVol) {
		this.numVol = numVol;
	}

	public String getAeroportDept() {
		return aeroportDept;
	}

	public void setAeroportDept(String aeroportDept) {
		this.aeroportDept = aeroportDept;
	}

	public LocalTime gethDepart() {
		return hDepart;
	}

	public void sethDepart(String hDepart) {
		
		this.hDepart = LocalTime.parse(hDepart);
	}
	
	public void sethDepart(LocalTime hDepart) {
		
		this.hDepart = hDepart;
	}

	public String getAeroportArr() {
		return aeroportArr;
	}

	public void setAeroportArr(String aeroportArr) {
		this.aeroportArr = aeroportArr;
	}

	public LocalTime gethArrivee() {
		return hArrivee;
	}

	public void sethArrivee(String hArrivee) {
		this.hArrivee = LocalTime.parse(hArrivee);
	}
	
	public void sethArrivee(LocalTime hArrivee) {
		this.hArrivee = hArrivee;
	}
}
