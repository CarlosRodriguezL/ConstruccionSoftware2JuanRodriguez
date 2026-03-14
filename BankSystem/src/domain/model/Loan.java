package domain.model;

import domain.enums.LoanType;
import domain.enums.LoanStatus;

/**
 * CLASS: Loan
 * 
 * This class represents a loan that a client requests from the bank.
 */
public class Loan {
    
    private int loanId;                 // Unique ID for the loan
    private LoanType loanType;           // Type (Consumer, Mortgage...)
    private String clientId;             // The identificationNumber of the client asking for the loan
    private double requestedAmount;      // How much they want
    private double approvedAmount;       // How much the bank actually approved (if any)
    private double interestRate;         // The interest rate (e.g., 0.15 for 15%)
    private int termMonths;              // How many months to pay it back
    private LoanStatus loanStatus;       // Current status (Under Study, Approved...)
    private String approvalDate;         // When it was approved (DD/MM/YYYY) - null if not approved
    private String disbursementDate;     // When the money was given - null if not disbursed
    private String disbursementAccountNumber; // Which account the money was sent to

    // Constructor
    public Loan(int loanId, LoanType loanType, String clientId, double requestedAmount,
                double interestRate, int termMonths) {
        
        // Basic validations
        if (loanId <= 0) {
            throw new IllegalArgumentException("ERROR: Loan ID must be a positive number.");
        }
        if (clientId == null || clientId.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Client ID cannot be empty.");
        }
        if (requestedAmount <= 0) {
            throw new IllegalArgumentException("ERROR: Requested amount must be greater than zero.");
        }
        if (interestRate <= 0) {
            throw new IllegalArgumentException("ERROR: Interest rate must be greater than zero.");
        }
        if (termMonths <= 0) {
            throw new IllegalArgumentException("ERROR: Term months must be greater than zero.");
        }

        this.loanId = loanId;
        this.loanType = loanType;
        this.clientId = clientId;
        this.requestedAmount = requestedAmount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.loanStatus = LoanStatus.UNDER_STUDY; // Default status when created
        this.approvedAmount = 0.0; // Not approved yet
        this.approvalDate = null;
        this.disbursementDate = null;
        this.disbursementAccountNumber = null;
    }

    // Getters and Setters
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        if (loanId <= 0) {
            throw new IllegalArgumentException("ERROR: Loan ID must be a positive number.");
        }
        this.loanId = loanId;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        if (clientId == null || clientId.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Client ID cannot be empty.");
        }
        this.clientId = clientId;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        if (requestedAmount <= 0) {
            throw new IllegalArgumentException("ERROR: Requested amount must be greater than zero.");
        }
        this.requestedAmount = requestedAmount;
    }

    public double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(double approvedAmount) {
        if (approvedAmount < 0) {
            throw new IllegalArgumentException("ERROR: Approved amount cannot be negative.");
        }
        this.approvedAmount = approvedAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate <= 0) {
            throw new IllegalArgumentException("ERROR: Interest rate must be greater than zero.");
        }
        this.interestRate = interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        if (termMonths <= 0) {
            throw new IllegalArgumentException("ERROR: Term months must be greater than zero.");
        }
        this.termMonths = termMonths;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(String disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public String getDisbursementAccountNumber() {
        return disbursementAccountNumber;
    }

    public void setDisbursementAccountNumber(String disbursementAccountNumber) {
        this.disbursementAccountNumber = disbursementAccountNumber;
    }
}