package ecommerce;

import java.util.Scanner;

public class userHelper {
	Scanner sc = new Scanner(System.in);

	void cartEnter(String userId) {
		cart c = new cart();
		char lo = 'n';
		session s = new session();
		while (lo == 'n') {
			System.out.println();
			System.out.println(String.format("%100s", "--------------Your Current Cart---------------"));
			c.displayCart();
			System.out.println();

			System.out.println("Enter 1 if want to add Items cart");
			System.out.println("Enetr 2 if want to remove form cart");
			System.out.println("Enetr 3 if want to order item in cart");
			System.out.println("Enter 4 to exit the cart without placing order");

			int choice = sc.nextInt();

			if (choice == 1) {
				s.showProductdetails();
				System.out.println("Enetr the Product Id which you want to add: ");
				String pid = sc.next();
				c.addToCart(userId, pid);
			} else if (choice == 2) {
				c.displayCart();
				System.out.println("Enter Product Id you want to remove: ");
				String pid = sc.next();
				c.displayCart();
				c.removeFromCart(pid);

			} else if (choice == 3) {
				c.orderProducts();
				lo = 'y';
			} else {
				lo = 'y';
			}

		}

	}

	void update(String userId) {
		userSession session = new userSession();
		System.out.println("Press 1 to update name");
		System.out.println("Press 2 to change Password");
		int choice = sc.nextInt();
		if (choice == 1) {
			System.out.println("Enter updated name: ");
			sc.nextLine();
			String nameString = sc.nextLine();
			session.updateUser("name", nameString, choice, userId, "user");
		} else {
			System.out.println("Enter updated password: ");
			String nameString = sc.next();
			sc.nextLine();
			session.updateUser("password", nameString, choice, userId, "user");
		}
	}

	void reviews(String userId) {
		session s = new session();
		s.showProductdetails();
		System.out.println("Enter Product id to review");
		String pid = sc.next();
		sc.nextLine();
		System.out.println("Write a review: ");
		String review = sc.nextLine();
		s.addReview(userId, pid, review);

	}
}
