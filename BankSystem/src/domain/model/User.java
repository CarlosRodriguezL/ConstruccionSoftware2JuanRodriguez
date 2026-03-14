package domain.model;

import domain.enums.UserRole;
import domain.enums.UserStatus;

/**
 * CLASS: User
 * 
 * This is the base class for EVERY person who uses the system.
 * It's like a template. It contains all the common information we need for anyone,
 * whether they are a client or a bank employee.
 * 
 * Other classes like NaturalPersonClient will INHERIT from this class.
 * This is called INHERITANCE in Object-Oriented Programming.
 */
public class User {
    // ---- Private fields (Encapsulation) ----
    // We make fields private so they can only be changed through controlled methods (getters/setters).
    private int userId;                 // Unique ID for the system
    private String fullName;             // Full name of the person
    private String identificationNumber; // Government ID (DNI, Cedula, NIT). Must be unique.
    private String email;                // Email address. Must have "@" and a domain.
    private String phone;                // Phone number. Must be 7-15 digits.
    private String address;              // Physical address.
    private UserRole role;               // What is this user's job? (from the Enum)
    private UserStatus status;           // Is the user ACTIVE, INACTIVE, or BLOCKED?
    private String username;              // Login name
    private String password;              // Login password

    // ---- Constructor ----
    // This is a special method that runs when we create a new User object (using the 'new' keyword).
    // We put validations here to make sure the data is correct before we save the user.
    public User(int userId, String fullName, String identificationNumber, String email, 
                String phone, String address, UserRole role, UserStatus status, 
                String username, String password) {
        
        // --- Input Validation (Business Rules) ---
        // 1. Email must contain '@'
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("ERROR: Email is invalid. It must contain '@'.");
        }
        
        // 2. Phone number length must be between 7 and 15 characters (digits).
        if (phone == null || phone.length() < 7 || phone.length() > 15) {
            throw new IllegalArgumentException("ERROR: Phone number must be between 7 and 15 digits long.");
        }
        
        // 3. Full name cannot be empty or just spaces.
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Full name cannot be empty.");
        }
        
        // 4. Address cannot be empty.
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Address cannot be empty.");
        }

        // If all validations pass, we assign the values to our private fields.
        this.userId = userId;
        this.fullName = fullName;
        this.identificationNumber = identificationNumber;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.status = status;
        this.username = username;
        this.password = password;
    }

    // ---- Getters and Setters ----
    // These are public methods that allow other parts of the program to SEE (get) 
    // or MODIFY (set) the private fields in a controlled way.
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        // We can also add validation in setters if we want to allow changes later.
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Full name cannot be empty.");
        }
        this.fullName = fullName;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("ERROR: Email is invalid. It must contain '@'.");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.length() < 7 || phone.length() > 15) {
            throw new IllegalArgumentException("ERROR: Phone number must be between 7 and 15 digits long.");
        }
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Address cannot be empty.");
        }
        this.address = address;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ---- Helper method to check password (simple, for learning) ----
    public boolean checkPassword(String passwordToCheck) {
        return this.password.equals(passwordToCheck);
    }
}