package clinic.model.clinical;

public class VitalInfo {

    private String bloodPressure;
    private double temperature;
    private String pulse;
    private int oxygenLevel;
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public int getOxygenLevel() {
		return oxygenLevel;
	}
	public void setOxygenLevel(int oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}
    
}
