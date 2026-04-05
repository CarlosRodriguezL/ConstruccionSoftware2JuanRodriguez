package application;

import domain.model.Loan;
import domain.model.User;
import domain.model.BankAccount;
import domain.model.AuditLog;
import domain.enums.LoanStatus;
import domain.enums.UserRole;
import domain.enums.AccountStatus;
import infrastructure.InMemoryDatabase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// SERVICIO: LoanService
// Maneja préstamos: solicitar, aprobar, rechazar, desembolsar
public class LoanService {
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Solicitar un préstamo
    public void requestLoan(Loan loan, User requestingUser) {
        // Quiénes pueden solicitar
        if (requestingUser.getRole() != UserRole.NATURAL_PERSON_CLIENT &&
            requestingUser.getRole() != UserRole.COMPANY_CLIENT &&
            requestingUser.getRole() != UserRole.COMMERCIAL_EMPLOYEE) {
            System.out.println("ERROR: No tienes permiso para solicitar préstamos");
            return;
        }

        // Guardar préstamo
        InMemoryDatabase.loans.add(loan);
        
        // Bitácora
        String detail = "Préstamo solicitado: $" + loan.getRequestedAmount() + " Tipo: " + loan.getLoanType();
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "SOLICITUD_PRESTAMO",
            LocalDateTime.now().format(formatter),
            requestingUser.getUserId(),
            requestingUser.getRole().toString(),
            String.valueOf(loan.getLoanId()),
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Préstamo solicitado. ID: " + loan.getLoanId() + " - EN ESTUDIO");
    }

    // Aprobar préstamo (solo analista)
    public void approveLoan(int loanId, double approvedAmount, User analyst) {
        if (analyst.getRole() != UserRole.INTERNAL_ANALYST) {
            System.out.println("ERROR: Solo analistas pueden aprobar préstamos");
            return;
        }

        Loan loan = InMemoryDatabase.findLoanById(loanId);
        if (loan == null) {
            System.out.println("ERROR: Préstamo no encontrado");
            return;
        }

        if (loan.getLoanStatus() != LoanStatus.UNDER_STUDY) {
            System.out.println("ERROR: El préstamo no está EN ESTUDIO");
            return;
        }

        if (approvedAmount <= 0 || approvedAmount > loan.getRequestedAmount()) {
            System.out.println("ERROR: Monto aprobado inválido");
            return;
        }

        loan.setLoanStatus(LoanStatus.APPROVED);
        loan.setApprovedAmount(approvedAmount);
        loan.setApprovalDate(LocalDateTime.now().format(formatter));
        
        String detail = "Préstamo aprobado por: $" + approvedAmount;
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "APROBACION_PRESTAMO",
            LocalDateTime.now().format(formatter),
            analyst.getUserId(),
            analyst.getRole().toString(),
            String.valueOf(loanId),
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Préstamo " + loanId + " APROBADO por $" + approvedAmount);
    }

    // Rechazar préstamo (solo analista)
    public void rejectLoan(int loanId, User analyst) {
        if (analyst.getRole() != UserRole.INTERNAL_ANALYST) {
            System.out.println("ERROR: Solo analistas pueden rechazar préstamos");
            return;
        }

        Loan loan = InMemoryDatabase.findLoanById(loanId);
        if (loan == null) {
            System.out.println("ERROR: Préstamo no encontrado");
            return;
        }

        if (loan.getLoanStatus() != LoanStatus.UNDER_STUDY) {
            System.out.println("ERROR: El préstamo no está EN ESTUDIO");
            return;
        }

        loan.setLoanStatus(LoanStatus.REJECTED);
        
        String detail = "Préstamo rechazado";
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "RECHAZO_PRESTAMO",
            LocalDateTime.now().format(formatter),
            analyst.getUserId(),
            analyst.getRole().toString(),
            String.valueOf(loanId),
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Préstamo " + loanId + " RECHAZADO");
    }

    // Desembolsar préstamo (solo analista)
    public void disburseLoan(int loanId, String accountNumber, User analyst) {
        if (analyst.getRole() != UserRole.INTERNAL_ANALYST) {
            System.out.println("ERROR: Solo analistas pueden desembolsar préstamos");
            return;
        }

        Loan loan = InMemoryDatabase.findLoanById(loanId);
        if (loan == null) {
            System.out.println("ERROR: Préstamo no encontrado");
            return;
        }

        if (loan.getLoanStatus() != LoanStatus.APPROVED) {
            System.out.println("ERROR: El préstamo debe estar APROBADO");
            return;
        }

        BankAccount account = InMemoryDatabase.findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("ERROR: Cuenta no encontrada");
            return;
        }

        if (!account.getHolderId().equals(loan.getClientId())) {
            System.out.println("ERROR: La cuenta no pertenece al cliente");
            return;
        }

        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
            System.out.println("ERROR: La cuenta no está activa");
            return;
        }

        double amount = loan.getApprovedAmount();
        double before = account.getCurrentBalance();
        account.deposit(amount);

        loan.setLoanStatus(LoanStatus.DISBURSED);
        loan.setDisbursementDate(LocalDateTime.now().format(formatter));
        loan.setDisbursementAccountNumber(accountNumber);

        String detail = "Desembolso: $" + amount + " a cuenta " + accountNumber + " Saldo anterior: $" + before + " Nuevo: $" + account.getCurrentBalance();
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "DESEMBOLSO_PRESTAMO",
            LocalDateTime.now().format(formatter),
            analyst.getUserId(),
            analyst.getRole().toString(),
            String.valueOf(loanId),
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Préstamo " + loanId + " desembolsado: $" + amount + " a cuenta " + accountNumber);
    }
}