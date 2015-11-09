package main.java.Model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="patient")
public class Patient {

	@Id
	@Column(name="id")
    private int id;
	@Column(name="name")
    private String name;
	@Column(name="address")
    private String address;
	@Column(name="phone")
    private String phone;
	
	@OneToMany(targetEntity = Study.class)
	@JoinColumn(name="patient_id")
	private List<Study> studies;

    public String getName() {
		return name;
	}

	public List<Study> getStudy() {
		return studies;
	}

	public void setStudy(List<Study> studies) {
		this.studies = studies;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Patient() {		
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public Patient(int id, String name, String address, String phone,
			List<Study> studies) {		
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.studies = studies;
	}

	public long getId() {
        return id;
    }
}
