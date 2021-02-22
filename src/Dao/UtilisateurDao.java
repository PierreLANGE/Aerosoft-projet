package dao;

import connexion.ConnectionBdd;
import interfaces.*;
import java.sql.*;
import java.util.ArrayList;
import models.Utilisateur;
import vues.SDialog;

public class UtilisateurDao implements Dao {

	public UtilisateurDao() {
	}

		/**
	 * @param idOjb
	 * @return ArrayList<Utilisateur>
	 */
	public ArrayList<Utilisateur> search(Object idOjb) {
		
		int idSearch = (int) idOjb;
		Utilisateur utilisateur = null;

		Connection conn = null;
		PreparedStatement stmt = null;

		ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<>();

		String sql = "SELECT * FROM `UTILISATEUR` WHERE IdUtilisateur LIKE '" + idSearch + "%'";

		try {
			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {

				utilisateur = new Utilisateur(res.getInt("IdUtilisateur"), res.getString("Mail"),
						res.getString("MotDePasse"), res.getBoolean("Statut"),
						res.getString("IdRole"));

				listeUtilisateurs.add(utilisateur);
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Impossible d'afficher les Utilisateurs");
			throw new RuntimeException(e);
		}

		return listeUtilisateurs;
	}

	/**
	 * @param idOjb
	 * @return Object
	 */
	public Object get(Object idOjb) {
		int id = (int) idOjb;
		Utilisateur utilisateur = null;

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT * FROM `UTILISATEUR` WHERE IdUtilisateur =" + id;

		try {
			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {

				utilisateur = new Utilisateur(res.getInt("IdUtilisateur"), res.getString("Mail"),
						res.getString("MotDePasse"), res.getBoolean("Statut"),
						res.getString("IdRole"));
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Impossible d'afficher les Utilisateurs");
			throw new RuntimeException(e);
		}

		return utilisateur;
	}

	/**
	 * @return ArrayList<Utilisateur>
	 */
	public ArrayList<Utilisateur> getAll() {

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT * FROM `UTILISATEUR`";

		ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<>();

		try {
			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {

				Utilisateur utilisateur = new Utilisateur(res.getInt("IdUtilisateur"),
						res.getString("Mail"), res.getString("MotDePasse"),
						res.getBoolean("Statut"), res.getString("IdRole"));

				listeUtilisateurs.add(utilisateur);
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Impossible d'afficher les pilotes");
			throw new RuntimeException(e);
		}
		return listeUtilisateurs;
	}

	/**
	 * @param t
	 * @param params
	 */
	public void save(Object t, String[] params) {

		Utilisateur utilisateur = (Utilisateur) t;

		Connection conn = null;
		PreparedStatement stmt = null;
		String sql =
				"INSERT INTO `UTILISATEUR` (IdUtilisateur,Mail,MotDePasse,Statut,IdRole) VALUES (?,?,?,?,?)";

		try {
			conn = ConnectionBdd.getConnection();

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, Integer.parseInt(params[0]));
			stmt.setString(2, params[1]);
			stmt.setString(3, params[2]);
			stmt.setBoolean(4, Boolean.parseBoolean(params[3]));
			stmt.setString(5, params[4]);

			stmt.execute();

			System.out.println(utilisateur.getIdUtilisateur() + " a bien été ajouté");
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
	public void update(Object t, String[] params) {

		Utilisateur utilisateur = (Utilisateur) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionBdd.getConnection();

			stmt = conn.prepareStatement(
					"UPDATE `UTILISATEUR` SET IdUtilisateur=?,Mail=?,MotDePasse=?,Statut=?,IdRole=? WHERE IdUtilisateur=?");

			stmt.setString(1, params[0]);
			stmt.setString(2, params[1]);
			stmt.setString(3, params[2]);
			stmt.setBoolean(4, Boolean.parseBoolean(params[3]));
			stmt.setString(5, params[4]);
			stmt.setInt(6, utilisateur.getIdUtilisateur());

			stmt.executeUpdate();

		} catch (SQLException e) {

			// e.printStackTrace();
			new SDialog("Echec", "La modification n'a pas reussie car " + e, "ok", "")
					.setVisible(true);
					
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param t
	 */
	public void delete(Object t) {

		Utilisateur utilisateur = (Utilisateur) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement("DELETE FROM `UTILISATEUR` WHERE `IdUtilisateur`=?",
					Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, utilisateur.getIdUtilisateur());

			stmt.execute();

			System.out.println(utilisateur.getIdUtilisateur() + " a bien été Supprimé");

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
			throw new RuntimeException(e);
		}
	}
}
