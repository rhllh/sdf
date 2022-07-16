package vttp2022.day2.workshop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testBankAccount() {
        BankAccount myAccount = new BankAccount("Rahillah", 10000);
        System.out.printf("\nHello %s. You have opened an account with initial balance $%.2f.\n",myAccount.getAccountHolderName(),myAccount.getAccountBalance());
        assertEquals("Rahillah",myAccount.getAccountHolderName());

        System.out.printf("Your account number is %s\n",myAccount.getAccountNo());
        System.out.printf("Your account was created on: %s\n",myAccount.getCreatedDate());
        
        System.out.printf("Is your account closed? %b\n",myAccount.getIsAccountClosed());
        assertFalse("Your account is closed",myAccount.getIsAccountClosed());
        
        System.out.printf("Your balance is: $%.2f\n",myAccount.getAccountBalance());
        assertEquals("Wrong balance",10000,myAccount.getAccountBalance(),0.1);

        myAccount.deposit("1200");
        System.out.printf("Your balance is now: $%.2f\n",myAccount.getAccountBalance());
        assertEquals("Deposit incorrect",11200.00,myAccount.getAccountBalance(),0.1);

        myAccount.withdraw("500");
        assertEquals("Withdraw incorrect",10700.00, myAccount.getAccountBalance(),0.1);
        System.out.printf("Now your balance is: $%.2f\n",myAccount.getAccountBalance());
        
        myAccount.setAccountClosed(true);
        System.out.printf("Is your account closed? %b\n",myAccount.getIsAccountClosed());
        
        System.out.println("Here is a list of your transactions:");
        myAccount.getTransactions();
    }

    @Test
    public void FixedDepositAccount() {
        FixedDepositAccount myFixedDepositAccount = new FixedDepositAccount("Rahillah", 1000);
        System.out.printf("\nHello %s. You have opened an account with initial balance $%.2f.\n",myFixedDepositAccount.getAccountHolderName(),myFixedDepositAccount.getAccountBalance());
        assertEquals("Rahillah",myFixedDepositAccount.getAccountHolderName());

        System.out.printf("Your account number is: %s\n", myFixedDepositAccount.getAccountNo());
        
        myFixedDepositAccount.deposit("300");
        assertEquals("Deposit incorrectly",1003,myFixedDepositAccount.getAccountBalance(),0.1);
        
        System.out.printf("Your balance is: $%.2f\n", myFixedDepositAccount.getAccountBalance());
        
        System.out.println("Changing the interest to 2%..");
        myFixedDepositAccount.setInterest(2);
        assertEquals("Adding interest incorrect",1002, myFixedDepositAccount.getAccountBalance(),0.1);
        
        System.out.printf("New balance is $%.2f\n",myFixedDepositAccount.getAccountBalance());
        //System.out.println("Changing the interest to 5%..");
        //myFixedDepositAccount.setInterest(5);
        myFixedDepositAccount.setAccountClosed(true);
        assertTrue("Your account is still open",myFixedDepositAccount.getIsAccountClosed());
        System.out.printf("Is your account closed? %b\n",myFixedDepositAccount.getIsAccountClosed());
    }
}
