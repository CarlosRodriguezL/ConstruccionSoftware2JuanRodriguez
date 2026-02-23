package clinic.model.orders;

import clinic.model.people.Patient;
import clinic.model.people.User;
import java.time.LocalDate;
import java.util.List;

public class Order {

    private int orderNumber;
    private Patient patient;
    private User doctor;
    private LocalDate date;
    private List<OrderItem> items;
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public User getDoctor() {
		return doctor;
	}
	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
    
}
