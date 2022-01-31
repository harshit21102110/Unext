package ecommerce;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 if you want to log-in as User");
		System.out.println("Press 2 if you want to log-in as Seller");

		int choice = sc.nextInt();
		char exitConsole = 'n';
		do {
			switch (choice) {
			case 1:
				userLogin sLogin = new userLogin();
				sLogin.userEnter();
			case 2:
			default:
				System.out.println("Enter Correct Choice");
			}

			System.out.println("Do you Want to exit Console (y/n)");
			exitConsole = sc.next().charAt(0);

		} while (exitConsole == 'n');

	}

}
