package domain.enums;

/**
 * ENUM: LoanStatus
 * 
 * This catalog tracks where a loan is in its "life cycle".
 * It's like the stages of a package being shipped.
 */
public enum LoanStatus {
    UNDER_STUDY,    // The bank just received the request and is checking it
    APPROVED,       // A bank analyst said "yes", but the money hasn't been given yet
    REJECTED,       // A bank analyst said "no"
    DISBURSED,      // The money was sent to the client's account
    IN_DEFAULT,     // The client stopped paying (the loan is "bad")
    CANCELLED       // The loan process was stopped for some other reason
}