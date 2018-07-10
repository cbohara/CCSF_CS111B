import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your name:");
		String name = scan.nextLine();
		System.out.println("Please enter you account ID:");
		String id = scan.nextLine();
		System.out.println("Please enter your initial balance:");
		double balance = Double.parseDouble(scan.nextLine());
		System.out.println("Please enter the monthly fee for the account:");
		double monthlyFee = Double.parseDouble(scan.nextLine());
		
		BankAccount account = new BankAccount(name, id, balance, monthlyFee);
		
		char command;
		do {
			System.out.println("Enter \'d\' to make a deposit.");
			System.out.println("Enter \'w\' to make a withdrawal.");
			System.out.println("Enter \'b\' to check the balance.");
			System.out.println("Enter \'f\' to apply the fee.");
			System.out.println("Enter \'q\' to quit.");
			
			command = scan.nextLine().charAt(0);
			
			switch (command) {
				case 'd':
					System.out.println("How much would you like to deposit?");
					double depositAmount = Double.parseDouble(scan.nextLine());
					account.deposit(depositAmount);
					break;
				case 'w':
					System.out.println("How much would you like to withdraw?");
					double withdrawAmount = Double.parseDouble(scan.nextLine());
					account.withdraw(withdrawAmount);
					break;
				case 'b':
					System.out.println("Name: " + account.getName());
					System.out.println("Account ID: " + account.getId());
					System.out.printf("Balance: $%.2f%n", account.getBalance());
					System.out.printf("Monthly Fee: $%.2f%n", account.getMonthlyFee());
					break;
				case 'f':
					System.out.println("What is your new monthly fee?");
					double monthlyFeeAmount = Double.parseDouble(scan.nextLine());
					account.adjustMonthlyFee(monthlyFeeAmount);
					break;
				case 'q':
					System.out.println("Exiting program.");
					System.exit(0);
			}		
		} while (command != 'q');
	}
}
