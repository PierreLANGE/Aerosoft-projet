package Dao;

import connexion.ConnectionBdd;
import Interfaces.Dao;
import java.sql.*;
import java.util.ArrayList;
import Models.*;
import vues.SDialog;

public class VolDao implements Dao {

	public VolDao() {
	}

	/**
	 * @param id
	 * @return ArrayList<Vol>
	 */
	@Override
	public ArrayList<Vol> search(Object id) {

		String idSearch = String.valueOf(id);

		Vol vol = null;

		Connection conn = null;
		PreparedStatement stmt = null;

		ArrayList<Vol> listeVols = new ArrayList<>();

		String sql = "SELECT * FROM `VOL` WHERE NumVol LIKE '" + idSearch + "%'";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			
			System.out.println("Voici les informations du vol " + idSearch);
			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {

				vol = new Vol(res.getString("NumVol"), res.getString("AeroportDept"),
						res.getString("HDepart"), res.getString("AeroportArr"),
						res.getString("HArrivee"));

				listeVols.add(vol);
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return listeVols;
	}

	/**
	 * @param id
	 * @return Object
	 */
	@Override
	public Object get(Object id) {

		Vol vol = null;

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT * FROM `VOL` WHERE NumVol=?";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setObject(1, id);

			System.out.println("Voici les informations du vol " + id);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				vol = new Vol(res.getString("NumVol"), res.getString("AeroportDept"),
						res.getString("HDepart"), res.getString("AeroportArr"),
						res.getString("HArrivee"));
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return vol;
	}

	/**
	 * @return ArrayList<Vol>
	 */
	@Override
	public ArrayList<Vol> getAll() {

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT * FROM `VOL`";

		ArrayList<Vol> listeVols = new ArrayList<>();

		try {
			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {

				Vol vol = new Vol(res.getString("NumVol"), res.getString("AeroportDept"),
						res.getString("HDepart"), res.getString("AeroportArr"),
						res.getString("HArrivee"));

				listeVols.add(vol);
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		}

		return listeVols;
	}

	/**
	 * @param t
	 * @param params
	 */
	@Override
	public void save(Object t, String[] params) {

		Vol vol = (Vol) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql =
				"INSERT INTO `VOL` (NumVol, AeroportDept, HDepart, AeroportArr, HArrivee) VALUES (?,?,?,?,?)";

		try {

			conn = ConnectionBdd.getConnection();

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, params[0]);
			stmt.setString(2, params[1]);
			stmt.setString(3, params[2]);
			stmt.setString(4, params[3]);
			stmt.setString(5, params[4]);
			stmt.execute();

			System.out.println(vol.getNumVol() + " a bien été ajouté");
			new SDialog("Ajout", "Ajouter reussie", "Valider", "").setVisible(true);

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Impossible d'ajouter un Vol");
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

		Vol vol = (Vol) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionBdd.getConnection();

			stmt = conn.prepareStatement(
					"UPDATE `VOL` SET AeroportDept=?,HDepart=?,AeroportArr=?,HArrivee=? WHERE NumVol=?");

			stmt.setString(1, params[1]);
			stmt.setString(2, params[2]);
			stmt.setString(3, params[3]);
			stmt.setString(4, params[4]);
			stmt.setString(5, vol.getNumVol());

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

		Vol vol = (Vol) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionBdd.getConnection();

			stmt = conn.prepareStatement("DELETE FROM `VOL` WHERE `NumVol`=?",
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, vol.getNumVol());

			stmt.execute();

			System.out.println(vol.getNumVol() + " a bien été Supprimé");
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
			
		}
	}
}
