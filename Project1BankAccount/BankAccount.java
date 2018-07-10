public class BankAccount {
	private String name;
	private String id;
	private double balance;
	private double monthlyFee;
	
	public BankAccount(String name, String id, double balance, double monthlyFee) {
		this.name = name;
		this.id = id;
		this.balance = balance;
		this.monthlyFee = monthlyFee;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public double getMonthlyFee() {
		return monthlyFee;
	}
	
	public void setName(String name) {
		if (!name.isEmpty()) {
			this.name = name;
		}
	}
	
	public void setId(String id) {
		if (!id.isEmpty()) {
			this.id = id;
		}
	}
	
	public void setBalance(double balance) {
		if (balance >= 0.0) {
			this.balance = balance;
		} else {
			System.out.println("Balance must be greater than or equal to 0. Default balance is 0.");
		}
	}
	
	public void setMonthlyFee(double monthlyFee) {
		if (monthlyFee >= 0.0) {
			this.monthlyFee = monthlyFee;
		} else {
			System.out.println("Monthly fee must be greater than or equal to 0. Default fee is 0.");
		}
	}
	
	public String toString() {
		String s = "Name " + name + "! Your account ID is " + id + ".\n";
		s += "Your balance is $" + balance + ".\n";
		s += "Your monthly fee is $" + monthlyFee + ".\n";
		return s;
	}
	
	public void deposit(double depositAmount) {
		if (depositAmount > 0.0) {
			balance += depositAmount;
		} else {
			System.out.println("The deposit amount must be greater than 0.  Please try again.");
		}
	}
	
	public void withdraw(double withdrawAmount) {
		if (withdrawAmount > 0.0) {	
			if (balance - withdrawAmount < 0.0) {
				System.out.println("The withdraw amount must be less than the balance to avoid overdraft fees.");
			} else {
				balance -= withdrawAmount;
			}
		} else {
			System.out.println("The withdraw amount must be greater than 0. Please try again.");
		}
	}
	
	public void adjustMonthlyFee(double newMonthlyFee) {
		if (newMonthlyFee > 0.0) {
			monthlyFee = newMonthlyFee;
		} else {
			System.out.println("The monthly fee must be greater than 0. Please try again.");
		}
	}
}