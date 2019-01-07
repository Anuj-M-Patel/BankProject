
public abstract class BankAccount {

	/**
	 * the account number of the next created bank account
	 */
	private static int nextAccountNumber = 1;
	
	/**
	 * the name of the owner
	 */
	private String name;
	/**
	 * the account number
	 */
	private int accountNumber;
	/**
	 * the balance
	 */
	private double balance;
	
	/**
	 * 
	 * @param name	the name of the owner of the bank account
	 */
	public BankAccount(String name) {
		this.name = name;
		accountNumber = nextAccountNumber;
		balance = 0;
		nextAccountNumber += 1;
	}
	
	/**
	 * 
	 * @param name	the name of the owner of the bank account
	 * @param balance	the balance of the bank account
	 */
	public BankAccount(String name, double balance) {
		this.name = name;
		accountNumber = nextAccountNumber;
		this.balance = balance;
		nextAccountNumber += 1;
	}
	
	/**
	 * deposits money into the bank account
	 * @param amount	the amount of money to be deposited
	 */
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * withdraws money from the bank account
	 * @param amount	the amount of money to be withdrawn
	 */
	public void withdraw(double amount) {
		if (amount > 0) {
			balance -= amount;
		}
	}
	
	/**
	 * returns the name of the owner of the bank account
	 * @return	the name of the owner
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns the balance of the bank account
	 * @return	the balance
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * returns the account number of the bank account
	 * @return	the account number
	 */
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public abstract void endOfMonthUpdate();
	
	/**
	 * transfer money from the bank account to a bank account
	 * @param bankAccount	the bank account to transfer money to
	 * @param amount	the amount of money to transfer
	 */
	public void transfer(BankAccount bankAccount, double amount) {
		if (balance > 0) {
			withdraw(amount);
			bankAccount.deposit(amount);
		}
	}
	
	/**
	 * returns the account number, name of the owner, and the balance of the bank account
	 * @return the account number, name of the owner, and the balance
	 */
	public String toString() {
		return accountNumber + "\t" + name + "\t$" + balance;
	}
}
