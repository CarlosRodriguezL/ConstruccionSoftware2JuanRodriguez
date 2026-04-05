package domain.model;

import domain.enums.AccountType;
import domain.enums.AccountStatus;
import domain.enums.Currency;

/**
 * CLASS: BankAccount
 * 
 * This class represents a bank account where money is kept.
 */
public class BankAccount {
    
    private String accountNumber;
    private AccountType accountType;
    private String holderId;
    private double currentBalance;
    private Currency currency;
    private AccountStatus accountStatus;
    private String openingDate;

    // Constructor
    public BankAccount(String accountNumber, AccountType accountType, String holderId,
                       double currentBalance, Currency currency, AccountStatus accountStatus,
                       String openingDate) {
        
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Account number cannot be empty.");
        }
        if (currentBalance < 0) {
            throw new IllegalArgumentException("ERROR: Current balance cannot be negative.");
        }
        if (holderId == null || holderId.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Holder ID cannot be empty.");
        }
        if (openingDate == null || openingDate.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Opening date cannot be empty.");
        }

        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.holderId = holderId;
        this.currentBalance = currentBalance;
        this.currency = currency;
        this.accountStatus = accountStatus;
        this.openingDate = openingDate;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getHolderId() {
        return holderId;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    // Setters (solo para los que realmente necesitamos)
    public void setAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Account number cannot be empty.");
        }
        this.accountNumber = accountNumber;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setHolderId(String holderId) {
        if (holderId == null || holderId.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Holder ID cannot be empty.");
        }
        this.holderId = holderId;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setOpeningDate(String openingDate) {
        if (openingDate == null || openingDate.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Opening date cannot be empty.");
        }
        this.openingDate = openingDate;
    }

    // ---- Business Logic Methods ----
    
    /**
     * Method to deposit money into the account.
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("ERROR: Deposit amount must be greater than zero.");
        }
        if (this.accountStatus != AccountStatus.ACTIVE) {
            throw new IllegalStateException("ERROR: Cannot deposit into an account that is not ACTIVE.");
        }
        this.currentBalance += amount;
    }

    /**
     * Method to withdraw money from the account.
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("ERROR: Withdrawal amount must be greater than zero.");
        }
        if (this.accountStatus != AccountStatus.ACTIVE) {
            throw new IllegalStateException("ERROR: Cannot withdraw from an account that is not ACTIVE.");
        }
        if (this.currentBalance < amount) {
            throw new IllegalStateException("ERROR: Insufficient funds. Current balance: " + this.currentBalance);
        }
        this.currentBalance -= amount;
    }
}