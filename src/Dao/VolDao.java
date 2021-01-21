package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Interfaces.Dao;

import Models.Vol;
import compAero.Services;

public class VolDao implements Dao<VolDao> {
	
	Services s = new Services();
	
	private List<Vol> vols = new ArrayList<>();
	
	public List<Vol> getVols() {
		return vols;
	}

	public void setVols(List<Vol> vols) {
		this.vols = vols;
	}
	
	public int getRowCountVols() {
		return vols.size();
	}
	
	public List<Vol> getVolInVols(String searchedVol) {
		
		Predicate<Vol> volfind = vol -> vol.getNumVol().equals(searchedVol);
		
		return vols.stream().filter(volfind).collect(Collectors.toList());
	}
	
	public VolDao() {

		super();
		Connection conn = null;
		Statement stmt = null;
		conn = s.getConn(conn);
		stmt = s.getStmt(stmt, conn);	
		
	
		final String sql = "SELECT Vol.NumVol,(Select NomAeroprt from Aeroport WHERE Aeroport.idAeroport=Vol.AeroportDept) as AeroportDept,Vol.HDepart,(Select NomAeroprt from Aeroport WHERE Aeroport.idAeroport=Vol.AeroportArr) as AeroportArr,Vol.HArrivee FROM Vol";
		
		
		Vol vol = null;
		
		try {
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				/*
				 	private String numVol;
					private String aeroportDept;
					private LocalTime hDepart;
					private String aeroportArr;
					private LocalTime hArrivee; 
				 */
				vol = new Vol();
				
				vol.setNumVol(rs.getString(1));
				vol.setAeroportDept(rs.getString(2));
				vol.sethDepart(rs.getString(3));	
				vol.setAeroportArr(rs.getString(4));
				vol.sethArrivee(rs.getString(5));

				this.vols.add(vol);
			}

			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("nb enr in vols on constructor:" +
		this.vols.size());
	}
	@Override
	public Optional<VolDao> get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VolDao> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(VolDao t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(VolDao v, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(VolDao v) {
		// TODO Auto-generated method stub
		
	}



}
