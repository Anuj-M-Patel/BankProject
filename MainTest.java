
public class MainTest {

	public static void main(String[] args) {

		CheckingAccount ca = new CheckingAccount("Bob", 10, 5, 10);
		
		ca.deposit(5000);
		
		System.out.println(ca.getBalance());
		
		SavingsAccount sa = new SavingsAccount("Bob", 10, 5, 10);
		
		sa.deposit(50);
		sa.withdraw(50);
		ca.transfer(sa, 4000);
		
		System.out.println(ca.getBalance());
		System.out.println(sa.getBalance());

	}

}
