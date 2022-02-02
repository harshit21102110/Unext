package ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class session {
	private dataSource ds;
	private Connection conn;

	public void showReviews() {

		try {
			Scanner scanner = new Scanner(System.in);
			showProductdetails();

			System.out.println("Enter the Product ID to see the reviers");
			String productId = scanner.next();

			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}
			PreparedStatement statement = conn.prepareStatement("Select * from reviews where productId=?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			statement.setString(1, productId);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				System.out.println();
				System.out.println("No Reviews for this product ");
				System.out.println();
				return;
			}

			PreparedStatement statement2 = conn.prepareStatement("Select pName from product where productId=?");
			statement2.setString(1, productId);
			ResultSet rs2 = statement2.executeQuery();
			rs2.next();

			String pNmaeString = rs2.getString(1);
			System.out.println(pNmaeString);
			System.out
					.println(String.format("%20s %10s %20s %10s %70s", "User Id", "|", "Product Name", "|", "Review"));
			System.out.println(String.format("%s",
					"-----------------------------------------------------------------------------------------------------------------------------------------------------"));

			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(String.format("%20s %10s %20s %10s %50s", rs.getString(1), "|", pNmaeString, "|",
						rs.getString(3)));

			}

			System.out.println();

			ds.closePrepaerdStatement(statement2);
			ds.closePrepaerdStatement(statement);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while showing reviews " + e.getMessage());
		}

	}

	public void showProductdetails() {

		try {

			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}
			PreparedStatement statement = conn.prepareStatement("Select productId,pName,price,pType from product");

			System.out.println(String.format("%20s %10s %20s %10s %20s %10s %20s", "Product Id", "|", "Product Name",
					"|", "Price", "|", "Product Type"));
			System.out.println(String.format("%s",
					"-----------------------------------------------------------------------------------------------------------------------------------------------------"));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				System.out.println(String.format("%20s %10s %20s %10s %20s %10s %20s", rs.getString(1), "|",
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
