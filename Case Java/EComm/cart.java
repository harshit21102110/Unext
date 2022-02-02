package ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class cart {

	List<cartItems> userCart;

	private int cartValue;

	cart() {
		userCart = new ArrayList<cartItems>();
		this.cartValue = 0;
	}

	int checkIntemInCart(String productId) {

		for (int i = 0; i < userCart.size(); i++) {
			if (userCart.get(i).getProductId().equals(productId)) {
				return i;
			}
		}

		return -1;
	}

	void addToCart(String userId, String productId) {

		// Check if the item is available in stock

		session s = new session();

		int stock = s.checkStock(productId);

		if (stock == -1)
			return;
		if (stock == 0) {
			System.out.println("Item not in stock");
			return;
		}

		cartItems c = s.getProductDeatils(productId);

		int itemIndex = checkIntemInCart(productId);

		if (itemIndex >= 0) {
			if (stock < userCart.get(itemIndex).getQuantity() + 1) {
				System.err.println("Item is out of stock");
				System.out.println();
				return;
			}

			userCart.get(itemIndex).setQuantity(userCart.get(itemIndex).getQuantity() + 1);
			this.cartValue += userCart.get(itemIndex).getPrice();

		} else {

			cartItems add = new cartItems();
			add.setUserId(userId);
			add.setProductId(productId);
			add.setProductName(c.getProductName());
			add.setPrice(c.getPrice());
			add.setQuantity(1);

			userCart.add(add);

			this.cartValue += c.getPrice();
		}
		System.out.println();
		System.out.println("The Item Sucessfully added to the cart");
		System.out.println();
	}

	void removeFromCart(String productId) {
		System.out.println();

		int itemIndex = checkIntemInCart(productId);

		if (itemIndex == -1) {
			System.out.println("The Product doesnt exists in the cart");
			return;
		}

		cartItems delCart = new cartItems();

		delCart = userCart.get(itemIndex);
		this.cartValue -= delCart.getPrice();
		if (delCart.getQuantity() == 1) {

			userCart.remove(itemIndex);

			System.out.println("The Item is removed from the cart");
			return;
		} else {

			userCart.get(itemIndex).setQuantity(userCart.get(itemIndex).getQuantity() - 1);
			System.out.println("The Item Quantity removed from cart");
		}
		System.out.println();
	}

	void displayCart() {
		System.out.println(String.format("%25s %5s %25s %5s %25s %5s %25s", "Product Id", "|", "Product Name", "|",
				"Price", "|", "Quantity"));
		System.out.println(String.format("%s",
				"-----------------------------------------------------------------------------------------------------------------------------------------------------"));
		for (cartItems i : userCart) {

			System.out.println(String.format("%25s %5s %25s %5s %25s %5s %25s", i.getProductId(), "|",
					i.getProductName(), "|", i.getPrice(), "|", i.getQuantity()));

		}
		System.out.println();
		System.out.println("The total cart value is => " + this.cartValue);

	}

	void orderProducts() {
		try {
			session s = new session();
			dataSource ds = new dataSource();
			Connection conn = ds.getConnection();

			for (cartItems i : userCart) {

				PreparedStatement statement = conn.prepareStatement(
						"Insert into orders(userId,productId,productName,price,quantity) values(?,?,?,?,?)");
				int currStock = s.checkStock(i.getProductId());
				PreparedStatement statement2 = conn.prepareStatement("update product set stock=? where productId=?");
				statement2.setInt(1, currStock - i.getQuantity());
				statement2.setString(2, i.getProductId());
				statement.setString(1, i.getUserId());
				statement.setString(2, i.getProductId());
				statement.setString(3, i.getProductName());
				statement.setInt(4, i.getPrice());
				statement.setInt(5, i.getQuantity());

				statement.executeUpdate();
				statement2.executeUpdate();
				statement2.close();
				statement.close();
			}
			System.out.println();
			System.out.println("Items Sucesfully Ordered");
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Cannot order the produvts = > " + e.getLocalizedMessage());
		}

	}
}