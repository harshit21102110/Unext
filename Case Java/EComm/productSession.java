package ecommerce;

import java.util.Scanner;

public class productSession {

	Scanner sc = new Scanner(System.in);

	void userEnter() {
		char us = 'n';
		do {
			String userIdString = null;
			sellerHelper uh = new sellerHelper();
			session s = new session();
			System.out.println("Press 1 to Login as a Seller");
			System.out.println("Press 2 to ro Register");
			int choice = sc.nextInt();
			if (choice == 1) {
				System.out.println("Enter User-Name");
				String userName = sc.next();
				System.out.println("Enter Password");
				String passString = sc.next();
				boolean check = uh.checkSeller(userName, passString);
				if (check == false) {
					continue;
				} else {
					// User loged in
					userIdString = userName;

					int ls = 1;

					while (ls != 6) {
						System.out.println("Press 1 to Add Product");
						System.out.println("Press 2 to Update Price");
						System.out.println("Press 3 to Update Quantity ");
						System.out.println("Press 4 to Log-Out");

						int userLoginChoice = sc.nextInt();
						switch (userLoginChoice) {
						case 1:
							uh.addProduct(userIdString);
							break;
						case 2:
							uh.updatePrice(userIdString);
							break;
						case 3:
							uh.updateQuantity(userIdString);
							break;
						case 4:
							ls = 6;
							break;
						default:
							System.out.println("Enter correct choice");
							break;
						}

					}
				}
			} else if (choice == 2) {

				seller user = new seller();
				System.out.print("Enter UserId: ");
				user.setSellerId(sc.next());
				sc.nextLine();
				System.out.println("Enter Name: ");
				user.setSellerName(sc.nextLine());
				System.out.println("Enter Phone Number: ");
				user.setPhoneNumber(sc.nextInt());
				System.out.println("Enter Password : ");
				user.setPassword(sc.next());
				sc.nextLine();
				System.out.println("Enter Address: ");
				user.setAddress(sc.nextLine());

				uh.addSeller(user);
				System.out.println("Log-In to enter");
				continue;

			}

			System.out.println("If wanted to go back press y ,else press any key");

			us = sc.next().charAt(0);
		} while (us != 'y');
	}

}
