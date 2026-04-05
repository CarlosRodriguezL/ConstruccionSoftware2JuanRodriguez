package infrastructure;

import domain.enums.TransferStatus;
import domain.model.User;
import domain.model.BankAccount;
import domain.model.Loan;
import domain.model.Transfer;
import domain.model.AuditLog;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase {
    

    public static List<User> users = new ArrayList<>();
    public static List<BankAccount> accounts = new ArrayList<>();
    public static List<Loan> loans = new ArrayList<>();
    public static List<Transfer> transfers = new ArrayList<>();
    public static List<AuditLog> auditLogs = new ArrayList<>();


    public static User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    public static User findUserByIdentification(String idNumber) {
        for (User user : users) {
            if (user.getIdentificationNumber().equals(idNumber)) {
                return user;
            }
        }
        return null;
    }


    public static BankAccount findAccountByNumber(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public static List<BankAccount> findAccountsByHolderId(String holderId) {
        List<BankAccount> result = new ArrayList<>();
        for (BankAccount account : accounts) {
            if (account.getHolderId().equals(holderId)) {
                result.add(account);
            }
        }
        return result;
    }


    public static Loan findLoanById(int loanId) {
        for (Loan loan : loans) {
            if (loan.getLoanId() == loanId) {
                return loan;
            }
        }
        return null;
    }

    public static Transfer findTransferById(int transferId) {
        for (Transfer transfer : transfers) {
            if (transfer.getTransferId() == transferId) {
                return transfer;
            }
        }
        return null;
    }

    public static List<Transfer> findTransfersWaitingApproval() {
        List<Transfer> result = new ArrayList<>();
        for (Transfer transfer : transfers) {
            if (transfer.getTransferStatus() == TransferStatus.WAITING_APPROVAL) {
                result.add(transfer);
            }
        }
        return result;
    }

    public static boolean isIdentificationNumberUnique(String idNumber) {
        return findUserByIdentification(idNumber) == null;
    }


    public static boolean isAccountNumberUnique(String accountNumber) {
        return findAccountByNumber(accountNumber) == null;
    }


    public static int getNextUserId() {
        return users.size() + 1;
    }


    public static int getNextLoanId() {
        return loans.size() + 1;
    }


    public static int getNextTransferId() {
        return transfers.size() + 1;
    }


    public static String getNextLogId() {
        return "LOG-" + (auditLogs.size() + 1);
    }
}