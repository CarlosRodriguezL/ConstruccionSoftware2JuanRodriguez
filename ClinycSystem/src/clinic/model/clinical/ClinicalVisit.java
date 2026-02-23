package clinic.model.clinical;

import clinic.model.people.Patient;
import clinic.model.people.User;

public class ClinicalVisit {

    private Patient patient;
    private User nurse;
    private VitalInfo vitalInfo;
    private String observations;
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public User getNurse() {
		return nurse;
	}
	public void setNurse(User nurse) {
		this.nurse = nurse;
	}
	public VitalInfo getVitalInfo() {
		return vitalInfo;
	}
	public void setVitalInfo(VitalInfo vitalInfo) {
		this.vitalInfo = vitalInfo;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
    
}
