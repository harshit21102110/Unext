package caseS;

public class Doner extends Person {

	private String bloodBankName ;
	private String donerType ;
	private String donationDate ;
	public String getBloodBankName() {
		return bloodBankName;
	}
	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}
	public String getDonerType() {
		return donerType;
	}
	public void setDonerType(String donerType) {
		this.donerType = donerType;
	}
	public String getDonationDate() {
		return donationDate;
	}
	public void setDonationDate(String donationDate) {
		this.donationDate = donationDate;
	}
	public void displayDonationDetails() {
		
		System.out.println("--------------Donation Details--------------- ");
		System.out.println("Name= "+getName());
		System.out.println("DateOfBirth= "+getDateOfBirth());
		System.out.println("gender= "+getGender());
		System.out.println("mobileNumber= "+getMobileNumber());
		System.out.println("Blood Group= "+getBloodGroup());
		System.out.println("Blood Bank Name= "+getBloodBankName());
		System.out.println("Doner Type= "+getDonerType());
		System.out.println("Donation Type= " + getDonationDate());
 
	}
	
}
