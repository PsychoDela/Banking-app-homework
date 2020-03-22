import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class User 
{
	private String name;
	private int accNumber;
	private double balance;
	private boolean created;
	private static String log = "";
	final private static int masterKey = 134679;
	private static ArrayList <User> accounts = new ArrayList <User> ();
	private static String export = "";
	
	public User(String name, int accNumber, double balance) 
	{
		setName(name);
		setAccNumber(accNumber);
		setBalance(balance);
		export += (accNumber + " ");
		accounts.add(this);
		log += (Integer.toString(accNumber) + " created, ");
		setCreated(true);
	}
	
	public static void importData() throws FileNotFoundException
	{
		File data = new File("accNumbers.txt");
		File log1 = new File("log.txt");
		Scanner sc1 = new Scanner(log1);
		Scanner sc = new Scanner(data);
		
		if (log1.exists())
		{
			log += sc1.nextLine();
		}
		
		try
		{
			log1.createNewFile();
		}
		catch(Exception e)
		{
			
		}
		
		sc1.close();
		
		if (data.exists() == false)
		{
			try
			{
				data.createNewFile();
			}
			catch (IOException e)
			{
				System.out.println("Something went wrong. Restart and try again!");
				System.exit(0);
			}		
		}
		
		else if (data.exists())
		{
			ArrayList <Integer> accNumbers = new ArrayList <Integer> ();
			
			while(sc.hasNextInt())
			{
				accNumbers.add(sc.nextInt());
			}
			
			for (int i = 0; i < accNumbers.size(); i++)
			{
				int accNumber = 0;
				String name = "";
				double balance = 0;
				
				accNumber = accNumbers.get(i);
				
				File data1 = new File(Integer.toString(accNumber) + ".txt");
				Scanner sc2 = new Scanner(data1);
				
				if (data1.exists())
				{
					name = sc2.nextLine();
				}
				
				sc2.close();
				
				File data2 = new File(name + ".txt");
				Scanner sc3 = new Scanner(data2);
				
				if (data2.exists())
				{
					balance = sc3.nextDouble();
				}
				
				sc3.close();
				
				User account = new User(name , accNumber , balance);
				System.out.println("Import: " + account.isCreated());
			}
		}
		
		log += ("Data successfully imported, ");
		sc.close();
	}
	
	public static void exportDataStage1() throws IOException
	{
		FileWriter writer = new FileWriter("accNumbers.txt");
		File accNumbers = new File("accNumbers.txt");
		Scanner sc = new Scanner(accNumbers);
		
		writer.flush();
		writer.write(export);
		writer.close();
		
		while (sc.hasNextInt())
		{
			log += ("Stage 1 of export completed, ");
			exportDataStage2(sc.nextInt());
		}
		
		sc.close();
	}
	
	public static void exportDataStage2(int accNumber) throws IOException
	{
		File name = new File(Integer.toString(accNumber) + ".txt");
		FileWriter writer = new FileWriter(Integer.toString(accNumber) + ".txt");
		
		if (name.exists())
		{
			name.delete();
		}
		
		name.createNewFile();
		writer.flush();
		writer.write(nameFinder(accNumber));
		writer.close();
		
		log += ("Stage 2 of export completed, ");
		exportDataStage3(nameFinder(accNumber) , accNumber);
	}
	
	public static void exportDataStage3(String name , int accNumber) throws IOException
	{
		File balance = new File(name + ".txt");
		FileWriter writer = new FileWriter(name + ".txt");
		
		if (balance.exists())
		{
			balance.delete();
		}
		
		balance.createNewFile();
		writer.flush();
		writer.write(Double.toString(balanceFinder(accNumber)));
		writer.close();
		
		log += ("Stage 3 of export completed, ");
		exportDataStage4();
	}
	
	public static void exportDataStage4() throws IOException
	{
		File log2 = new File("log.txt");
		FileWriter writer = new FileWriter("log.txt");
		
		if (log2.exists() == false)
		{
			log2.createNewFile();
		}
		
		writer.flush();
		writer.write(log);
		writer.close();
	}
	
	public static double balanceFinder(int accNum)
	{
		for (int i = 0; i < accounts.size(); i++)
		{
			if (accounts.get(i).getAccNumber() == accNum)
			{
				log += ("Balance of " + accounts.get(i).getName() + " requested, "); 
				return accounts.get(i).getBalance();	
			}
		}
		
		return 0;
	}
	
	public static String nameFinder(int accNum)
	{
		for (int i = 0; i < accounts.size(); i++)
		{
			if (accounts.get(i).getAccNumber() == accNum)
			{
				log += ("Name of " + accounts.get(i).getAccNumber() + " requested, ");
				return accounts.get(i).getName();
			}
		}
		
		return "Name not found for this account number.";
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		for (int i = 0; i < accounts.size(); i++)
		{
			if (accounts.get(i).getName().equals(name))
			{
				System.out.println("\nAccount with that name already exists. Please try again!\n");
				Main.signUp();
			}
		}
		
		this.name = name;
	}

	public int getAccNumber() 
	{
		return accNumber;
	}

	public void setAccNumber(int accNumber) 
	{
		if (accNumber < 1)
		{
			System.out.println("\nInvalid entry. Please try again!\n");
			Main.signUp();
		}
		
		for (int i = 0; i < accounts.size(); i++)
		{
			if (accounts.get(i).getAccNumber() == accNumber)
			{
				System.out.println("\nAccount with that number already exists. Please try again!\n");
				Main.signUp();
			}
		}
		
		this.accNumber = accNumber;
	}

	public double getBalance() 
	{
		return balance;
	}

	public void setBalance(double balance) 
	{
		if (balance < 1)
		{
			System.out.println("\nInvalid entry. Please try again!\n");
			Main.signUp();
		}
		
		else
		{
			this.balance = balance;
		}		
	}
	
	public boolean isCreated() 
	{
		return created;
	}

	public void setCreated(boolean created) 
	{
		this.created = created;
	}
	
	public static void details(int accNumber)
	{
		for (int i = 0; i < accounts.size(); i++)
		{
			if (accounts.get(i).getAccNumber() == accNumber)
			{
				System.out.println("Name: " + accounts.get(i).getName());
				System.out.println("Balance: " + accounts.get(i).getBalance());
			}
		}
		
		System.out.println("\nAccount number doesn't exist in our system. Please try again!\n");
		Main.options();
	}
	
	public static boolean transfer (int srcAcc , int tgtAcc , int money)
	{
		if (accNumValidation(srcAcc) == false)
		{
			System.out.println("\nSource account number doesn't exist in our system. Please try again!\n");
			Main.transfer();
		}
		
		if (accNumValidation(tgtAcc) == false)
		{
			System.out.println("\nTarget account number doesn't exist in our system. Please try again!\n");
			Main.transfer();
		}
		
		for (int i = 0; i < accounts.size(); i++)
		{
			if (accounts.get(i).getAccNumber() == srcAcc)
			{
				if (accounts.get(i).getBalance() < money)
				{
					System.out.println("\nSource account has insufficent funds. Please try again!\n");
					Main.transfer();
				}
				
				for (int j = 0; j < accounts.size(); j++)
				{
					if (accounts.get(j).getAccNumber() == tgtAcc)
					{
						log += ("Successfully transfered " + money + " $ from " + Integer.toString(srcAcc) + " to " + Integer.toString(tgtAcc) + ", ");
						accounts.get(i).setBalance(accounts.get(i).getBalance() - money);
						accounts.get(j).setBalance(accounts.get(j).getBalance() + money);
						System.out.println("Successfully transfered " + money + " $ from " + Integer.toString(srcAcc) + " to " + Integer.toString(tgtAcc));
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public static boolean ATM (int accNum , int money)
	{
		if (accNumValidation(accNum) == true)
		{
			for (int i = 0; i < accounts.size(); i++)
			{	
				if (accounts.get(i).getAccNumber() == accNum)
				{
					if (accounts.get(i).getBalance() < money)
					{
						System.out.println("\nAccount has insufficent funds. Please try again!\n");
						Main.transfer();
					}
					
					accounts.get(i).setBalance(accounts.get(i).getBalance() - money);
					System.out.println("Money successfully withdrawn, balance is now: " + accounts.get(i).getBalance() + " $.");
					log += (Integer.toString(accounts.get(i).getAccNumber()) + " successfully withdrew " + money + " $, ");
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean accNumValidation(int accNum)
	{
		for (int i = 0; i < accounts.size(); i++)
		{
			if (accounts.get(i).getAccNumber() == accNum)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static void printLog(int key)
	{
		if (key == masterKey)
		{
			log += "Master-key successfully entered.";
			System.out.println("Access granted.\n");
			System.out.println(log);
		}
	}
}
