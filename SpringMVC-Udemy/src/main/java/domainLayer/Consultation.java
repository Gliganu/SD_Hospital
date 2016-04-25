package domainLayer;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="consultations")
public class Consultation {

	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "patient")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "doctor")
	private User user;
	
	@Min(30)
	@Max(120)
	private int length;
	
	private String notes;
	
	private String doctorName;
	private String patientPersonalNumericCode;
	
	private boolean alertedDoctor = false;
	
	public Consultation(){
		
	}

	public Consultation(Date date, Patient patient, User user, String notes,int length) {
		this.date = date;
		this.patient = patient;
		this.user = user;
		this.notes = notes;
		this.length = length;
		this.doctorName = user.getName();
		this.patientPersonalNumericCode = patient.getPersonalNumericCode();
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isAlertedDoctor() {
		return alertedDoctor;
	}

	public void setAlertedDoctor(boolean alertedDoctor) {
		this.alertedDoctor = alertedDoctor;
	}

	
	
	
	
	
	
	
	
}
