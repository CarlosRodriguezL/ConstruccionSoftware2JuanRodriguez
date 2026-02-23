package clinic.model.service;

import clinic.model.orders.*;
import clinic.model.billing.Invoice;
import clinic.model.people.Patient;
import clinic.model.people.Policy;

import java.time.LocalDate;
import java.util.List;

public class OrderService {

    private static final double COPAY_VALUE = 50000;
    private static final double COPAY_YEAR_LIMIT = 1_000_000;

    public Invoice createOrder(Order order) {

        validateOrder(order);

        double totalCost = calculateTotal(order.getItems());

        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setPatient(order.getPatient());
        invoice.setDoctor(order.getDoctor());
        invoice.setDate(LocalDate.now());

        processBilling(order.getPatient(), invoice, totalCost);

        return invoice;
    }

    private void validateOrder(Order order) {

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new RuntimeException("La orden debe tener al menos un servicio");
        }

        for (OrderItem item : order.getItems()) {

            if (item.getName() == null || item.getName().isEmpty()) {
                throw new RuntimeException("Todos los ítems deben tener nombre");
            }

            if (item instanceof MedicationOrder med) {
                if (med.getDose() == null || med.getDose().isEmpty()) {
                    throw new RuntimeException(
                        "El medicamento debe incluir dosis aplicada"
                    );
                }
            }
        }
    }

    private double calculateTotal(List<OrderItem> items) {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getCost();
        }

        return total;
    }

    private void processBilling(Patient patient,
                                Invoice invoice,
                                double totalCost) {

        Policy policy = patient.getPolicy();

        if (policy == null || !policy.isActive()) {
            invoice.setCopay(0);
            invoice.setTotal(totalCost);
            return;
        }

        double copay = COPAY_VALUE;

        double accumulatedCopayYear = 0;

        if (accumulatedCopayYear >= COPAY_YEAR_LIMIT) {
            copay = 0;
        }

        invoice.setCopay(copay);
        invoice.setTotal(totalCost);
    }
}
