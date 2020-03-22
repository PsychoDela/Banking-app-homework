import java.io.IOException;
import java.util.Scanner;

public class Main 
{
	static Scanner sc = new Scanner (System.in);
	
	public static void main(String[] args) 
	{
		try
		{
			User.importData();
		}
		catch(IOException e)
		{
			System.out.println("Import data failed.");
		}
		finally
		{
			start();
		}
	}
		
	public static void start()
	{
		int choice;
		
		System.out.println("\n*-*-* Bank Of Dela *-*-*");
		System.out.println("1) Create account\n2) Money Transfer\n3) ATM\n4) More options\n5) Exit");
		System.out.println("\nWhen exiting make sure you don't suddenly close the program or the files won't be saved.\n");
		
		choice = sc.nextInt();
		
		if (choice < 1 || choice > 5)
		{
			System.out.println("\nInvalid entry. Please try again!\n");
			start();
		}
		
		switch (choice)
		{
			case 1:
				signUp();
				break;
				
			case 2:
				transfer();
				break;
				
			case 3:
				ATM();
				break;
				
			case 4:
				options();
				break;
				
			case 5:
				exit();
				break;
		}
	}
	
	public static void signUp()
	{
		String name;
		int accNumber;
		double balance;
		
		System.out.println("Enter your name: ");
		sc.nextLine();
		name = sc.nextLine();
		
		System.out.println("Enter an account number:");
		accNumber = sc.nextInt();
		
		System.out.println("Enter the base amount on your account:");
		balance = sc.nextInt();
		
		User account = new User (name , accNumber , balance);
		
		if (account.isCreated() == true)
		{
			System.out.println("Account successfully created!");
		}
		
		start();
	}
	
	public static void transfer()
	{
		int srcAcc;
		int tgtAcc;
		int money;
		
		System.out.println("Enter source account number: ");
		srcAcc = sc.nextInt();
		
		System.out.println("Enter target account number: ");
		tgtAcc = sc.nextInt();
		
		System.out.println("Enter amount of money to be transfered: ");
		money = sc.nextInt();
		
		User.transfer(srcAcc, tgtAcc, money);
		Main.start();
	}
	
	public static void ATM()
	{
		int accNum;
		int money;
		
		System.out.println("*-*-* ATM *-*-*");
		System.out.println("Enter account number: ");
		accNum = sc.nextInt();
		
		System.out.println("Enter amount of money to be withdrawn: ");
		money = sc.nextInt();
		
		User.ATM(accNum , money);
		Main.start();
	}
	
	public static void options()
	{
		int choice;
		
		System.out.println("1) Account details\n2) Log file (requires master-key)");
		choice = sc.nextInt();
		
		if (choice < 1 || choice > 2)
		{
			System.out.println("Invalid entry. Please try again!");
			options();
		}
		
		else if (choice == 1)
		{
			int accNum;
			
			System.out.println("Enter account number: ");
			accNum = sc.nextInt();
			
			User.details(accNum);
			Main.start();
		}
		
		else if (choice == 2)
		{
			int masterKey;
			
			System.out.println("Enter master-key: ");
			masterKey = sc.nextInt();
			User.printLog(masterKey);
			Main.start();
		}
	}
	
	public static void exit()
	{
		try
		{
			User.exportDataStage1();
		}
		catch(Exception e)
		{
			System.out.println("Save failed.");
		}
		finally
		{
			System.out.println("Program terminated.");
			System.exit(0);
		}
	}
}
