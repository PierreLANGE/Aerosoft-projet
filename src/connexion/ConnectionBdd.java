package connexion;

import java.sql.*;

public class ConnectionBdd {

	private static final String driver_name = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/aerosoft";
	private static final String username = "root";
	private static final String password = "";

	public static Connection getConnection() {

		try {

			Class.forName(driver_name);
			return DriverManager.getConnection(url, username, password);

		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}


	/**
	 * @param stmt
	 */
	@SuppressWarnings("unused")
	private static void close(PreparedStatement stmt) {

		if (stmt != null) {

			try {
				stmt.close();

			} catch (SQLException e) {
				
				throw new RuntimeException(e);

			}
		}
	}
}
