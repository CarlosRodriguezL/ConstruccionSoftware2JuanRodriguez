package clinic.model.people;

import clinic.enums.Gender;

public class Patient extends Person {

    private Gender gender;
    private Contact emergencyContact;
    private Policy policy;
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Contact getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(Contact emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;

	}

	public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
}

public void setFullName(String fullName) {
    this.fullName = fullName;
}
    
}
