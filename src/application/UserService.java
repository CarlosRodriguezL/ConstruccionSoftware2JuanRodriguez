package application;

import domain.model.User;
import domain.model.NaturalPersonClient;
import domain.model.CompanyClient;
import domain.model.AuditLog;
import domain.enums.UserStatus;
import infrastructure.InMemoryDatabase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// SERVICIO: UserService
// Maneja todo lo relacionado con usuarios: registro, login, búsqueda
public class UserService {
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Registra un cliente persona natural
    public void registerNaturalPerson(NaturalPersonClient client) {
        // Verificar que la identificación no exista
        if (!InMemoryDatabase.isIdentificationNumberUnique(client.getIdentificationNumber())) {
            System.out.println("ERROR: La identificación ya existe");
            return;
        }

        // Guardar en la base de datos
        InMemoryDatabase.users.add(client);
        
        // Crear registro en bitácora
        String detail = "Registro de cliente persona natural: " + client.getFullName();
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "REGISTRO_CLIENTE",
            LocalDateTime.now().format(formatter),
            client.getUserId(),
            client.getRole().toString(),
            client.getIdentificationNumber(),
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Cliente registrado con éxito. ID: " + client.getUserId());
    }

    // Registra una empresa
    public void registerCompany(CompanyClient company) {
        // Verificar que el NIT no exista
        if (!InMemoryDatabase.isIdentificationNumberUnique(company.getIdentificationNumber())) {
            System.out.println("ERROR: El NIT ya existe");
            return;
        }

        // Verificar que el representante legal existe
        User rep = InMemoryDatabase.findUserById(company.getLegalRepresentativeId());
        if (rep == null) {
            System.out.println("ERROR: Representante legal no encontrado");
            return;
        }

        // Guardar
        InMemoryDatabase.users.add(company);
        
        // Bitácora
        String detail = "Registro de empresa: " + company.getBusinessName();
        AuditLog log = new AuditLog(
            InMemoryDatabase.getNextLogId(),
            "REGISTRO_EMPRESA",
            LocalDateTime.now().format(formatter),
            company.getUserId(),
            company.getRole().toString(),
            company.getIdentificationNumber(),
            detail
        );
        InMemoryDatabase.auditLogs.add(log);
        
        System.out.println("✓ Empresa registrada con éxito. ID: " + company.getUserId());
    }

    // Login de usuario
    public User login(String username, String password) {
        User user = InMemoryDatabase.findUserByUsername(username);
        
        if (user == null) {
            System.out.println("ERROR: Usuario no encontrado");
            return null;
        }
        
        if (!user.checkPassword(password)) {
            System.out.println("ERROR: Contraseña incorrecta");
            return null;
        }
        
        if (user.getStatus() != UserStatus.ACTIVE) {
            System.out.println("ERROR: Usuario no está activo");
            return null;
        }
        
        System.out.println("✓ Bienvenido " + user.getFullName());
        return user;
    }

    // Busca usuario por identificación
    public User findUserByIdentification(String idNumber) {
        User user = InMemoryDatabase.findUserByIdentification(idNumber);
        if (user == null) {
            System.out.println("ERROR: Usuario no encontrado");
        }
        return user;
    }
}