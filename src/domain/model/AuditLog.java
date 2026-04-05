package domain.model;

/**
 * CLASS: AuditLog
 * 
 * This class is for the "NoSQL" part of our project.
 * It represents a single entry in the audit log. Every important action
 * (like opening an account, approving a loan) will create one of these.
 * 
 * We store the details as a simple String, but imagine it's a JSON document.
 */
public class AuditLog {
    
    private String logId;               // Unique ID for this log entry
    private String operationType;        // What happened? (e.g., "LOAN_APPROVAL")
    private String operationDateTime;    // When did it happen? (DD/MM/YYYY HH:MM:SS)
    private int userId;                  // Who did it?
    private String userRole;             // What was their role at the time?
    private String affectedProductId;    // What was affected? (Account number, Loan ID, etc.)
    private String detailData;           // A simple string that holds extra info, like a mini-JSON.

    // Constructor
    public AuditLog(String logId, String operationType, String operationDateTime, 
                    int userId, String userRole, String affectedProductId, String detailData) {
        
        // Simple validations
        if (logId == null || logId.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Log ID cannot be empty.");
        }
        if (operationType == null || operationType.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Operation type cannot be empty.");
        }
        if (operationDateTime == null || operationDateTime.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Operation date/time cannot be empty.");
        }
        if (userId <= 0) {
            throw new IllegalArgumentException("ERROR: User ID must be a positive number.");
        }
        if (userRole == null || userRole.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: User role cannot be empty.");
        }
        if (affectedProductId == null || affectedProductId.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Affected product ID cannot be empty.");
        }

        this.logId = logId;
        this.operationType = operationType;
        this.operationDateTime = operationDateTime;
        this.userId = userId;
        this.userRole = userRole;
        this.affectedProductId = affectedProductId;
        this.detailData = detailData; // This can be null, it's optional.
    }

    // Getters and Setters
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        if (logId == null || logId.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Log ID cannot be empty.");
        }
        this.logId = logId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        if (operationType == null || operationType.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Operation type cannot be empty.");
        }
        this.operationType = operationType;
    }

    public String getOperationDateTime() {
        return operationDateTime;
    }

    public void setOperationDateTime(String operationDateTime) {
        if (operationDateTime == null || operationDateTime.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Operation date/time cannot be empty.");
        }
        this.operationDateTime = operationDateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("ERROR: User ID must be a positive number.");
        }
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        if (userRole == null || userRole.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: User role cannot be empty.");
        }
        this.userRole = userRole;
    }

    public String getAffectedProductId() {
        return affectedProductId;
    }

    public void setAffectedProductId(String affectedProductId) {
        if (affectedProductId == null || affectedProductId.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Affected product ID cannot be empty.");
        }
        this.affectedProductId = affectedProductId;
    }

    public String getDetailData() {
        return detailData;
    }

    public void setDetailData(String detailData) {
        this.detailData = detailData;
    }
}