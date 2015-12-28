package org.sphic.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="study")
@JsonIgnoreProperties(value={"patient", "series"})
public class Study {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String studyId;

	@ManyToOne(targetEntity = Patient.class)
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@Column(name="study_ins_uid")
	private String study_ins_uid;

	@Column(name="study_ins_id")
	private String study_ins_id;

	@Column(name="created")
	private Date created;

	@Column(name="updated")
	private Date updated;

	@Column(name="deleted")
	private Date deleted;

	@Column(name="name")
	private String name;

	@Column(name="description")
	private String study_description;
	
	@Column(name="referring_physician_Name")
	private String physician;

	@Column(name="accession_number")
	private String accession_number;

    @Column(name = "institution_name")
	private String institutionName;

	@OneToMany(targetEntity = Series.class, cascade = CascadeType.ALL, mappedBy = "study")
	private List<Series> series;
	
	public Study(){
		
	}

	public Study(String study_ins_id, String studyInstantUID, Date created, Date updated, Date deleted, String name, String description, String physician, String accessionNumber, String institutionName, List<Series> series) {
		this.study_ins_id = study_ins_id;
		this.study_ins_uid = studyInstantUID;
		this.created = created;
		this.updated = updated;
		this.deleted = deleted;
		this.name = name;
		this.study_description = description;
		this.physician = physician;
		this.accession_number = accessionNumber;
		this.institutionName = institutionName;
		this.series = series;
	}

	public String getStudy_ins_uid() {
		return study_ins_uid;
	}

	public void setStudy_ins_uid(String study_ins_uid) {
		this.study_ins_uid = study_ins_uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudy_description() {
		return study_description;
	}

	public void setStudy_description(String study_description) {
		this.study_description = study_description;
	}

	public String getAccession_number() {
		return accession_number;
	}

	public void setAccession_number(String accession_number) {
		this.accession_number = accession_number;
	}

	public String getStudyId() {
		return studyId;
	}

	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getPhysician() {
		return physician;
	}

	public void setPhysician(String physician) {
		this.physician = physician;
	}

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

	public String getStudy_ins_id() {
		return study_ins_id;
	}

	public void setStudy_ins_id(String study_ins_id) {
		this.study_ins_id = study_ins_id;
	}
}
