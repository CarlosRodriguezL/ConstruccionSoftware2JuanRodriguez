package domain.enums;

/**
 * ENUM: AccountStatus
 * 
 * This tells us the current state of a bank account.
 */
public enum AccountStatus {
    ACTIVE,      // The account is working normally. You can deposit and withdraw.
    BLOCKED,     // The account is temporarily frozen (maybe due to a court order).
    CANCELLED    // The account is closed forever.
}