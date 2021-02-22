package dao;

import connexion.ConnectionBdd;
import interfaces.Dao;
import java.sql.*;
import java.util.ArrayList;
import models.*;
public class DetailAvionDao implements Dao {

  /**
   * @return ArrayList<DetailAvion>
   */
  public ArrayList<DetailAvion> getAll() {

    Connection conn = null;
    PreparedStatement stmt = null;

    String sql = "SELECT * FROM `DETAILAVION`";

    ArrayList<DetailAvion> listeDetailAvion = new ArrayList<>();

    try {

      conn = ConnectionBdd.getConnection();
      stmt = conn.prepareStatement(sql);

      ResultSet res = stmt.executeQuery(sql);

      while (res.next()) {

        DetailAvion detailAvion = new DetailAvion(res.getString("TypeAvion"),
            res.getInt("Capacite"), res.getInt("IdConstructeur"));

        listeDetailAvion.add(detailAvion);

      }

      res.close();
      conn.close();

    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("Impossible d'afficher les details avions");

    }

    return listeDetailAvion;
  }
  
    @Override
  public ArrayList<DetailAvion> search(Object id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object get(Object id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void save(Object t, String[] params) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(Object t, String[] params) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(Object t) {
    // TODO Auto-generated method stub

  }

}
