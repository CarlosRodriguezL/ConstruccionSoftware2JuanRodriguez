package domain.enums;

/**
 * ENUM: TransferStatus
 * 
 * This catalog tracks where a money transfer is in its process.
 */
public enum TransferStatus {
    PENDING,            // Just created, waiting to be processed
    WAITING_APPROVAL,   // It's a big amount, waiting for the company boss to say OK
    APPROVED,           // The boss said OK, but the money hasn't moved yet
    EXECUTED,           // SUCCESS! The money moved from one account to another.
    REJECTED,           // The boss said NO.
    EXPIRED             // The boss took too long to decide (more than 1 hour).
}