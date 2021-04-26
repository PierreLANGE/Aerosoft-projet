package Dao;

import connexion.ConnectionBdd;
import Interfaces.*;
import java.sql.*;
import java.util.ArrayList;
import Models.*;
import vues.SDialog;

public class PiloteDao implements Dao {

	public PiloteDao() {
	}

	/**
	 * @param idObj
	 * @return ArrayList<Pilote>
	 */
	@Override
	public ArrayList<Pilote> search(Object idObj) {

		int idSearch = (int) idObj;
		Pilote pilote = null;

		Connection conn = null;
		PreparedStatement stmt = null;

		ArrayList<Pilote> listePilotes = new ArrayList<>();

		String sql = "SELECT * FROM `PILOTE` WHERE IdPilote LIKE '" + idSearch + "%'";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			//stmt.setObject(1, idSearch);

			System.out.println("Voici les informations du pilote " + idSearch);

			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {

				pilote = new Pilote(
						res.getInt("IdPilote"),
						res.getString("NomPilote"), 
						res.getString("PrenomPilote"),
						res.getString("Matricule")
				);
					
				listePilotes.add(pilote);

			}
			
			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return listePilotes;
	}


	/**
	 * @param idObj
	 * @return Object
	 */
	@Override
	public Object get(Object idObj) {

		int id = (int) idObj;
		Pilote pilote = null;

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT * FROM `PILOTE` WHERE IdPilote=?";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setObject(1, id);

			System.out.println("Voici les informations du pilote " + id);

			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				pilote = new Pilote(
						res.getInt("IdPilote"),
						res.getString("NomPilote"), 
						res.getString("PrenomPilote"),
						res.getString("Matricule")
					);

			}
			
			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return pilote;
	}

	/**
	 * @return ArrayList<Pilote>
	 */
	@Override
	public ArrayList<Pilote> getAll() {

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT * FROM `PILOTE`";

		ArrayList<Pilote> listePilotes = new ArrayList<>();

		try {

			conn = (Connection) ConnectionBdd.getConnection();
			stmt = (PreparedStatement) conn.prepareStatement(sql);

			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {

				Pilote pilote = new Pilote(
						res.getInt("IdPilote"),
						res.getString("NomPilote"),
						res.getString("PrenomPilote"), 
						res.getString("Matricule")
					);

				listePilotes.add(pilote);
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Impossible d'afficher les pilotes");
			throw new RuntimeException(e);
		}

		return listePilotes;
	}

	/**
	 * @param t
	 * @param params
	 */
	@Override
	public void save(Object t, String[] params) {

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql =
				"INSERT INTO `PILOTE` (IdPilote, NomPilote, PrenomPilote, Matricule) VALUES (?,?,?,?)";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, Integer.parseInt(params[0]));
			stmt.setString(2, params[1]);
			stmt.setString(3, params[2]);
			stmt.setString(4, params[3]);

			stmt.execute();

			System.out.println(params[2] + " a bien été ajouté");
			new SDialog("Ajout", "Ajouter reussie", "Valider", "").setVisible(true);

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Impossible d'ajouter un pilote");
			new SDialog("Echec", "L'ajout n'a pas reussie car " + e, "ok", "").setVisible(true);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param t
	 * @param params
	 */
	@Override
	public void update(Object t, String[] params) {

		Pilote pilote = (Pilote) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionBdd.getConnection();

			stmt = conn.prepareStatement(
					"UPDATE `PILOTE` SET NomPilote=?,PrenomPilote=?,Matricule=? WHERE IdPilote=?");

			stmt.setString(1, params[0]);
			stmt.setString(2, params[1]);
			stmt.setString(3, params[2]);
			stmt.setInt(4, pilote.getIdPilote());

			stmt.executeUpdate();

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

		Pilote pilote = (Pilote) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement("DELETE FROM `PILOTE` WHERE `IdPilote`=?",
					Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, pilote.getIdPilote());
			stmt.execute();

			System.out.println(pilote.getMatricule() + " a bien été Supprimé");

			new SDialog("Suppresssion", "Suppresssion reussie", "Valider", "").setVisible(true);

		} catch (SQLException e) {

			e.printStackTrace();
			new SDialog("Echec", "La suppresssion n'a pas reussie car " + e, "ok", "")
					.setVisible(true);

			throw new RuntimeException(e);
		}

		try {

			stmt.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param name
	 * @return int
	 */
	public int getIdByName(String name) {

		System.out.println("getIdByName name : " + name);

		int IdPiloteToReturn = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT `IdPilote` FROM `PILOTE` WHERE `NomPilote` LIKE '" + name + "'";

		try {

			conn = (Connection) ConnectionBdd.getConnection();
			stmt = (PreparedStatement) conn.prepareStatement(sql);

			System.out.println("getIdByName stmt : " + stmt);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				IdPiloteToReturn = res.getInt("IdPilote");

			}

		} catch (SQLException e) {

			e.printStackTrace();

			new SDialog("Echec", "La selection du pilote n'a pas reussie car " + e, "ok", "")
					.setVisible(true);

			throw new RuntimeException(e);
		}

		try {

			stmt.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
			
		}

		return IdPiloteToReturn;
	}
}
