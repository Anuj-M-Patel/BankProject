
public class CheckingAccount extends BankAccount {

	/**
	 * the overdraft fee
	 */
	public final double OVER_DRAFT_FEE;
	
	/**
	 * the transaction fee
	 */
	public final double TRANSACTION_FEE;
	
	/**
	 * the number of free transactions
	 */
	public final int FREE_TRANSACTIONS;
	
	/**
	 * the number of transactions made
	 */
	private int numberOfTransactions;
	
	/**
	 * 
	 * @param name	the name of the owner
	 * @param balance	the balance
	 * @param overDraftFee the overdraft fee
	 * @param transactionFee	the transaction fee
	 * @param freeTransactions	the number of free transactions
	 */
	public CheckingAccount(String name, double balance, double overDraftFee, double transactionFee, int freeTransactions) {
		super(name, balance);
		OVER_DRAFT_FEE = overDraftFee;
		TRANSACTION_FEE = transactionFee;
		FREE_TRANSACTIONS = freeTransactions;
		numberOfTransactions = 0;
	}
	
	/**
	 * 
	 * @param name	the name of the owner
	 * @param overDraftFee the overdraft fee
	 * @param transactionFee	the transaction fee
	 * @param freeTransactions	the number of free transactions
	 */
	public CheckingAccount(String name, double overDraftFee, double transactionFee, int freeTransactions) {
		super(name);
		OVER_DRAFT_FEE = overDraftFee;
		TRANSACTION_FEE = transactionFee;
		FREE_TRANSACTIONS = freeTransactions;
		numberOfTransactions = 0;
	}
	
	/**
	 * deposits money into the checking account
	 * @param amount 	the amount of money to deposit
	 */
	public void deposit(double amount) {
		if (numberOfTransactions >= FREE_TRANSACTIONS) {
			super.deposit(amount - TRANSACTION_FEE);
		}
		else {
			super.deposit(amount);
		}
		numberOfTransactions++;
	}
	
	/**
	 * withdraws money from the checking account
	 * @param amount	the amount of money to withdraw
	 */
	public void withdraw(double amount) {
		if (amount > 0) {
			if (numberOfTransactions >= FREE_TRANSACTIONS) {
				if (getBalance() >= 0) {
					super.withdraw(TRANSACTION_FEE + amount);
					if (getBalance() < 0) {
						super.withdraw(OVER_DRAFT_FEE);
					}
				}
				else if (getBalance() < 0) {
					throw new IllegalArgumentException();
				}
			}
			else {
				if (getBalance() >= 0) {
					super.withdraw(amount);
					if (getBalance() < 0) {
						super.withdraw(OVER_DRAFT_FEE);
					}
				}
				else if (getBalance() < 0) {
					throw new IllegalArgumentException();
				}
			}
			numberOfTransactions++;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	

	/**
	 * transfers money from the checking account to a bank account
	 * @param bankAccount	the bank account to be transferred to
	 * @param amount	the amount of money to be transferred
	 */
	public void transfer(BankAccount bankAccount, double amount) {
		if (getName().equals(bankAccount.getName())) {
			if (getBalance() - TRANSACTION_FEE - amount >= 0) {
				withdraw(amount);
				bankAccount.deposit(amount);
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
	 * resets the number of transactions
	 */
	public void endOfMonthUpdate() {
		numberOfTransactions = 0;
	}
	
}
