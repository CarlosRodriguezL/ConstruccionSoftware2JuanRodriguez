package presentation;

import domain.enums.*;
import domain.model.*;
import application.*;
import java.util.Scanner;

// CLASE PRINCIPAL: Main (versión INTERACTIVA simple)
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();

    public static void main(String[] args) {
        System.out.println("=== SISTEMA BANCARIO ===");
        
        // Pedir datos al usuario
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Identificación: ");
        String id = scanner.nextLine();
        
        // Crear cliente con los datos ingresados
        NaturalPersonClient cliente = new NaturalPersonClient(
            1,
            nombre,
            id,
            "email@test.com",
            "555-1234",
            "Dirección",
            UserStatus.ACTIVE,
            "usuario",
            "123",
            "15/05/1990"
        );
        
        userService.registerNaturalPerson(cliente);
        
        System.out.println("\n✓ Cliente registrado: " + cliente.getFullName());
        
        scanner.close();
    }
}