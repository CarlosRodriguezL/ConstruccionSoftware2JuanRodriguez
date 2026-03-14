package domain.model;

import domain.enums.TransferStatus;

/**
 * CLASS: Transfer
 * 
 * This class represents a request to move money from one bank account to another.
 */
public class Transfer {
    
    private int transferId;                 // Unique ID for the transfer
    private String originAccount;            // Account number money comes FROM
    private String destinationAccount;       // Account number money goes TO
    private double amount;                   // How much money
    private String creationDateTime;         // When the transfer was requested (DD/MM/YYYY HH:MM:SS)
    private String approvalDateTime;         // When it was approved (null if not approved)
    private TransferStatus transferStatus;   // Pending, Executed, etc.
    private int creatorUserId;               // Who requested this transfer
    private Integer approverUserId;          // Who approved it (null if not approved). Using Integer to allow null.

    // Constructor
    public Transfer(int transferId, String originAccount, String destinationAccount, double amount,
                    String creationDateTime, TransferStatus transferStatus, int creatorUserId) {
        
        // Validations
        if (transferId <= 0) {
            throw new IllegalArgumentException("ERROR: Transfer ID must be a positive number.");
        }
        if (originAccount == null || originAccount.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Origin account cannot be empty.");
        }
        if (destinationAccount == null || destinationAccount.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Destination account cannot be empty.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("ERROR: Transfer amount must be greater than zero.");
        }
        if (creationDateTime == null || creationDateTime.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Creation date/time cannot be empty.");
        }
        if (creatorUserId <= 0) {
            throw new IllegalArgumentException("ERROR: Creator User ID must be a positive number.");
        }

        this.transferId = transferId;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.creationDateTime = creationDateTime;
        this.transferStatus = transferStatus;
        this.creatorUserId = creatorUserId;
        this.approvalDateTime = null;
        this.approverUserId = null;
    }

    // Getters and Setters
    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        if (transferId <= 0) {
            throw new IllegalArgumentException("ERROR: Transfer ID must be a positive number.");
        }
        this.transferId = transferId;
    }

    public String getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(String originAccount) {
        if (originAccount == null || originAccount.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Origin account cannot be empty.");
        }
        this.originAccount = originAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        if (destinationAccount == null || destinationAccount.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Destination account cannot be empty.");
        }
        this.destinationAccount = destinationAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("ERROR: Transfer amount must be greater than zero.");
        }
        this.amount = amount;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        if (creationDateTime == null || creationDateTime.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Creation date/time cannot be empty.");
        }
        this.creationDateTime = creationDateTime;
    }

    public String getApprovalDateTime() {
        return approvalDateTime;
    }

    public void setApprovalDateTime(String approvalDateTime) {
        this.approvalDateTime = approvalDateTime;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }

    public int getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(int creatorUserId) {
        if (creatorUserId <= 0) {
            throw new IllegalArgumentException("ERROR: Creator User ID must be a positive number.");
        }
        this.creatorUserId = creatorUserId;
    }

    public Integer getApproverUserId() {
        return approverUserId;
    }

    public void setApproverUserId(Integer approverUserId) {
        this.approverUserId = approverUserId;
    }
}
