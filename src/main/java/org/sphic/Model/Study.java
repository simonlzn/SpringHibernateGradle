package org.sphic.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="study")
public class Study {
	@Id
	@Column(name="id")
	private int studyId;
	
	@Column(name="patient_id")
	private String patientId;

	@Column(name="study_ins_uid")
	private String study_ins_uid;

	@Column(name="created")
	private Date created;

	@Column(name="updated")
	private Date updated;

	@Column(name="deleted")
	private Date deleted;

	@Column(name="comments")
	private String comments;

	@Column(name="description")
	private String study_description;
	
	@Column(name="referring_physician_Name")
	private String physician;

	@Column(name="accession_number")
	private String accession_number;

//	@Column(name="modalities")
//	private String modalities;

    @Column(name = "institution_name")
	private String institutionName;
	
	@Transient
	@OneToMany(targetEntity = Series.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "study_id")
	private List<Series> series;
	
	public Study(){
		
	}

	public Study(int studyId, String patientId, String studyInstantUID, Date created, Date updated, Date deleted, String comments, String description, String physician, String accessionNumber, String institutionName, List<Series> series) {
		this.studyId = studyId;
		this.patientId = patientId;
		this.study_ins_uid = studyInstantUID;
		this.created = created;
		this.updated = updated;
		this.deleted = deleted;
		this.comments = comments;
		this.study_description = description;
		this.physician = physician;
		this.accession_number = accessionNumber;
		this.institutionName = institutionName;
		this.series = series;
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

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPhysician() {
		return physician;
	}

	public void setPhysician(String physician) {
		this.physician = physician;
	}

//	public String getModalities() {
//		return modalities;
//	}
//
//	public void setModalities(String modalities) {
//		this.modalities = modalities;
//	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getDeleted() {
		return deleted;
	}

	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

	public String getInstitutionName() { return this.institutionName; }

	public void setInstitutionName(String institutionName) { this.institutionName = institutionName;}

	public String getStudyInstantUID() { return this.study_ins_uid; }

	public void getStudyInstantUID(String studyInstantUID) { this.study_ins_uid = studyInstantUID;}
}
