
public class SavingsAccount extends BankAccount {
	
	/**
	 * the minimum balance
	 */
	public final double MINIMUM_BALANCE;
	
	/**
	 * the minimum balance fee
	 */
	public final double MINIMUM_BALANCE_FEE;
	
	/**
	 * the interest rate
	 */
	private double interestRate;
	
	/**
	 * 
	 * @param name	the name of the owner
	 * @param balance	the balance
	 * @param interestRate	the interest rate
	 * @param minimumBalance	the minimum balance
	 * @param minimumBalanceFee	the minimum balance fee
	 */
	public SavingsAccount(String name, double balance, double interestRate, double minimumBalance, double minimumBalanceFee) {
		super(name, balance);
		this.interestRate = interestRate;
		MINIMUM_BALANCE = minimumBalance;
		MINIMUM_BALANCE_FEE = minimumBalanceFee;
	}
	
	/**
	 * 
	 * @param name	the name of the owner
	 * @param interestRate	the interest rate
	 * @param minimumBalance	the minimum balance
	 * @param minimumBalanceFee	the minimum balance fee
	 */
	public SavingsAccount(String name, double interestRate, double minimumBalance, double minimumBalanceFee) {
		super(name);
		this.interestRate = interestRate;
		MINIMUM_BALANCE = minimumBalance;
		MINIMUM_BALANCE_FEE = minimumBalanceFee;
	}
	
	/**
	 * withdraws money from the savings account
	 * @param amount	the amount of money to be withdrawn
	 */
	public void withdraw(double amount) {
		if (amount > 0) {
			if (getBalance() - amount >= MINIMUM_BALANCE) {
				super.withdraw(amount);
			}
			else if (getBalance() - amount < MINIMUM_BALANCE && getBalance() - amount >= 0) {
				super.withdraw(amount + MINIMUM_BALANCE_FEE);
			}
			else {
				throw new IllegalArgumentException();
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * transfer money from the savings account to a bank account
	 * @param bankAccount	the bank account to be transferred to
	 * @param amount	the amount of money to be transferred
	 */
	public void transfer(BankAccount bankAccount, double amount) {
		if (getName().equals(bankAccount.getName())) {
			withdraw(amount);
			bankAccount.deposit(amount);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * adds interest to the balance
	 */
	public void addInterest() {
		super.deposit(interestRate*getBalance());
	}
	
	/**
	 * adds interest to the balance
	 */
	public void endOfMonthUpdate() {
		addInterest();
	}
}
