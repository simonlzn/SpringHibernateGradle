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
@Table(name="study")
public class Study {
	@Id
	@Column(name="id")
	private int studyId;
	
	@Column(name="patient_id")
	private int patientId;	
	
	@Column(name="time")
	private Date time;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="physician")
	private String physician;
	
	@Column(name="modalities")
	private String modalities;
	
	@OneToMany(targetEntity = Series.class)
	@JoinColumn(name = "study_id")
	private List<Series> series;
	
	public Study(){
		
	}
		
	public Study(int studyId, int patientId, Date time, String comments,
			String physician, String modalities) {
		this.studyId = studyId;
		this.patientId = patientId;
		this.time = time;
		this.comments = comments;
		this.physician = physician;
		this.modalities = modalities;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getStudyId() {
		return studyId;
	}

	public void setStudyId(int studyId) {
		this.studyId = studyId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPhysician() {
		return physician;
	}

	public void setPhysician(String physician) {
		this.physician = physician;
	}

	public String getModalities() {
		return modalities;
	}

	public void setModalities(String modalities) {
		this.modalities = modalities;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}	
}
