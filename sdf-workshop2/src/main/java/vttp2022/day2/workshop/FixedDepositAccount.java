package vttp2022.day2.workshop;

public class FixedDepositAccount extends BankAccount {
    private float interest = 3;
    private int duration = 6;
    private int timesChangedInterest = 0;
    private int timesChangedDuration = 0;

    public FixedDepositAccount(String name, float balance) {
        super(name, balance);
        this.timesChangedInterest = 0;
        this.timesChangedDuration = 0;
        timesChangedInterest = 0;
        timesChangedDuration = 0;
    }
    public FixedDepositAccount(String name, float balance, float interestAmount) {
        super(name, balance);
        this.timesChangedInterest = 0;
        this.timesChangedDuration = 0;
        this.interest = interestAmount;
    }
    public FixedDepositAccount(String name, float balance, float interestAmount, int durationMonths) {
        this(name, balance, interestAmount);
        this.timesChangedInterest = 0;
        this.timesChangedDuration = 0;
        this.duration = durationMonths;
    }

    // prevent interest and duration from being modified more than once
    public void setInterest(float interestAmount) {
        if (this.timesChangedInterest == 1 || interest < 0) errorMsg("interest");
        else {
            interest = interestAmount;
            this.getAccountBalance();
            this.timesChangedInterest += 1;
        }
    }

    public void setDuration(int months) {
        if (this.timesChangedDuration == 1 || duration < 0) errorMsg("duration");
        else {
            duration = months;
            this.getAccountBalance();
            this.timesChangedDuration += 1;
        }
    }

    public String errorMsg(String errorType) {
        String msg = String.format("You cannot change the %s anymore",errorType);
        throw new IllegalArgumentException(msg);
    }

    // deposit and withdraw perform NOP (do nothing)
    @Override
    public void deposit(String amount) {
        // do nothing
    }

    @Override
    public void withdraw(String amount) {
        // do nothing
    }

    // getAccountBalance() returns balance with interest
    @Override
    public float getAccountBalance() {
        float balance = super.getAccountBalance();
        float balanceWithInterest = balance + this.interest;
        return balanceWithInterest;
    }
}
