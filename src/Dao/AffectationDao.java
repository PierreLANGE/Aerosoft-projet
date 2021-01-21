package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import Interfaces.Dao;
import Models.Aeroport;
import Models.Affectation;
import Models.Pilote;
import compAero.Services;

public class AffectationDao implements Dao<Affectation> {

	Services s = new Services();

	private List<Affectation> affectations = new ArrayList<>();

	public List<Affectation> getAffectations() {
		return affectations;
	}

	public void setAffectations(List<Affectation> affectations) {
		this.affectations = affectations;
	}

	public AffectationDao() {

		super();
		Connection conn = null;
		Statement stmt = null;
		conn = s.getConn(conn);
		stmt = s.getStmt(stmt, conn);
		final String sql = "SELECT " + "Affectation.NumVol," + "Affectation.DateVol," + "Affectation.NumAvion, "
				+ "Affectation.idPilote, "
				+ "(Select PrenomPilote from Pilote WHERE  Pilote.idPilote = Affectation.idPilote) as PrenomPilote,"
				+ "(Select NomPilote from Pilote WHERE  Pilote.idPilote = Affectation.idPilote) as NomPilote "
				+ "from Affectation";

		Affectation affectation = null;

		try {
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				affectation = new Affectation();

				affectation.setNumVol(rs.getString(1));
				affectation.setDateVol(rs.getDate(2));
				affectation.setNumAvion(rs.getString(3));

				Pilote p = new Pilote();

				p.setIdPilote(rs.getInt(4));
				p.setPrenomPilote(rs.getString(5));
				p.setNomPilote(rs.getString(6));

				affectation.setPilote(p);

				this.affectations.add(affectation);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("nb enr in affectations on constructor:" +
		// this.affectations.size());
	}

	@Override
	public Optional<Affectation> get(long id) {

		return Optional.ofNullable(affectations.get((int) id));
	}

	@Override
	public List<Affectation> getAll() {
		return affectations;
	}

	@Override
	public void save(Affectation affectation) {
		affectations.add(affectation);
	}

	@Override
	public void update(Affectation affectation, String[] params) {

		affectation.setDateVol(java.sql.Date.valueOf(params[0]));

		Connection conn = null;
		Statement stmt = null;
		conn = s.getConn(conn);
		stmt = s.getStmt(stmt, conn);

		Pilote p = affectation.getPilote();

		String requete = "UPDATE Affectation SET DateVol='" + affectation.getDateVol() + "' WHERE NumVol='"
				+ affectation.getNumVol() + "' and NumAvion='" + affectation.getNumAvion() + "'" + " and idPilote="
				+ p.getIdPilote();

		System.out.println("requete on update AffectationDao: " + requete);

		try {
			
			stmt.executeUpdate(requete);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void delete(Affectation affectation) {
		Connection conn = null;
		Statement stmt = null;
		conn = s.getConn(conn);
		stmt = s.getStmt(stmt, conn);

		Pilote p = affectation.getPilote();

		String requete = "delete from Affectation WHERE NumVol='" + affectation.getNumVol() + "' and NumAvion='"
				+ affectation.getNumAvion() + "'" + " and idPilote=" + p.getIdPilote();

		System.out.println("requete on update AffectationDao: " + requete);

		try {
			stmt.executeUpdate(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		affectations.remove(affectation);
	}

}
