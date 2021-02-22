package dao;

import connexion.ConnectionBdd;
import interfaces.Dao;
import java.sql.*;
import java.util.ArrayList;
import models.*;
import vues.SDialog;

public class RoleDao implements Dao {

	/**
	 * @param id
	 * @return ArrayList<Role>
	 */
	public ArrayList<Role> search(Object id) {

		Role role = null;
		String idSearch = String.valueOf(id);

		Connection conn = null;
		PreparedStatement stmt = null;

		ArrayList<Role> listeRole = new ArrayList<>();

		String sql = "SELECT * FROM `ROLES` WHERE RoleNom LIKE '" + idSearch + "%'";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			
			System.out.println("Voici les informations du role " + idSearch);
			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {

				role = new Role(res.getString("IdRole"), res.getString("RoleNom"));

				listeRole.add(role);

			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listeRole;
	}

	/**
	 * @param id
	 * @return Object
	 */
	public Object get(Object id) {

		Role role = null;
		String idSearch = String.valueOf(id);

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT * FROM `ROLES` WHERE RoleNom=?";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, idSearch);

			System.out.println("Voici les informations du role " + idSearch);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				role = new Role(res.getString("IdRole"), res.getString("RoleNom"));

			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return role;
	}

	/**
	 * @return ArrayList<Role>
	 */
	public ArrayList<Role> getAll() {

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT * FROM `ROLES`";

		ArrayList<Role> listeRole = new ArrayList<>();

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {

				Role role = new Role(res.getString("IdRole"), res.getString("RoleNom"));
				listeRole.add(role);
			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Impossible d'afficher les roles");

		}

		return listeRole;
	}

	/**
	 * @param t
	 * @param params
	 */
	@Override
	public void save(Object t, String[] params) {

		Role role = (Role) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "INSERT INTO `ROLES` (IdRole,RoleNom) VALUES (?,?)";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, params[0]);
			stmt.setString(2, params[1]);

			stmt.execute();

			System.out.println(role.getIdRole() + " a bien été ajouté");
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
		
		Role role = (Role) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "UPDATE `ROLES` set IdRole=?,RoleNom=? WHERE IdRole=?";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, params[0]);
			stmt.setString(2, params[1]);
			stmt.setString(3, params[2]);

			stmt.executeUpdate();

			System.out.println(role.getIdRole() + " a bien été ajouté");

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Impossible de modifier un role");
			new SDialog("Echec", "La modification n'a pas reussie car " + e, "ok", "").setVisible(true);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param t
	 */
	@Override
	public void delete(Object t) {
		
		Role role = (Role) t;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement("DELETE FROM `roles` WHERE `IdRole`=?",
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, role.getIdRole());
			stmt.execute();

			System.out.println(role.getIdRole() + " a bien été Supprimé");

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
	 * @param idSearch
	 * @return String
	 */
	public String getRoleNomByIdRole(String idSearch) {

		String nom = "";
		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "SELECT RoleNom FROM `ROLES` WHERE IdRole=?";

		try {

			conn = ConnectionBdd.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, idSearch);

			System.out.println("Voici les informations du role " + idSearch);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				nom = res.getString("RoleNom");

			}

			res.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		return nom;
	}
}
