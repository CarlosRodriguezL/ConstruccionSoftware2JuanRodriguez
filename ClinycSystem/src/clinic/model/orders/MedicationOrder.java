package clinic.model.orders;

public class MedicationOrder extends OrderItem {

    private String medicationId;
    private String dose;
    private int duration;
	public String getMedicationId() {
		return medicationId;
	}
	public void setMedicationId(String medicationId) {
		this.medicationId = medicationId;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
    
}
