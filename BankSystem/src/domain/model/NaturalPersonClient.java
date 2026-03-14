package domain.model;

import domain.enums.UserRole;
import domain.enums.UserStatus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

/**
 * CLASS: NaturalPersonClient
 * 
 * This class represents a specific type of User: a real person who is a client.
 * It INHERITS from the User class (using 'extends').
 * 
 * It has everything a User has, PLUS an extra field: birthDate.
 */
public class NaturalPersonClient extends User {

    private String birthDate; // Format: DD/MM/YYYY

    // Constructor
    public NaturalPersonClient(int userId, String fullName, String identificationNumber, 
                               String email, String phone, String address, UserStatus status,
                               String username, String password, String birthDate) {
        
        // First, we call the constructor of the PARENT class (User) to set the common fields.
        // We use 'super(...)' for this. The role is fixed to NATURAL_PERSON_CLIENT.
        super(userId, fullName, identificationNumber, email, phone, address, 
              UserRole.NATURAL_PERSON_CLIENT, status, username, password);
        
        // --- Specific validation for NaturalPersonClient ---
        // 1. Birth date cannot be empty.
        if (birthDate == null || birthDate.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Birth date cannot be empty.");
        }

        // 2. Must be 18 years or older.
        if (!isAdult(birthDate)) {
            throw new IllegalArgumentException("ERROR: Client must be at least 18 years old.");
        }

        this.birthDate = birthDate;
    }

    // Simple method to check if the person is 18 or older
    private boolean isAdult(String birthDateStr) {
        try {
            // Define the format of our date (DD/MM/YYYY)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Convert the String to a LocalDate object
            LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
            // Get today's date
            LocalDate today = LocalDate.now();
            // Calculate the period between the two dates
            Period age = Period.between(birthDate, today);
            
            // Return true if age is 18 years or more
            return age.getYears() >= 18;
        } catch (Exception e) {
            // If the date format is wrong, throw an error
            throw new IllegalArgumentException("ERROR: Invalid date format. Please use DD/MM/YYYY.");
        }
    }

    // Getter and Setter for the specific field
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if (birthDate == null || birthDate.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Birth date cannot be empty.");
        }
        if (!isAdult(birthDate)) {
            throw new IllegalArgumentException("ERROR: Client must be at least 18 years old.");
        }
        this.birthDate = birthDate;
    }
}