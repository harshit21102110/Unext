package Assignment;

import java.util.Scanner;

 
public class Assignment {
	
	static Scanner sc = new Scanner(System.in);

	static void findEvenOrOdd(int n) {
		
		if(n%2==0) {
			System.out.println("Then nuber is even ");
		}
		else 
			System.out.println("The Number is Odd");
		
	}
	
	static void swapNumbers()
	{
		
		int n = sc.nextInt();
		int m =sc.nextInt();
		n=n+m;
		m=n-m;
		n=n-m;
		
		System.out.println("The Swapped Numbers are= "+n+" and "+m);
		
	}
	
	static void findGreatest() {
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();

		if(a>b && a>c)
		{
			System.out.println("The Greatest Number is "+a);
		}
		else if (b>a && b>c) {
			System.out.println("The Greates Number is"+b);
		}else {
			System.out.println("The Greatest Number is"+c);
		}
	
		
		
	}
	
	static void checkVowel() {
	char a = sc.next().charAt(0);
	
	a=Character.toLowerCase(a);
	
	// using if else 
	
	if(a=='a'||a=='e'||a=='i'||a=='o'||a=='u') System.out.println("The Entered number is a vowel");
	else 
		System.out.println("The Entered number is a Consonant");
	
	// using switch 	
	switch (a) {
	case 'a' :
	case 'e' :
	case 'i' :
	case 'o' :
	case 'u' : System.out.println("The enterd number is a vowel");break;
	default : System.out.println("The Entered number is a consonant");break;
	}
}
	
	static void printEven() {
		for(int i=1;i<=50;i++) {
			if(i%2==0)System.out.println(i);
		}
	}
	
	
	static void printOdd() {
		for(int i=50;i<=100;i++) {
			if(i%2!=0)System.out.println(i);
		}
	}
	
	static void printEvenSum() {
		int n = sc.nextInt();
		int sum =0;
		for(int i=1;i<=n;i++) {
			if(i%2==0) sum+=i;
		}
		System.out.println(sum);
	}
	
	static void printPatterns() {
		int n = sc.nextInt();
	
		// Pattern 1 
		
		for(int i=1;i<=n;i++) {
			for(int j =1;j<=i;j++) {
				System.out.print(i);
			}
		System.out.println();
		}
		
		// Pattern 2
		System.out.println(); 
		
		for(int i =0; i<n;i++) {
			for(int j=0;j<n;j++)System.out.print(n);
			
			System.out.println();
		}
		System.out.println(); 
		// pattern3 
		
		for(int i=1;i<=n;i++) {
			
			for(int j=1;j<=n-i;j++) {
				System.out.print(" ");
			}
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}
			
			System.out.println();
		}
		System.out.println(); 
		
	}
	
	static void reverseArray() {
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int k=0;k<n;k++)
			arr[k]= sc.nextInt();
			
		int i =0;
		int j =n-1;
		while(i<j) {
			arr[i]=arr[i]+arr[j];
			arr[j]=arr[i]-arr[j];
			arr[i]=arr[i]-arr[j];
			i++;j--;
		}
		
		for(  int k : arr) {
			System.out.print(k+" ");
		}
		
		System.out.println();
		
	}
	
	static int fact(int n) {
		if(n==1) return n;
		
		return n*fact(n-1);
	}
	
	static boolean isPrime(int n) {
		 
		 if (n <= 1)
		 {
			  return true ;
		 }
	        if (n <= 3)
	        {
				  return true;
			 }
	        if (n % 2 == 0 || n % 3 == 0)
	             {
	   			 return false ;
	   		 }
	        for (int i = 5; i * i <= n; i = i + 6)
	            if (n % i == 0 || n % (i + 2) == 0)
	            {
	   			  return false;
	   		 }
	  
	         
				 return true;
			 
	}
	
	
	static void printPrime() {
		
		int n =sc.nextInt();
		
		for(int i=2;i<=n;i++) {
			if(isPrime(i)) {
				System.out.print(i+" ");
			}
		}
		System.out.println();
		
	}
	
	static void swapArray() {
		
		int n = sc.nextInt();
		
		int arr[] = new int[n];
		for(int i =0;i<n;i++) {
			arr[i]=sc.nextInt();
		}
	for(int i =0;i<n-1;i+=2) {
		arr[i]=arr[i]+arr[i+1];
		arr[i+1]=arr[i]-arr[i+1];
		arr[i]=arr[i]-arr[i+1];
		 
	}
	
	for(int i :arr) {
		System.out.print(i+" ");
	}
	System.out.println();
		
	}
	
	static void printReverse() {
		
		int n = sc.nextInt();
		
		int ans =0;
		while(n>0) {
			ans=ans*10+n%10;
			n=n/10;
			
		}
		
		System.out.println(ans);
		
		
	}
	
 	public static void main(String[] args) {
	 
  		findEvenOrOdd(11);
  		swapNumbers() ;
  		swapArray();
 		findGreatest();
        checkVowel();
        printEven();
        printOdd();
        printEvenSum();
        printPatterns();
        reverseArray();
        System.out.println(fact(3));
        System.out.println(isPrime(5));
        printPrime();
        swapArray();
        printReverse();
        
	}

}
