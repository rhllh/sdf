package vttp2022.day2.workshop;

import java.util.List;
import java.util.LinkedList;
//import java.util.Random;
import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount {
    private String accountHolderName = "";
    private String accountNo = UUID.randomUUID().toString().substring(0,8);
    private float accountBalance;
    private List<String> transactions = new LinkedList<>();      // asyncronous, will deal with concurrency issues
    private boolean isAccountClosed;
    private LocalDate accountCreatedDate;
    private LocalDate accountClosedDate;
    private LocalDateTime date;
    private DateTimeFormatter format;
    private String formatDate;
    private String transDescription;

    // two constructors
    public BankAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountBalance = 0;
        //this.accountNo = setAccountNo();
        this.accountCreatedDate = setCreatedDate();
    }

    public BankAccount(String accountHolderName, float accountBalance) {
        this.accountHolderName = accountHolderName;
        this.accountBalance = accountBalance;
        //this.accountNo = setAccountNo();
        this.accountCreatedDate = setCreatedDate();
    }

    // getter method
    public String getAccountHolderName() {return this.accountHolderName;}
    public float getAccountBalance() {return this.accountBalance;}
    public boolean getIsAccountClosed() {return this.isAccountClosed;}
    public String getAccountNo() {return this.accountNo;}
    public LocalDate getCreatedDate() {return this.accountCreatedDate;}
    public LocalDate getClosedDate() {return this.accountClosedDate;}
    public void getTransactions() {
        for (String transaction : this.transactions) {
            System.out.println("- " + transaction);
        }
    }

    // setter method
    public void setAccountClosed(boolean isAccountClosed) {
        this.isAccountClosed = isAccountClosed;
        System.out.println("Closing account..");
    }
    /*
    public String setAccountNo() {
        Random rand = new Random();
        String number = "";
        for (int i = 0; i < 8; i++) {      // 8-digit account number
            int n = rand.nextInt(10) + 0;
            number += Integer.toString(n);
        }
        return number;
    }
    */
    public LocalDate setCreatedDate() {return LocalDate.now();}
    public LocalDate setClosedDate() {return LocalDate.now();}

    // deposit method
    public void deposit(String amount) {
        try {
            Float floatAmount = Float.parseFloat(amount);
            if (floatAmount.floatValue() <= 0) {
                throw new IllegalArgumentException("Please deposit a positive amount that is greater than zero!");
            } else if (this.isAccountClosed) {
                throw new IllegalArgumentException("This account is closed!");
            } else {
                date = LocalDateTime.now();
                format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                formatDate = date.format(format);
                transDescription = String.format("Deposit $%.2f at %s", floatAmount.floatValue(), formatDate);
                System.out.println(transDescription);
                this.transactions.add(transDescription);
                this.accountBalance += floatAmount;
            } 
        } catch (NumberFormatException e) {     // throws exception if non-numerical is passed
            System.err.println(e);
            throw new IllegalArgumentException("Please enter a numeric amount!");
        }
        
    }

    // withdraw method
    public void withdraw(String amount) {
        try {
            Float floatAmount = Float.parseFloat(amount);
            if (floatAmount <= 0) {
                throw new IllegalArgumentException("Please withdraw a positive amount!");
            } else if (this.isAccountClosed) {
                throw new IllegalArgumentException("This account is closed!");
            } else {
                date = LocalDateTime.now();
                format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                formatDate = date.format(format);
                transDescription = String.format("Withdraw $%.2f at %s", floatAmount.floatValue(), formatDate);
                System.out.println(transDescription);
                this.transactions.add(transDescription);
                this.accountBalance -= floatAmount;
            }
        } catch (NumberFormatException e) {     // throws exception if non-numerical is passed
            System.err.println(e);
            throw new IllegalArgumentException("Please enter a numeric amount!");
        }
    }

}