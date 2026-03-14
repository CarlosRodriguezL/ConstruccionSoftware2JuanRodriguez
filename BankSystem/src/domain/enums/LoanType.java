package domain.enums;

/**
 * ENUM: LoanType
 * 
 * A catalog of the reasons why someone might borrow money from the bank.
 */
public enum LoanType {
    CONSUMER,   // A loan to buy things like a TV or go on vacation
    VEHICLE,    // A loan to buy a car or motorcycle
    MORTGAGE,   // A loan to buy a house (this takes many years to pay back)
    CORPORATE   // A loan for a business to grow
}