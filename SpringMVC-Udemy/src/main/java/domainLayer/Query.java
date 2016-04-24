package domainLayer;

public class Query {

	private String doctorName;
	private String patientPersonalNumericCode;

	public Query(String doctorName, String patientPersonalNumericCode) {
		this.doctorName = doctorName;
		this.patientPersonalNumericCode = patientPersonalNumericCode;
	}
	
	public Query() {
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientPersonalNumericCode() {
		return patientPersonalNumericCode;
	}

	public void setPatientPersonalNumericCode(String patientPersonalNumericCode) {
		this.patientPersonalNumericCode = patientPersonalNumericCode;
	}
	
	
}
