package ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class sellerHelper {
	Scanner sc = new Scanner(System.in);

	private dataSource ds;
	private Connection conn;
	private String checkUserString = "Select password from seller where sellerId =?";

	void showProduct(String sellerId) {
		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}

			PreparedStatement statement = conn.prepareStatement(
					"Select productId ,pName ,price ,stock,ptype from product where sellerId = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setString(1, sellerId);
			ResultSet rs = statement.executeQuery();

			System.out.println();
			if (rs.next() == false) {
				System.out.println("No Iterms are offered by the seller");
				return;
			}
			rs.beforeFirst();

			System.out.println(String.format("%20s %5s %20s %5s %20s %5s %20s %5s %20s", "productId", "|", "pName", "|",
					"Price", "|", "stock", "|", "Product Type"));
			System.out.println();
			while (rs.next()) {
				System.out.println(String.format("%20s %5s %20s %5s %20s %5s %20s %5s %20s", rs.getString(1), "|",
						rs.getString(2), "|", rs.getInt(3), "|", rs.getInt(4), "|", rs.getString(5)));

			}
			System.out.println();
			ds.closePrepaerdStatement(statement);

		} catch (Exception e) {

			// TODO: handle exception
			System.out.println("Cannot show product=> " + e.getLocalizedMessage());
		}
	}

	boolean checkSeller(String sellerId, String password) {
		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}

			PreparedStatement statement = conn.prepareStatement(checkUserString);
			statement.setString(1, sellerId);
			ResultSet rs = statement.executeQuery();
			if (!rs.next()) {
				System.out.println("Enter Correct User Id");
				return false;
			}

			if (!password.equals(rs.getString(1))) {
				System.out.println("Password Entered is Wrong");
				return false;
			}
			System.out.println("--------------------Successfully Logged In--------------");
			ds.closePrepaerdStatement(statement);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The user cannot be added " + e.getLocalizedMessage());
			return false;
		}

	}

	void addProduct(String sellerId) {

		try {
			showProduct(sellerId);

			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}

			System.out.println("Enter the Product Id: ");
			String pId = sc.next();
			sc.nextLine();
			sc.nextLine();
			System.out.println("Enter Product Name");
			String pName = sc.nextLine();
			sc.nextLine();
			System.out.println("Enter Product Price");
			int pPrice = sc.nextInt();
			System.out.println("Enter Stock for the product :");
			int pStock = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Type of Product :");
			String pType = sc.next();
			sc.nextLine();

			PreparedStatement statement = conn.prepareStatement("Insert into product values(?,?,?,?,?,?)");
			statement.setString(1, pId);
			statement.setString(2, pName);
			statement.setInt(3, pPrice);
			statement.setInt(4, pStock);
			statement.setString(5, pType);
			statement.setString(6, sellerId);

			statement.executeUpdate();

			System.out.println("Product sucessfully added");
			System.out.println();
			ds.closePrepaerdStatement(statement);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Product cannot be added =>" + e.getLocalizedMessage());
		}

	}

	void updatePrice(String sellerId) {
		showProduct(sellerId);
		System.out.println("Enter the product id: ");
		String pId = sc.next();

		System.out.println("Eneter the updated price: ");
		int price = sc.nextInt();

		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}

			PreparedStatement statement = conn
					.prepareStatement("Update product set price = ? where sellerId = ? AND productId=?");
			statement.setInt(1, price);
			statement.setString(2, sellerId);
			statement.setString(3, pId);

			statement.executeUpdate();
			System.out.println("Price Sucessfully Changed");
			System.out.println();
			ds.closePrepaerdStatement(statement);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Price Cannot be updated =>" + e.getLocalizedMessage());
		}

	}

	void updateQuantity(String sellerId) {
		showProduct(sellerId);

		System.out.println("Enter the product id: ");
		String pId = sc.next();

		System.out.println("Eneter the updated Stock: ");
		int stock = sc.nextInt();

		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}

			PreparedStatement statement = conn
					.prepareStatement("Update product set stock = ? where sellerId = ? AND productId=?");
			statement.setInt(1, stock);
			statement.setString(2, sellerId);
			statement.setString(3, pId);

			statement.executeUpdate();
			System.out.println("Stock successfully added");
			System.out.println();
			ds.closePrepaerdStatement(statement);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Price Cannot be updated =>" + e.getLocalizedMessage());
		}

	}

	void addSeller(seller user) {
		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}
			PreparedStatement statement = conn.prepareStatement("Insert into seller values(?,?,?,?,?)");
			statement.setString(1, user.getSellerId());
			statement.setString(2, user.getSellerName());
			statement.setString(3, user.getAddress());
			statement.setInt(4, user.getPhoneNumber());
			statement.setString(5, user.getPassword());
			statement.executeUpdate();

			ds.closePrepaerdStatement(statement);

			System.out.println("User sucesfully added");
			return;

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("The user cannot be added " + e.getLocalizedMessage());
			return;
		}
	}

}
