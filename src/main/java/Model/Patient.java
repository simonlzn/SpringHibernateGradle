package main.java.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "c_name")
	private String c_name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "birthdate")
	private Date birthdate;

	@OneToMany(targetEntity = Study.class)
	@JoinColumn(name = "patient_id")
	private List<Study> studies;
	
	public Patient(int id, String name, String c_name, String address,
			String phone, int age, Date birthdate, List<Study> studies) {
		this.id = id;
		this.name = name;
		this.c_name = c_name;
		this.address = address;
		this.phone = phone;
		this.age = age;
		this.birthdate = birthdate;
		this.studies = studies;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public List<Study> getStudies() {
		return studies;
	}

	public void setStudies(List<Study> studies) {
		this.studies = studies;
	}

	public String getName() {
		return name;
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

	public long getId() {
		return id;
	}
}
