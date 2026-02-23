package clinic.model.orders;

public class ProcedureOrder extends OrderItem {

    private int quantity;
    private int frequency;
    private boolean requiresSpecialist;
    private Specialist specialist;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
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
    
}
