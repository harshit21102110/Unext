package ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userSession {

	private String addUserString = "Insert into user values(?,?,?,?)";
	private String addAddString = "Insert into address values(?,?,?,?,?,?)";
	private String checkUserString = "Select password from user where userId =?";
	private String deleteUserString = "delete from user where userId=?";
	private String deleteUserAddressString = "delete from address where userId=?";
	private String showUserDetailString = "select * from user where userId =?";
	private String showUserDetailAddressString = "select * from address where userId =?";
	private String showPastOrderString = "Select * from orders where userId=? order by dateOfOrder desc";

	private dataSource ds;
	private Connection conn;

	public boolean addUser(User user) {
		try {

			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}
			PreparedStatement statement = conn.prepareStatement(addUserString);
			statement.setString(1, user.getUserId());
			statement.setString(2, user.getName());
			statement.setInt(3, user.getPhoneNumber());
			statement.setString(4, user.getPassword());
			statement.executeUpdate();

			ds.closePrepaerdStatement(statement);

			statement = conn.prepareStatement(addAddString);
			statement.setString(1, user.getUserId());
			statement.setString(2, user.getLocality());
			statement.setString(3, user.getState());
			statement.setString(4, user.getCountry());
			statement.setInt(5, user.getCreditCard());
			statement.setInt(6, user.getPinCode());
			statement.executeUpdate();
			ds.closePrepaerdStatement(statement);

			System.out.println("User sucesfully added");
			return true;

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("The user cannot be added " + e.getLocalizedMessage());
			return false;
		}

	}

	public boolean checkUser(String userId, String password) {

		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}

			PreparedStatement statement = conn.prepareStatement(checkUserString);
			statement.setString(1, userId);
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

	public boolean updateUser(String colString, String setString, int setInt, String userId, String table) {
		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}

			StringBuffer sb = new StringBuffer();
			sb.append("update ").append(table).append(" set ").append(colString).append(" =? where userId=? ");
			String updateUserString = sb.toString();

			PreparedStatement statement = conn.prepareStatement(updateUserString);

			if (setString == null) {

				statement.setInt(1, setInt);
				statement.setString(2, userId);

			} else {

				statement.setString(1, setString);
				statement.setString(2, userId);

			}

			statement.executeUpdate();

			ds.closePrepaerdStatement(statement);
			System.out.println("User sucesfully updated");
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The user cannot be updated " + e.getLocalizedMessage());
			return false;
		}

	}

	public void showUserDetails(String userId) {

		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}

			PreparedStatement statement = conn.prepareStatement(showUserDetailString);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			System.out.println(String.format("%55s", "User details"));
			System.out.println();
			System.out.println(
					String.format("%20s %10s %20s %10s %20s", "User Id", "|", "User Name", "|", "Phone Number"));
			System.out.println(
					"--------------------------------------------------------------------------------------------");
			while (rs.next()) {

				System.out.println(String.format("%20s %10s %20s %10s %20s", rs.getString(1), "|", rs.getString(2), "|",
						rs.getInt(3)));

			}

			statement = conn.prepareStatement(showUserDetailAddressString);
			statement.setString(1, userId);
			rs = statement.executeQuery();
			System.out.println();
			System.out.println(String.format("%90s", "Address details"));
			System.out.println();
			System.out.println(String.format(" %20s %5s %20s %5s %20s %5s %20s %5s %20s %5s %20s", "User Id", "|",
					"Locality", "|", "State", "|", "Country", "|", "Credit Card Info", "|", "Pin Code"));
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				System.out.println(
						String.format("%20.5s %5.5s %20.5s %5.5s %20.5s %5.5s %20.5s %5.5s %20.5s %5.5s %20.5s",
								rs.getString(1), "|", rs.getString(2), "|", rs.getString(3), "|", rs.getString(4), "|",
								rs.getInt(5), "|", rs.getInt(6)));
			}

			ds.closePrepaerdStatement(statement);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("The user cannot be showed " + e.getLocalizedMessage());

		}

	}

	public void showPastOrders(String userId) {

		try {
			if (ds == null) {
				ds = new dataSource();
			}
			if (conn == null) {
				conn = ds.getConnection();
			}

			PreparedStatement statement = conn.prepareStatement(showPastOrderString, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			statement.setString(1, userId);

			ResultSet rs = statement.executeQuery();
			if (!rs.next()) {
				System.out.println("No Past Orders");
				return;
			}
			rs.beforeFirst();
			System.out.println(String.format("%30s %25s %10s %25s %10s %23s %10s  %25s %10s %25s %10s", "User Id", "|",
					"Product Id", "|", "Product Name", "|", "Price", "|", "Quantity", "|", "Date of Order"));
			System.out.println(String.format("%s",
					"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
			while (rs.next()) {
				System.out.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getInt(4)
						+ "|" + rs.getInt(5) + "|" + rs.getString(6));
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cannot show past orders => " + e.getLocalizedMessage());
		}

	}
}
