package vttp2022.day2.workshop;

/**
 * Test cases
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BankAccount myAccount = new BankAccount("Rahillah", 10000);
        System.out.printf("\nHello %s. You have opened an account with initial balance $%.2f.\n",myAccount.getAccountHolderName(),myAccount.getAccountBalance());
        System.out.printf("Your account number is %s\n",myAccount.getAccountNo());
        System.out.printf("Your account was created on: %s\n",myAccount.getCreatedDate());
        System.out.printf("Is your account closed? %b\n",myAccount.getIsAccountClosed());
        System.out.printf("Your balance is: $%.2f\n",myAccount.getAccountBalance());
        myAccount.deposit("1200");
        //myAccount.deposit(40);
        System.out.printf("Your balance is now: $%.2f\n",myAccount.getAccountBalance());
        myAccount.withdraw("500");
        System.out.printf("Now your balance is: $%.2f\n",myAccount.getAccountBalance());
        myAccount.setAccountClosed(true);
        System.out.printf("Is your account closed? %b\n",myAccount.getIsAccountClosed());
        System.out.println("Here is a list of your transactions:");
        myAccount.getTransactions();

        FixedDepositAccount myFixedDepositAccount = new FixedDepositAccount("Rahillah", 1000);
        System.out.printf("\nHello %s. You have opened an account with initial balance $%.2f.\n",myFixedDepositAccount.getAccountHolderName(),myFixedDepositAccount.getAccountBalance());
        System.out.printf("Your account number is: %s\n", myFixedDepositAccount.getAccountNo());
        myFixedDepositAccount.deposit("300");
        System.out.printf("Your balance is: $%.2f\n", myFixedDepositAccount.getAccountBalance());
        System.out.println("Changing the interest to 2%..");
        myFixedDepositAccount.setInterest(2);
        System.out.printf("New balance is $%.2f\n",myFixedDepositAccount.getAccountBalance());
        //System.out.println("Changing the interest to 5%..");
        //myFixedDepositAccount.setInterest(5);
        myFixedDepositAccount.setAccountClosed(true);
        System.out.printf("Is your account closed? %b\n", myFixedDepositAccount.getIsAccountClosed());
    }
}
