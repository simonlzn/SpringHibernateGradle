package org.sphic.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="patient_profile")
public class Profile {
	@Id
	@Column(name="id")
	private int profileId;
	
	@Column(name="patient_id")
	private int patientId;	
	
	@Column(name="step")
	private String step;	
	
	@Column(name="staff_type")
	private String staffType;	
	
	@Column(name="staff")
	private String staff;	
	
	@Column(name="start_date")
	private Date startDate;	
	
	@Column(name="end_date")
	private Date endDate;	
	
	@Column(name="description")
	private String description;

	public Profile() {
		
	}

	public Profile(int profileId, int patientId, String step, String staffType,
			String staff, Date startDate, Date endDate, String description) {		
		this.profileId = profileId;
		this.patientId = patientId;
		this.step = step;
		this.staffType = staffType;
		this.staff = staff;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
