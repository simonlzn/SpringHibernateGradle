package main.java.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="study")
public class Study {
	@Id
	@Column(name="id")
	private int studyId;
	
	@Column(name="patient_id")
	private int patientId;	
	
	@Column(name="timestamp")
	private Date timestamp;
	
	@Column(name="comments")
	private String comments;
	
	public Study(){
		
	}
	
	public Study(int studyId, int patientId, Date timestamp, String comments) {		
		this.studyId = studyId;
		this.patientId = patientId;
		this.timestamp = timestamp;
		this.comments =comments;
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

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}	
}
