package org.sphic.tps.model;

import java.util.Date;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Study entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "study", catalog = "sphic")
public class Study implements java.io.Serializable {

	// Fields

	private Integer studyId;
	private Patient patient;
	private String accessionNumber;
	private String referringPhysicianName;
	private String institutionName;
	private String name;
	private String description;
	private String studyInsUid;
	private String studyInsId;
	private Date studyDateTime;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private Boolean isActive;
	private Set<FourDImages> fourDImageses = new HashSet<FourDImages>(0);
	private Set<Series> serieses = new HashSet<Series>(0);

	// Constructors

	/** default constructor */
	public Study() {
	}

	public Study(String study_ins_id, String studyInstantUID, Date created, Date updated, Date deleted, String name, String description, String accessionNumber, String institutionName, Set<Series> series) {
		this.studyInsId = study_ins_id;
		this.studyInsUid = studyInstantUID;
		this.createdAt = created;
		this.updatedAt = updated;
		this.deletedAt = deleted;
		this.name = name;
		this.description = description;
		this.accessionNumber = accessionNumber;
		this.institutionName = institutionName;
		this.serieses = series;
	}

	/** minimal constructor */
	public Study(Integer studyId) {
		this.studyId = studyId;
	}

	/** full constructor */
	public Study(Integer studyId, Patient patient, String accessionNumber,
			String referringPhysicianName, String institutionName, String name,
			String description, String studyInsUid, Date studyDateTime,
			Date createdAt, Date updatedAt, Date deletedAt,
			Boolean isActive, Set<FourDImages> fourDImageses,
			Set<Series> serieses) {
		this.studyId = studyId;
		this.patient = patient;
		this.accessionNumber = accessionNumber;
		this.referringPhysicianName = referringPhysicianName;
		this.institutionName = institutionName;
		this.name = name;
		this.description = description;
		this.studyInsUid = studyInsUid;
		this.studyDateTime = studyDateTime;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.isActive = isActive;
		this.fourDImageses = fourDImageses;
		this.serieses = serieses;
	}

	// Property accessors
	@Id
	@Column(name = "study_id", unique = true, nullable = false)
	public Integer getStudyId() {
		return this.studyId;
	}

	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Column(name = "accession_number", length = 100)
	public String getAccessionNumber() {
		return this.accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	@Column(name = "referring_physician_name", length = 100)
	public String getReferringPhysicianName() {
		return this.referringPhysicianName;
	}

	public void setReferringPhysicianName(String referringPhysicianName) {
		this.referringPhysicianName = referringPhysicianName;
	}

	@Column(name = "institution_name", length = 100)
	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 300)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "study_ins_uid", length = 800)
	public String getStudyInsUid() {
		return this.studyInsUid;
	}

	public void setStudyInsUid(String studyInsUid) {
		this.studyInsUid = studyInsUid;
	}

	@Column(name = "study_ins_id", length = 800)
	public String getStudyInsId() {
		return this.studyInsId;
	}

	public void setStudyInsId(String studyInsId) {
		this.studyInsId = studyInsId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "study_date_time", length = 10)
	public Date getStudyDateTime() {
		return this.studyDateTime;
	}

	public void setStudyDateTime(Date studyDateTime) {
		this.studyDateTime = studyDateTime;
	}

	@Column(name = "created_at", length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at", length = 19)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "deleted_at", length = 19)
	public Date getDeletedAt() {
		return this.deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Column(name = "is_active")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "study")
	public Set<FourDImages> getFourDImageses() {
		return this.fourDImageses;
	}

	public void setFourDImageses(Set<FourDImages> fourDImageses) {
		this.fourDImageses = fourDImageses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "study")
	public Set<Series> getSerieses() {
		return this.serieses;
	}

	public void setSerieses(Set<Series> serieses) {
		this.serieses = serieses;
	}

}