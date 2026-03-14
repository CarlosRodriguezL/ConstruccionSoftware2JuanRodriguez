package domain.model;

import domain.enums.UserRole;
import domain.enums.UserStatus;

/**
 * CLASS: CompanyClient
 * 
 * This class represents a company that is a client.
 * It also INHERITS from User.
 * 
 * A company client is a "User" in the system, but it's actually a legal entity.
 * The person using the account is the legal representative.
 */
public class CompanyClient extends User {

    private String businessName;           // The official registered name of the company
    private String taxId;                   // The tax identification number (NIT). Must be unique.
    private int legalRepresentativeId;      // The userId of the NaturalPersonClient who represents this company.

    // Constructor
    public CompanyClient(int userId, String fullName, String identificationNumber, 
                         String email, String phone, String address, UserStatus status,
                         String username, String password, 
                         String businessName, String taxId, int legalRepresentativeId) {
        
        // Call the parent (User) constructor. Role is fixed to COMPANY_CLIENT.
        // Note: The 'fullName' parameter here might be the name of the representative user.
        // The company's legal name is stored in 'businessName'.
        super(userId, fullName, identificationNumber, email, phone, address, 
              UserRole.COMPANY_CLIENT, status, username, password);
        
        // Validations for company-specific fields
        if (businessName == null || businessName.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Business name cannot be empty.");
        }
        if (taxId == null || taxId.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Tax ID cannot be empty.");
        }
        // We can't validate the representative here because we don't have the list of users.
        // That will be done in the Service layer (UserService).

        this.businessName = businessName;
        this.taxId = taxId;
        this.legalRepresentativeId = legalRepresentativeId;
    }

    // Getters and Setters
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        if (businessName == null || businessName.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Business name cannot be empty.");
        }
        this.businessName = businessName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        if (taxId == null || taxId.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Tax ID cannot be empty.");
        }
        this.taxId = taxId;
    }

    public int getLegalRepresentativeId() {
        return legalRepresentativeId;
    }

    public void setLegalRepresentativeId(int legalRepresentativeId) {
        this.legalRepresentativeId = legalRepresentativeId;
    }
}