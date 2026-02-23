package clinic.main;

import clinic.model.orders.*;
import clinic.model.people.*;
import clinic.enums.*;
import clinic.model.service.OrderService;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        OrderService service = new OrderService();

        Patient patient = new Patient();
        patient.setIdNumber("123");
        patient.setFullName("Ana López");

        User doctor = new User();
        doctor.setFullName("Dr. Pérez");
        doctor.setUsername("doctor1");
        doctor.setRole(Role.DOCTOR);

        DiagnosticOrder diagnostic = new DiagnosticOrder();
        diagnostic.setName("Examen de sangre");
        diagnostic.setCost(80000);
        diagnostic.setQuantity(1);
        diagnostic.setType(OrderItemType.DIAGNOSTIC);

        Order order = new Order();
        order.setOrderNumber(1);
        order.setPatient(patient);
        order.setDoctor(doctor);
        order.setItems(new ArrayList<>());

        order.getItems().add(diagnostic);

        service.createOrder(order);

        System.out.println("Orden creada correctamente");
    }
}