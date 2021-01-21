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
import compAero.Services;

public class AeroportDao implements Dao<Aeroport> {

	Services s = new Services();

	private List<Aeroport> aeroports = new ArrayList<>();

	public AeroportDao() {
		super();
		
		Connection conn = null;
		Statement stmt = null;
		
		conn = s.getConn(conn);
		stmt = s.getStmt(stmt, conn);
		
		final String sql = "SELECT * from Aeroport ORDER BY NomVilleDesservie asc";

		Aeroport aeroport = null;

		try {
			final ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				aeroport = new Aeroport();
				
	            /*final String idAeroport = rs.getString("idAeroport");
	            final String nomAeroprt = rs.getString("NomAeroprt");
	            final String nomVilleDesservie = rs.getString("NomVilleDesservie");*/

				aeroport.setIdAeroport(rs.getString(1));
				aeroport.setNomAeroport(rs.getString(2));
				aeroport.setNomVilleDesservie(rs.getString(3));

				aeroports.add(aeroport);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Optional<Aeroport> get(long id) {

		return Optional.ofNullable(aeroports.get((int) id));
	}

	@Override
	public List<Aeroport> getAll() {
		return aeroports;
	}

	@Override
	public void save(Aeroport aeroport) {
		aeroports.add(aeroport);
	}

	@Override
	public void update(Aeroport aeroport, String[] params) {

		aeroport.setIdAeroport(Objects.requireNonNull(params[0], "idAeroport cannot be null"));
		aeroport.setNomAeroport(params[1]);
		aeroport.setNomVilleDesservie(params[2]);

		aeroports.add(aeroport);
	}

	@Override
	public void delete(Aeroport aeroport) {
		aeroports.remove(aeroport);
	}

}
