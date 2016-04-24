package domainLayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="patients")
public class Patient implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;
	
	@NotNull
	@Size(min=13,max=13)
	@Pattern(regexp="^(0|[1-9][0-9]*)$")
	@Id
	private String personalNumericCode;
	
	@NotNull
	private Date dateOfBirth;
	
	@NotNull
	private String address;

	public Patient(String name, String personalNumericCode, Date dateOfBirth, String address) {
		super();
		this.name = name;
		this.personalNumericCode = personalNumericCode;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	
	public Patient(){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonalNumericCode() {
		return personalNumericCode;
	}

	public void setPersonalNumericCode(String personalNumericCode) {
		this.personalNumericCode = personalNumericCode;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((personalNumericCode == null) ? 0 : personalNumericCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (personalNumericCode == null) {
			if (other.personalNumericCode != null)
				return false;
		} else if (!personalNumericCode.equals(other.personalNumericCode))
			return false;
		return true;
	}

	 
	
	
	
}
