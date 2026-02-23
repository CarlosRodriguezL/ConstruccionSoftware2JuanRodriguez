package clinic.model.clinical;

import java.util.Map;
import java.time.LocalDate;

public class ClinicalHistory {

    private String patientId;
    private Map<LocalDate, ClinicalRecord> records;
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public Map<LocalDate, ClinicalRecord> getRecords() {
		return records;
	}
	public void setRecords(Map<LocalDate, ClinicalRecord> records) {
		this.records = records;
	}
    
}
