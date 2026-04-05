package application;

import domain.model.Transfer;
import domain.model.User;
import domain.model.BankAccount;
import domain.model.AuditLog;
import domain.enums.TransferStatus;
import domain.enums.UserRole;
import domain.enums.AccountStatus;
import infrastructure.InMemoryDatabase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.List;

// SERVICIO: TransferService
// Maneja transferencias: crear, aprobar, rechazar, verificar vencidas
public class TransferService {
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final double MONTO_MAXIMO_SIN_APROBACION = 1000000.00; // $1,000,000

    // Crear una transferencia
    public void createTransfer(Transfer transfer, User requestingUser) {
        // Verificar cuentas
        BankAccount origin = InMemoryDatabase.findAccountByNumber(transfer.getOriginAccount());
        BankAccount dest = InMemoryDatabase.findAccountByNumber(transfer.getDestinationAccount());
        
        if (origin == null || dest == null) {
            System.out.println("ERROR: Cuenta de origen o destino no existe");
            return;
        }

        if (origin.getAccountStatus() != AccountStatus.ACTIVE) {
            System.out.println("ERROR: La cuenta de origen no está activa");
            return;
        }

        // Determinar si necesita aprobación

        
        if (transfer.getAmount() > MONTO_MAXIMO_SIN_APROBACION && 
            requestingUser.getRole() == UserRole.COMPANY_EMPLOYEE) {

            transfer.setTransferStatus(TransferStatus.WAITING_APPROVAL);
            System.out.println("⚠ Transferencia requiere aprobación del supervisor");
        } else {
            // Transferencia normal: ejecutar inmediato
            if (origin.getCurrentBalance() < transfer.getAmount()) {
                System.out.println("ERROR: Saldo insuficiente");
                return;
            }
            
            transfer.setTransferStatus(TransferStatus.EXECUTED);
            

            
            origin.withdraw(transfer.getAmount());
            dest.deposit(transfer.getAmount());
            
            String detail = "Transferencia ejecutada: $" + transfer.getAmount() + 
                           " Origen: " + transfer.getOriginAccount() + 
                           " Destino: " + transfer.getDestinationAccount();
            AuditLog log = new AuditLog(
                InMemoryDatabase.getNextLogId(),
                "TRANSFERENCIA_EJECUTADA",
                LocalDateTime.now().format(formatter),
                requestingUser.getUserId(),
                requestingUser.getRole().toString(),
                String.valueOf(transfer.getTransferId()),
                detail
            );
            InMemoryDatabase.auditLogs.add(log);
            
            System.out.println("✓ Transferencia ejecutada con éxito");
        }

        // Guardar transferencia
        InMemoryDatabase.transfers.add(transfer);
    }

    // Aprobar transferencia (solo supervisor de empresa)
    public void approveTransfer(int transferId, User supervisor) {
        if (supervisor.getRole() != UserRole.COMPANY_SUPERVISOR) {
            System.out.println("ERROR: Solo supervisores de empresa pueden aprobar");
            return;
        }

        Transfer transfer = InMemoryDatabase.findTransferById(transferId);
        if (transfer == null) {
            System.out.println("ERROR: Transferencia no encontrada");
            return;
        }

        if (transfer.getTransferStatus() != TransferStatus.WAITING_APPROVAL) {
            System.out.println("ERROR: La transferencia no está esperando aprobación");
            return;
        }

        BankAccount origin = InMemoryDatabase.findAccountByNumber(transfer.getOriginAccount());
        BankAccount dest = InMemoryDatabase.findAccountByNumber(transfer.getDestinationAccount());

        if (origin.getCurrentBalance() < transfer.getAmount()) {
            System.out.println("ERROR: Saldo insuficiente en cuenta de origen");
            return;
        }

        
        origin.withdraw(transfer.getAmount());
        dest.deposit(transfer.getAmount());
        
        transfer.setTransferStatus(TransferStatus.EXECUTED);
        transfer.setApprovalDateTime(LocalDateTime.now().format(formatter));
        transfer.setApproverUserId(supervisor.getUserId());

        String detail = "Transferencia aprobada por supervisor: $" + transfer.getAmount();
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "TRANSFERENCIA_APROBADA",
            LocalDateTime.now().format(formatter),
            supervisor.getUserId(),
            supervisor.getRole().toString(),
            String.valueOf(transferId),
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Transferencia " + transferId + " aprobada y ejecutada");
    }

    // Rechazar transferencia
    public void rejectTransfer(int transferId, User supervisor) {
        if (supervisor.getRole() != UserRole.COMPANY_SUPERVISOR) {
            System.out.println("ERROR: Solo supervisores de empresa pueden rechazar");
            return;
        }

        Transfer transfer = InMemoryDatabase.findTransferById(transferId);
        if (transfer == null) {
            System.out.println("ERROR: Transferencia no encontrada");
            return;
        }

        transfer.setTransferStatus(TransferStatus.REJECTED);
        transfer.setApprovalDateTime(LocalDateTime.now().format(formatter));
        transfer.setApproverUserId(supervisor.getUserId());

        String detail = "Transferencia rechazada por supervisor";
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "TRANSFERENCIA_RECHAZADA",
            LocalDateTime.now().format(formatter),
            supervisor.getUserId(),
            supervisor.getRole().toString(),
            String.valueOf(transferId),
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Transferencia " + transferId + " RECHAZADA");
    }

    // Verificar transferencias vencidas (más de 1 hora esperando)
    public void checkExpiredTransfers() {
        List<Transfer> waiting = InMemoryDatabase.findTransfersWaitingApproval();
        LocalDateTime now = LocalDateTime.now();
        int vencidas = 0;
        
        for (Transfer t : waiting) {
            LocalDateTime creation = LocalDateTime.parse(t.getCreationDateTime(), formatter);
            long minutes = Duration.between(creation, now).toMinutes();
            
            if (minutes >= 60) {
                t.setTransferStatus(TransferStatus.EXPIRED);
                
                String detail = "Transferencia vencida por falta de aprobación (" + minutes + " minutos)";
                AuditLog log = new AuditLog(
                    InMemoryDatabase.getNextLogId(),
                    "TRANSFERENCIA_VENCIDA",
                    LocalDateTime.now().format(formatter),
                    0, // sistema
                    "SISTEMA",
                    String.valueOf(t.getTransferId()),
                    detail
                );
                InMemoryDatabase.auditLogs.add(log);
                vencidas++;
            }
        }
        
        if (vencidas > 0) {
            System.out.println("⏰ " + vencidas + " transferencias vencidas por tiempo");
        }
    }
}