import java.util.Scanner;

class BankAccount {
	
	String name;
	String username;
	String password;
	String accountNo;
	float balance = 0f;
	int transactions = 0;
	String transactionHistory = "";
	
	public void register() {
		Scanner read = new Scanner(System.in);
		System.out.print("\nEnter Your Name - ");
		this.name = read.nextLine();
		System.out.print("\nEnter Your Username - ");
		this.username = read.nextLine();
		System.out.print("\nEnter Your Password - ");
		this.password = read.nextLine();
		System.out.print("\nEnter Your Account Number - ");
		this.accountNo = read.nextLine();
		System.out.println("\nRegistration completed..kindly login");
	}
	
	public boolean login() {
		boolean isLogin = false;
		Scanner read = new Scanner(System.in);
		while (!isLogin) {
			System.out.print("\nEnter Your Username - ");
			String inputUsername = read.nextLine();
			if (inputUsername.equals(username)) {
				while (!isLogin) {
					System.out.print("\nEnter Your Password - ");
					String inputPassword = read.nextLine();
					if (inputPassword.equals(password)) {
						System.out.print("\nLogin successful!!");
						isLogin = true;
					} else {
						System.out.println("\nIncorrect Password");
					}
				}
			} else {
				System.out.println("\nUsername not found");
			}
		}
		return isLogin;
	}
	
	public void withdrawAmount() {
		
		System.out.print("\nEnter amount to withdraw - ");
		Scanner read = new Scanner(System.in);
		float amount = read.nextFloat();
		try {
			
			if (balance >= amount) {
				transactions++;
				balance -= amount;
				System.out.println("\nWithdrawal Successful");
				String str = amount + " Rs Withdrawn\n";
				transactionHistory = transactionHistory.concat(str);
				
			} else {
				System.out.println("\nInsufficient Balance");
			}
			
		} catch (Exception e) {
		}
	}
	
	public void depositAmount() {
		
		System.out.print("\nEnter amount to deposit - ");
		Scanner read = new Scanner(System.in);
		float amount = read.nextFloat();
		
		try {
			if (amount <= 100000f) {
				transactions++;
				balance += amount;
				System.out.println("\nDeposit Successful");
				String str = amount + " Rs deposited\n";
				transactionHistory = transactionHistory.concat(str);
			} else {
				System.out.println("\nSorry...Limit is 100000.00");
			}
			
		} catch (Exception e) {
		}
	}
	
	public void transferAmount() {
		
		Scanner read = new Scanner(System.in);
		System.out.print("\nEnter Recipient's Name - ");
		String recipient = read.nextLine();
		System.out.print("\nEnter amount to transfer - ");
		float amount = read.nextFloat();
		
		try {
			if (balance >= amount) {
				if (amount <= 50000f) {
					transactions++;
					balance -= amount;
					System.out.println("\nSuccessfully Transferred to " + recipient);
					String str = amount + " Rs transferred to " + recipient + "\n";
					transactionHistory = transactionHistory.concat(str);
				} else {
					System.out.println("\nSorry...Limit is 50000.00");
				}
			} else {
				System.out.println("\nInsufficient Balance");
			}
		} catch (Exception e) {
		}
	}
	
	public void checkAccountBalance() {
		System.out.println("\n" + balance + " Rs");
	}
	
	public void showTransactionHistory() {
		if (transactions == 0) {
			System.out.println("\nEmpty");
		} else {
			System.out.println("\n" + transactionHistory);
		}
	}
}


class AtmInterface {
	
	public static int takeIntegerInput(int limit) {
		int input = 0;
		boolean flag = false;
		
		while (!flag) {
			try {
				Scanner read = new Scanner(System.in);
				input = read.nextInt();
				flag = true;
				
				if (flag && (input > limit || input < 1)) {
					System.out.println("Choose the number between 1 to " + limit);
					flag = false;
				}
			} catch (Exception e) {
				System.out.println("Enter only an integer value");
				flag = false;
			}
		};
		return input;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("\n********* WELCOME TO UNION BANK ATM SYSTEM *********\n");
		System.out.println("1.Register \n2.Exit");
		System.out.print("Enter Your Choice - ");
		int choice = takeIntegerInput(2);
		
		if (choice == 1) {
			BankAccount bankAccount = new BankAccount();
			bankAccount.register();
			while (true) {
				System.out.println("\n1.Login \n2.Exit");
				System.out.print("Enter Your Choice - ");
				int ch = takeIntegerInput(2);
				if (ch == 1) {
					if (bankAccount.login()) {
						System.out.println("\n\n**********WELCOME BACK " + bankAccount.name + " **********\n");
						boolean isFinished = false;
						while (!isFinished) {
							System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
							System.out.print("\nEnter Your Choice - ");
							int c = takeIntegerInput(6);
							switch (c) {
								case 1:
									bankAccount.withdrawAmount();
									break;
								case 2:
									bankAccount.depositAmount();
									break;
								case 3:
									bankAccount.transferAmount();
									break;
								case 4:
									bankAccount.checkAccountBalance();
									break;
								case 5:
									bankAccount.showTransactionHistory();
									break;
								case 6:
									isFinished = true;
									break;
							}
						}
					}
				} else {
					System.exit(0);
				}
			}
		} else {
			System.exit(0);
		}
	}
}
