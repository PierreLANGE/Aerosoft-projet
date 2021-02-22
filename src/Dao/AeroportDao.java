package dao;

import connexion.ConnectionBdd;
import interfaces.Dao;
import java.sql.*;
import java.util.ArrayList;
import models.*;
import vues.SDialog;

public class AeroportDao implements Dao {

	public AeroportDao() {
	}

	/**
	 * @return ArrayList<Aeroport>
	 */
	@Override
	public ArrayList<Aeroport> getAll() {

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT * FROM `AEROPORT` ORDER BY NomVilleDesservie";

		ArrayList<Aeroport> listeAeroports = new ArrayList<>();

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {

				// Retrieve by column name
				Aeroport aeroport = new Aeroport(res.getString("IdAeroport"),
						res.getString("NomAeroport"), res.getString("NomVilleDesservie"));

				listeAeroports.add(aeroport);
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listeAeroports;
	}

		/**
	 * @param id
	 * @return ArrayList<Aeroport>
	 */
	@Override
	public ArrayList<Aeroport> search(Object id) {
		
		String idSearch = String.valueOf(id);
		
		Aeroport aeroport = null;

		System.out.println("idSearch : " + idSearch);


		Connection conn = null;
		PreparedStatement stmt = null;

		ArrayList<Aeroport> listeAeroports = new ArrayList<>();

		String sql = "SELECT * FROM `AEROPORT` WHERE IdAeroport LIKE '" + idSearch + "%'";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet res = stmt.executeQuery(sql);

			System.out.println("Voici les informations de l'aeroport " + id);

			while (res.next()) {

				aeroport = new Aeroport(res.getString("IdAeroport"), res.getString("NomAeroport"),
						res.getString("NomVilleDesservie"));

				listeAeroports.add(aeroport);
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listeAeroports;
	}

	/**
	 * @param id
	 * @return Object
	 */
	@Override
	public Object get(Object id) {

		Aeroport aeroport = null;
		String idSearch = String.valueOf(id);

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT * FROM `AEROPORT` WHERE IdAeroport=?";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, idSearch);

			ResultSet res = stmt.executeQuery();

			System.out.println("Voici les informations de l'aeroport " + id);

			while (res.next()) {

				aeroport = new Aeroport(res.getString("IdAeroport"), res.getString("NomAeroport"),
						res.getString("NomVilleDesservie"));
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return aeroport;
	}


	/**
	 * @param t
	 * @param params
	 */
	@Override
	public void save(Object t, String[] params) {

		Aeroport aeroport = (Aeroport) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql =
				"INSERT INTO `AEROPORT` (IdAeroport,NomAeroport,NomVilleDesservie) VALUES (?,?,?)";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, params[0]);
			stmt.setString(2, params[1]);
			stmt.setString(3, params[2]);

			stmt.execute();

			System.out.println(aeroport.getNomAeroport() + " a bien été ajouté");

			new SDialog("Ajout", "Ajouter reussie", "Valider", "").setVisible(true);

		} catch (SQLException e) {

			e.printStackTrace();
			new SDialog("Echec", "L'ajout n'a pas reussie car " + e, "ok", "").setVisible(true);

		}
	}

	/**
	 * @param t
	 * @param params
	 */
	@Override
	public void update(Object t, String[] params) {

		Aeroport aeroport = (Aeroport) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "UPDATE `AEROPORT` SET NomAeroport=?,NomVilleDesservie=? WHERE IdAeroport=?";

		try {

			conn = ConnectionBdd.getConnection();

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, params[1]);
			stmt.setString(2, params[2]);
			stmt.setString(3, aeroport.getIdAeroport());
			stmt.executeUpdate();

			System.out.println(aeroport.getIdAeroport() + " a bien �t� modifi�");

		} catch (SQLException e) {

			new SDialog("Echec", "La modification n'a pas reussie car " + e, "ok", "")
					.setVisible(true);

			throw new RuntimeException(e);
		}
	}

	/**
	 * @param t
	 */
	@Override
	public void delete(Object t) {

		Aeroport aeroport = (Aeroport) t;

		System.out.println("delete aeroport : " + aeroport.getNomAeroport());

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "DELETE FROM `AEROPORT` WHERE IdAeroport=?";

		try {
			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, aeroport.getIdAeroport());

			stmt.execute();

			System.out.println(aeroport.getIdAeroport() + " a bien �t� supprim�");

			new SDialog("Suppresssion", "Suppresssion reussie", "Valider", "").setVisible(true);

		} catch (SQLException e) {

			e.printStackTrace();
			new SDialog("Echec", "La suppresssion n'a pas reussie car " + e, "ok", "")
					.setVisible(true);

		}

		try {

			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
