package ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class dataSource {

	private Connection connection;

	Connection getConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomm", "root", "demo");
			return connection;
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Connot make a connection ==>" + e.getLocalizedMessage());
		}
		return connection;
	}

	void closeConnection(Connection con) {

		try {

			if (con != null)
				con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cannot close the connection ===> " + e.getLocalizedMessage());

		}

	}

	public void closePrepaerdStatement(PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The statement cannot be closed ==>" + e.getLocalizedMessage());
		}

	}

}
