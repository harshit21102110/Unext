package ecommerce;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		User user = new User();

//		user.setUserId("dddd");
//		user.setName("ddd");
//		user.setPassword("sdd");
//		user.setPhoneNumber(12234);
//		user.setCountry("ddd");
//		user.setCreditCard(1233);
//
//		s.addUser(user);
//
//		s.showUserDetails("dddd");

		cart c = new cart();
		c.addToCart("dddd", "E01");
		c.displayCart();
		c.addToCart("dddd", "E01");
		c.addToCart("dddd", "E02");

		userSession session = new userSession();
		session.showPastOrders("ddd");
	}

}
