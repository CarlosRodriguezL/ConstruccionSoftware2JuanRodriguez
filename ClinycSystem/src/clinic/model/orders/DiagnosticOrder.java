package clinic.model.orders;

import clinic.enums.OrderItemType;

public class DiagnosticOrder extends OrderItem {

    private int quantity;
    private boolean requiresSpecialist;
    private Specialist specialist;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isRequiresSpecialist() {
		return requiresSpecialist;
	}
	public void setRequiresSpecialist(boolean requiresSpecialist) {
		this.requiresSpecialist = requiresSpecialist;
	}
	public Specialist getSpecialist() {
		return specialist;
	}
	public void setSpecialist(Specialist specialist) {
		this.specialist = specialist;
	}
    
	public void setName(String name) {
	this.name = name;
	}

	public void setCost(double cost) {
	this.cost = cost;
	}

	public void setType(OrderItemType type) {
	this.type = type;
	}
}
