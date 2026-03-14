package application;

import domain.model.BankAccount;
import domain.model.User;
import domain.model.AuditLog;
import domain.enums.AccountStatus;
import domain.enums.UserRole;
import domain.enums.UserStatus;
import infrastructure.InMemoryDatabase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// SERVICIO: AccountService
// Maneja cuentas bancarias: abrir, depositar, retirar
public class AccountService {
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Abrir una nueva cuenta
    public void openAccount(BankAccount account, User requestingUser) {
        // Solo cajeros y asesores comerciales pueden abrir cuentas
        if (requestingUser.getRole() != UserRole.TELLER_EMPLOYEE && 
            requestingUser.getRole() != UserRole.COMMERCIAL_EMPLOYEE) {
            System.out.println("ERROR: No tienes permiso para abrir cuentas");
            return;
        }

        // Verificar número de cuenta único
        if (!InMemoryDatabase.isAccountNumberUnique(account.getAccountNumber())) {
            System.out.println("ERROR: El número de cuenta ya existe");
            return;
        }

        // Verificar que el cliente existe y está activo
        User client = InMemoryDatabase.findUserByIdentification(account.getHolderId());
        if (client == null) {
            System.out.println("ERROR: Cliente no encontrado");
            return;
        }
        if (client.getStatus() != UserStatus.ACTIVE) {
            System.out.println("ERROR: El cliente no está activo");
            return;
        }

        // Guardar cuenta
        InMemoryDatabase.accounts.add(account);
        
        // Bitácora
        String detail = "Cuenta abierta: " + account.getAccountNumber() + " Tipo: " + account.getAccountType();
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "APERTURA_CUENTA",
            LocalDateTime.now().format(formatter),
            requestingUser.getUserId(),
            requestingUser.getRole().toString(),
            account.getAccountNumber(),
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Cuenta " + account.getAccountNumber() + " abierta con éxito");
    }

    // Depositar dinero
    public void deposit(String accountNumber, double amount, User requestingUser) {
        if (amount <= 0) {
            System.out.println("ERROR: El monto debe ser mayor a cero");
            return;
        }

        BankAccount account = InMemoryDatabase.findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("ERROR: Cuenta no encontrada");
            return;
        }

        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
            System.out.println("ERROR: La cuenta no está activa");
            return;
        }

        double before = account.getCurrentBalance();
        account.deposit(amount);
        
        // Bitácora
        String detail = "Depósito: $" + amount + " Saldo anterior: $" + before + " Nuevo saldo: $" + account.getCurrentBalance();
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "DEPOSITO",
            LocalDateTime.now().format(formatter),
            requestingUser.getUserId(),
            requestingUser.getRole().toString(),
            accountNumber,
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Depósito exitoso. Nuevo saldo: $" + account.getCurrentBalance());
    }

    // Retirar dinero
    public void withdraw(String accountNumber, double amount, User requestingUser) {
        if (amount <= 0) {
            System.out.println("ERROR: El monto debe ser mayor a cero");
            return;
        }

        BankAccount account = InMemoryDatabase.findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("ERROR: Cuenta no encontrada");
            return;
        }

        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
            System.out.println("ERROR: La cuenta no está activa");
            return;
        }

        if (account.getCurrentBalance() < amount) {
            System.out.println("ERROR: Saldo insuficiente");
            return;
        }

        double before = account.getCurrentBalance();
        account.withdraw(amount);
        
        // Bitácora
        String detail = "Retiro: $" + amount + " Saldo anterior: $" + before + " Nuevo saldo: $" + account.getCurrentBalance();
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "RETIRO",
            LocalDateTime.now().format(formatter),
            requestingUser.getUserId(),
            requestingUser.getRole().toString(),
            accountNumber,
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Retiro exitoso. Nuevo saldo: $" + account.getCurrentBalance());
    }

    // Buscar cuenta por número
    public BankAccount findAccountByNumber(String accountNumber) {
        BankAccount account = InMemoryDatabase.findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("ERROR: Cuenta no encontrada");
        }
        return account;
    }
}