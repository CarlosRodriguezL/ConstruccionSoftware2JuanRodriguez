package domain.enums;

/**
 * ENUM: UserStatus
 * 
 * This catalog shows if a user is currently allowed to use the system.
 * It's like a light switch: ON (ACTIVE), OFF (INACTIVE), or BROKEN (BLOCKED).
 */
public enum UserStatus {
    ACTIVE,    // The user can log in and do things
    INACTIVE,  // The user cannot log in (maybe they left the bank)
    BLOCKED    // The user is locked out for security reasons (wrong password too many times)
}