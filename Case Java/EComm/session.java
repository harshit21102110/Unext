package ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class session {
	private dataSource ds;
	private Connection conn;

	public void showProductdetails() {

		try {

			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}
			PreparedStatement statement = conn.prepareStatement("Select productId,pName,price,pType from product");

			System.out.println(String.format("%12s %23s %10s %23s %25s %25s %10s", "Product Id", "|", "Product Name",
					"|", "Price", "|", "Product Type"));
			System.out.println(String.format("%s",
					"-----------------------------------------------------------------------------------------------------------------------------------------------------"));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				System.out.println(String.format("%10s %25s %10s %25s %25s %25s %10s", rs.getString(1), "|",
						rs.getString(2), "|", rs.getInt(3), "|", rs.getString(4)));

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cannot show product details => " + e.getLocalizedMessage());
		}

	}

	public int checkStock(String productId) {
		try {

			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}
			PreparedStatement statement = conn.prepareStatement("Select stock from product where productId =?");
			statement.setString(1, productId);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				System.out.println("Enter Correct Product Id ");
				return -1;
			}
			return rs.getInt(1);

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Cannot get Stock => " + e.getLocalizedMessage());
			return -1;

		}
	}

	public cartItems getProductDeatils(String productId) {

		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}
			PreparedStatement statement = conn
					.prepareStatement("Select productId , price ,pName  from product where productId =?");
			statement.setString(1, productId);
			ResultSet rs = statement.executeQuery();
			rs.next();
			cartItems cItems = new cartItems();

			cItems.setProductId(rs.getString(1));
			cItems.setPrice(rs.getInt(2));
			cItems.setProductName(rs.getString(3));
			return cItems;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cannot get details => " + e.getLocalizedMessage());
			return null;
		}

	}

	void addReview(String userId, String productId, String review) {
		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}

			PreparedStatement statement = conn.prepareStatement("Insert into reviews values(?,?,?)");
			statement.setString(1, userId);
			statement.setString(2, productId);
			statement.setString(3, review);

			statement.executeUpdate();
			ds.closePrepaerdStatement(statement);
			System.out.println("Review sucessfully posted");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cannot post reviews => " + e.getLocalizedMessage());
		}

	}
}
