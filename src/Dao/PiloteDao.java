package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Interfaces.Dao;
import Models.Pilote;
import compAero.Services;

public class PiloteDao implements Dao<Pilote>{
	
	Services s = new Services();
	
	private List<Pilote> pilotes = new ArrayList<>();

	public List<Pilote> getPilotes() {
		return pilotes;
	}

	public void setPilotes(List<Pilote> pilotes) {
		this.pilotes = pilotes;
	}
	
	public PiloteDao() {
		Connection conn = null;
		Statement stmt = null;
		conn = s.getConn(conn);
		stmt = s.getStmt(stmt, conn);
		final String sql = "SELECT * FROM Pilote";
		
		Pilote p = null;
		
		try {
			final ResultSet rs = stmt.executeQuery(sql);		
			
			while (rs.next()) {
				/*
				   	private Integer idPilote;
					private String NomPilote;
					private String PrenomPilote;
				 */
								
				p = new Pilote();
				p.setIdPilote(rs.getInt(1));
				p.setNomPilote(rs.getString(2));
				p.setPrenomPilote(rs.getString(3));
				
				pilotes.add(p);
				
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
	public Optional<Pilote> get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pilote> getAll() {
		// TODO Auto-generated method stub
		return pilotes;
	}

	@Override
	public void save(Pilote p) {
				
		Connection conn = null;
		Statement stmt = null;
		
		conn = s.getConn(conn);
		stmt = s.getStmt(stmt, conn);
		
		final String sql = "INSERT INTO Pilote(idPilote,NomPilote,PrenomPilote) "
				+ "VALUES (LAST_INSERT_ID(),'" + p.getNomPilote() + "','" + p.getPrenomPilote() + "')";
		try {
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		p.setIdPilote(pilotes.size()+1);
		this.pilotes.add(p);
	}

	@Override
	public void update(Pilote t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Pilote t) {
		// TODO Auto-generated method stub
		
	}

}
