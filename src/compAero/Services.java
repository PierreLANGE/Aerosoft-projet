package compAero;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import Dao.AeroportDao;
import Dao.AffectationDao;
import Dao.PiloteDao;
import Dao.VolDao;
import Models.Affectation;
import Models.Pilote;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class Services {
	
	public static final AffectationDao affectationDao = new AffectationDao();
	public static final AeroportDao aeroportDao = new AeroportDao();
	public static final VolDao volDao = new VolDao();
	public static final PiloteDao piloteDao = new PiloteDao();
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/compagnie";
	static final String USER = "root";
	static final String PASS = "";
	Affichage af;
	
	public Services() {
		this.af = new Affichage();
	}

	public Connection getConn(Connection conn) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Statement getStmt(Statement stmt, final Connection conn) {
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public void afficheListePilotes(List<Pilote> pilotes) {
		
			this.af.titre("Liste des Pilotes",0);
			this.af.lignePilotes(pilotes);		
	}

	public void ajoutePilote() {
		this.afficheListePilotes(piloteDao.getAll());
		final BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println(" ");
			System.out.printf("Ajouter Pr\u00e9nom: ", new Object[0]);
			final String prenom = entree.readLine();
			System.out.println(" ");
			System.out.printf("Ajouter Nom: ", new Object[0]);
			final String nom = entree.readLine();
			
			Pilote p =new Pilote();
			p.setNomPilote(nom);
			p.setPrenomPilote(prenom);
			
			piloteDao.save(p);
			
		} catch (IOException ex2) {

			ex2.printStackTrace();
		}
		
		this.af.titre("Ajout de Pilote effectu\u00e9",0);
		
		this.afficheListePilotes(piloteDao.getPilotes());
		this.circulationMenu();
	}

	public void listeDesVols(final String searchVol) {
				
		if (searchVol != "") {
			this.af.ligneVol(volDao.getVolInVols(searchVol));
		}else {
			this.af.ligneVol(volDao.getVols());
		}
				
		this.circulationMenu();
	}

	public void listeDesAffectation(List<Affectation> affectations) {

		try {
			this.af.ligneAffectation(affectations);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getRowCount(final ResultSet resultSet) {
		if (resultSet == null) {
			return 0;
		}
		try {
			resultSet.last();
			return resultSet.getRow();
		} catch (SQLException exp) {
			exp.printStackTrace();
			try {
				resultSet.beforeFirst();
			} catch (SQLException exp2) {
				exp2.printStackTrace();
			}
		} finally {
			try {
				resultSet.beforeFirst();
			} catch (SQLException exp2) {
				exp2.printStackTrace();
			}
		}
		return 0;
	}

	public void searchVols() {
		this.af.titre("Recherche d'un vol",0);
		String c = "";
		final BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println(" ");
			System.out.printf("tapez votre NÂ° de Vol(\"ITXXX\"): ", new Object[0]);
			c = entree.readLine();
			this.listeDesVols(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.circulationMenu();
	}

	public void listeDesAeroParVille() {

		AeroportDao aeroportDao = new AeroportDao();
		af.ligneAeroport(aeroportDao.getAll());

		this.circulationMenu();
	}

	public void modifierSupprimerAffectation(final boolean suppression) {
		
	
		List<Affectation> affectations = affectationDao.getAffectations();

		
		String TypeAtion= "";
		
		if (suppression) {
			TypeAtion = "Supprimer";
			this.af.titre(TypeAtion +" une Affectation",0);
		} else {
			TypeAtion = "Modifier";
			this.af.titre(TypeAtion + " la date d'une Affectation",0);
		}
		
		this.listeDesAffectation(affectations);
				
		String nouvelleDateAModifier = "";
		
		String[] params;
		
		BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.printf("Entrez Le N° de ligne \u00e9e  " + TypeAtion + ": ", new Object[0]);
			int numLigne = Integer.parseInt(entree.readLine());
			
			/*System.out.println(" ");
			
			System.out.printf("quel Nï¿½ d'Avion: ", new Object[0]);
			final String numAvionPourModifier = entree.readLine();
			
			System.out.println(" ");
			
			System.out.printf("De quel Nom de Pilote: ", new Object[0]);
			final String pilotePourModifier = entree.readLine();*/
			
			if (!suppression) {
				
				params = new String[1];
						
			}else {
				params = new String[0];
			}
						
			if (!suppression) {
				
				System.out.println(" ");
				System.out.printf("Entrez La Nouvelle Date: ", new Object[0]);
				nouvelleDateAModifier = entree.readLine();
				
				params[0] = nouvelleDateAModifier;
						
			}
						
			if (!suppression) {
				//sql = "UPDATE Affectation SET DateVol='" + nouvelleDateAModifier + "'" + condition;
				
				
				affectationDao.update(affectations.get(numLigne-1), params);
			} else {
				affectationDao.delete(affectations.get(numLigne-1));
			}
			
			/*try {
				stmt.executeUpdate(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			stmt.close();
			conn.close();*/
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		this.listeDesAffectation(affectations);
		
		if (!suppression) {
			this.af.titre("Modifier la date effectu\u00e9e",2000);
		} else {
			this.af.titre("Suppression effectu\u00e9e",2000);
		}
		
		this.circulationMenu();
	}

	public void circulationMenu() {
		String c = "";
		final BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
		this.af.menu();

		try {
			System.out.println(" ");
			System.out.printf("tapez votre choix: ", new Object[0]);
			c = entree.readLine();

			switch (c) {
			case "1": {
				this.ajoutePilote();
				break;
			}
			case "2": {
				this.listeDesVols("");
				break;
			}
			case "3": {
				this.searchVols();
				break;
			}
			case "4": {
				this.listeDesAeroParVille();
				break;
			}
			case "5": {
				this.modifierSupprimerAffectation(false);
				break;
			}
			case "6": {
				this.modifierSupprimerAffectation(true);
				break;
			}
			case "7": {
				break;
			}
			default:
				this.circulationMenu();
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.af.titre("Goodbye!",0);
	}
}
