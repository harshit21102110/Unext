package caseS;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
	    
		Doner d= new Doner();
		
		String name = sc.next();
		d.setName(name);
		String dateOfBirth = sc.next();
		d.setDateOfBirth(dateOfBirth);
		String gender = sc.next();
		d.setGender(gender);
		String mobileNumber = sc.next();
		d.setMobileNumber(mobileNumber);
		String bloodGroup = sc.next();
		d.setBloodGroup(bloodGroup);
		String bloodBankName = sc.next();
		d.setBloodBankName(bloodBankName);
		String donerType = sc.next();
		d.setDonerType(donerType);
		String donationDate = sc.next();
		d.setDonationDate(donationDate);
		
		
		d.displayDonationDetails();
		
		
		
		
		
	}

}
