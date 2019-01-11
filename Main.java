import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		final double OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		final int FREE_TRANSACTIONS = 10;

		
		boolean loop = true;
		while(loop) {
		
			Scanner input = new Scanner(System.in);
			System.out.print("Would you like to create an account (c), make a transaction (t), or end the program (e)? ");
			
			
			String response1 = input.next();
			String name;
			BankAccount transferAccount = null;
			switch(response1) {
			case "t": 
				System.out.print("Would you like to withdraw (w), deposit (d), transfer (t), or get account numbers (n)? ");
				input.nextLine();
				String response2 = input.next();
				int amount;
				int accountNumber;
				String type = null;
				boolean transactionMade = false;
				switch(response2) {
				case "w":
					while (transactionMade == false) {
						System.out.print("Account Number: ");
						input.nextLine();
						accountNumber = Integer.parseInt(input.next());
						System.out.print("Amount: ");
						input.nextLine();
						amount = Integer.parseInt(input.next());
						for (BankAccount bankAccount : bankAccounts) {
							if (bankAccount.getAccountNumber() == accountNumber) {
								try {
									bankAccount.withdraw(amount);
								}
								catch(IllegalArgumentException e) {
									System.out.print("\nTransaction not authorized.");
								}
								transactionMade = true;
							}
						}
						if (transactionMade == false) {
							System.out.print("Account not found. Enter 'y' if you would like to get account numbers. ");
							input.nextLine();
							if (input.next().equals("y")) {
								System.out.print("Name: ");
								input.nextLine();
								name = input.next();
								boolean returnMade = false;
								for (BankAccount bankAccount : bankAccounts) {
									if (bankAccount.getName().equals(name)) {
										if (bankAccount instanceof CheckingAccount) {
											type = "Checking Account";
										}
										else if (bankAccount instanceof SavingsAccount) {
											type = "Savings Account";
										}
										System.out.print("Account Number: " + bankAccount.getAccountNumber() + " (" + type + ")" + "\n");
										returnMade = true;
									}
								}
								if (returnMade == false) {
									System.out.print("Account Not Found.\n");
									transactionMade = true;
								}
							}
						}
					}
					break;
				case "d":
					while(transactionMade == false) {
						System.out.print("Account Number: ");
						input.nextLine();
						accountNumber = Integer.parseInt(input.next());
						System.out.print("Amount: ");
						input.nextLine();
						amount = Integer.parseInt(input.next());
						for (BankAccount bankAccount : bankAccounts) {
							if (bankAccount.getAccountNumber() == accountNumber) {
								bankAccount.deposit(amount);
								transactionMade = true;
							}
						}
						if (transactionMade == false) {
							System.out.print("Account not found. Enter 'y' if you would like to get account numbers. ");
							input.nextLine();
							if (input.next().equals("y")) {
								System.out.print("Name: ");
								input.nextLine();
								name = input.next();
								boolean returnMade = false;
								for (BankAccount bankAccount : bankAccounts) {
									if (bankAccount.getName().equals(name)) {
										if (bankAccount instanceof CheckingAccount) {
											type = "Checking Account";
										}
										else if (bankAccount instanceof SavingsAccount) {
											type = "Savings Account";
										}
										System.out.print("Account Number: " + bankAccount.getAccountNumber() + " (" + type + ")" + "\n");
										returnMade = true;
									}
								}
								if (returnMade == false) {
									System.out.print("Account Not Found.\n");
									transactionMade = true;
								}
							}
						}
					}
					break;
				case "t":
					while(transactionMade == false) {
						int transferAccountNumber = -1;
						boolean accountNotFound = false;
						System.out.print("Account Number (Transferring From): ");
						input.nextLine();
						accountNumber = Integer.parseInt(input.next());
						System.out.print("Account Number (Transferring To): ");
						input.nextLine();
						transferAccountNumber = Integer.parseInt(input.next());
						System.out.print("Amount: ");
						input.nextLine();
						amount = Integer.parseInt(input.next());
						for (BankAccount bankAccount : bankAccounts) {
							if (bankAccount.getAccountNumber() == accountNumber) {
								for (int i = 0; i < bankAccounts.size(); i++) {
									if (bankAccounts.get(i).getAccountNumber() == transferAccountNumber) {
										transferAccount = bankAccounts.get(i);
									}
								}
								if (transferAccountNumber == -1 || transferAccount == null) {
									accountNotFound = true;
								}
								else if (accountNotFound == false) {
									try {
										bankAccount.transfer(transferAccount, amount);
									
									}
									catch(IllegalArgumentException e) {
										System.out.print("Transaction not authorized.\n");
									}
									transactionMade = true;
								}
							}
						}
						if (transactionMade == false) {
							System.out.print("Account not found. Enter 'y' if you would like to get account numbers. ");
							input.nextLine();
							if (input.next().equals("y")) {
								System.out.print("Name: ");
								input.nextLine();
								name = input.next();
								boolean returnMade = false;
								for (BankAccount bankAccount : bankAccounts) {
									if (bankAccount.getName().equals(name)) {
										if (bankAccount instanceof CheckingAccount) {
											type = "Checking Account";
										}
										else if (bankAccount instanceof SavingsAccount) {
											type = "Savings Account";
										}
										System.out.print("Account Number: " + bankAccount.getAccountNumber() + " (" + type + ")" + "\n");
										returnMade = true;
									}
								}
								if (returnMade == false) {
									System.out.print("Account Not Found.\n");
									transactionMade = true;
								}
							}
						}
					}
					break;
				case "n":
					System.out.print("Name: ");
					input.nextLine();
					name = input.next();
					for (BankAccount bankAccount : bankAccounts) {
						if (bankAccount.getName().equals(name)) {
							if (bankAccount instanceof CheckingAccount) {
								type = "Checking Account";
							}
							else if (bankAccount instanceof SavingsAccount) {
								type = "Savings Account";
							}
							System.out.print("Account Number: " + bankAccount.getAccountNumber() + " (" + type + ")" + "\n");
						}
					}
					break;
				default:
					System.out.print("Invalid  input.\n");
				}
				break;
			case "c":
				System.out.print("Name: ");
				input.nextLine();
				name = input.next();
				System.out.print("Would you like to make a savings account (s) or a checking account (c)? ");
				input.nextLine();
				String request = input.next();
				if (request.equals("s")) {
					bankAccounts.add(new SavingsAccount(name, RATE, MIN_BAL, MIN_BAL_FEE));
				}
				else if (request.equals("c")) {
					bankAccounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
				}
				else {
					System.out.print("\nNo option selected.");
				}
				
				break;
			case "e":
					loop = false;
				break;
			default:
				System.out.print("Invalid input.\n");
				break;
			}
			
			//test
			//for (BankAccount bankAccount : bankAccounts) {
			//	System.out.print(bankAccount.getAccountNumber() + " ");
			//	System.out.println(bankAccount.getBalance());
			//}
			
		}	
			
	}

}
