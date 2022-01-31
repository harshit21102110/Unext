package ecommerce;

import java.util.Scanner;

public class userLogin {

	Scanner sc = new Scanner(System.in);

	void userEnter() {
		char us = 'n';
		do {
			String userIdString = null;
			userSession uSession = new userSession();
			session s = new session();
			System.out.println("Press 1 to Login");
			System.out.println("Press 2 to SignUp");
			System.out.println("Press 3 to view Products");

			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("Enter User-Name");
				String userName = sc.next();
				System.out.println("Enter Password");
				String passString = sc.next();

				boolean check = uSession.checkUser(userName, passString);

				if (check == false) {
					continue;
				} else {
					// User loged in
					userIdString = userName;
					userHelper uh = new userHelper();
					int ls = 1;

					while (ls != 6) {
						System.out.println("Press 1 to Shop Products");
						System.out.println("Press 2 to Update Details");
						System.out.println("Press 3 to View Previous Orders ");
						System.out.println("Press 4 to Add Reviews");
						System.out.println("Press 5 to View User Details");
						System.out.println("Press 6 to Log-Out");

						int userLoginChoice = sc.nextInt();
						switch (userLoginChoice) {
						case 1:
							uh.cartEnter(userIdString);
							break;
						case 2:
							uh.update(userIdString);
							break;
						case 3:
							uSession.showPastOrders(userIdString);
							break;
						case 4:
							uh.reviews(userIdString);
							break;
						case 5:
							uSession.showUserDetails(userIdString);
						case 6:
							ls = 6;
							break;
						default:
							System.out.println("Enter correct choice");
							break;
						}

					}
				}
			} else if (choice == 2) {

				User user = new User();
				System.out.print("Enter UserId: ");
				user.setUserId(sc.next());
				sc.nextLine();
				System.out.println("Enter Name: ");
				user.setName(sc.nextLine());
				System.out.println("Enter Phone Number: ");
				user.setPhoneNumber(sc.nextInt());
				System.out.println("Enter Password : ");
				user.setPassword(sc.next());
				sc.nextLine();
				System.out.println("Enter Locality: ");
				user.setLocality(sc.nextLine());
				System.out.println("Enter State: ");
				user.setState(sc.next());
				sc.nextLine();
				System.out.println("Enter Country: ");
				user.setCountry(sc.next());
				sc.nextLine();
				System.out.println("Enter card Details: ");
				user.setCreditCard(sc.nextInt());
				sc.nextLine();
				System.out.println("Enter Pin Code: ");
				user.setPinCode(sc.nextInt());
				sc.nextLine();

				uSession.addUser(user);
				System.out.println("Log-In to enter");
				continue;

			} else if (choice == 3) {
				s.showProductdetails();
			} else {
				System.out.println("Enter correct choice");
			}

			System.out.println("If wanted to go back press y ,else press any key");

			us = sc.next().charAt(0);
		} while (us != 'y');
	}

}
