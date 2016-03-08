package org.sphic.tps.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Patient entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "patient", catalog = "sphic")
public class Patient implements java.io.Serializable {

	// Fields

	private Integer patientId;
	private Date birthdate;
	private Integer age;
	private String address;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private Boolean isActive;
	private Integer patientCatagory;
	private String medicalRecordNumber;
	private Integer treatmentId;
	private String patientName;
	private Integer sex;
	private String telephoneNumber;
	private String departmentCode;
	private String departmentName;
	private String warCode;
	private String wardName;
	private String bedNumber;
	private String diagnosticName;
	private String diagnosticCode;
	private String socialSecurityCardNumber;
	private String medicareCardNumber;
	private String hospitalInternalCardNumber;
	private Integer weight;
	private Integer height;
	private String bloodType;
	private Date admissionDate;
	private Date dischargeDate;
	private Integer status;
	private String residentNumber;
	private String residentName;
	private String attendingNumber;
	private String attendingName;
	private String chiefPhysicianNumber;
	private String chiefPhysicianName;
	private String idCardNumber;
	private String patientMainIndexId;
	private Integer hospitalAdmission;
	private String pinyin;
	private Set<Study> studies = new HashSet<Study>(0);

	// Constructors

	/** default constructor */
	public Patient() {
	}

	public Patient(int id, String name, String c_name, String address,
				   String phone, int age, Date birthdate,/* String institutionName,*/ Set<Study> studies) {
		this.patientId = id;
		this.patientName = name;
		this.pinyin = c_name;
		this.address = address;
		this.telephoneNumber = phone;
		this.age = age;
		this.birthdate = birthdate;
		this.studies = studies;
	}
	/** minimal constructor */
	public Patient(Integer patientId) {
		this.patientId = patientId;
	}

	/** full constructor */
	public Patient(Integer patientId, Date birthdate, Integer age,
			String address, Date createdAt, Date updatedAt,
			Date deletedAt, Boolean isActive, Integer patientCatagory,
			String medicalRecordNumber, Integer treatmentId,
			String patientName, Integer sex, String telephoneNumber,
			String departmentCode, String departmentName, String warCode,
			String wardName, String bedNumber, String diagnosticName,
			String diagnosticCode, String socialSecurityCardNumber,
			String medicareCardNumber, String hospitalInternalCardNumber,
			Integer weight, Integer height, String bloodType,
			Date admissionDate, Date dischargeDate, Integer status,
			String residentNumber, String residentName, String attendingNumber,
			String attendingName, String chiefPhysicianNumber,
			String chiefPhysicianName, String idCardNumber,
			String patientMainIndexId, Integer hospitalAdmission,
			String pinyin, Set<Study> studies) {
		this.patientId = patientId;
		this.birthdate = birthdate;
		this.age = age;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.isActive = isActive;
		this.patientCatagory = patientCatagory;
		this.medicalRecordNumber = medicalRecordNumber;
		this.treatmentId = treatmentId;
		this.patientName = patientName;
		this.sex = sex;
		this.telephoneNumber = telephoneNumber;
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		this.warCode = warCode;
		this.wardName = wardName;
		this.bedNumber = bedNumber;
		this.diagnosticName = diagnosticName;
		this.diagnosticCode = diagnosticCode;
		this.socialSecurityCardNumber = socialSecurityCardNumber;
		this.medicareCardNumber = medicareCardNumber;
		this.hospitalInternalCardNumber = hospitalInternalCardNumber;
		this.weight = weight;
		this.height = height;
		this.bloodType = bloodType;
		this.admissionDate = admissionDate;
		this.dischargeDate = dischargeDate;
		this.status = status;
		this.residentNumber = residentNumber;
		this.residentName = residentName;
		this.attendingNumber = attendingNumber;
		this.attendingName = attendingName;
		this.chiefPhysicianNumber = chiefPhysicianNumber;
		this.chiefPhysicianName = chiefPhysicianName;
		this.idCardNumber = idCardNumber;
		this.patientMainIndexId = patientMainIndexId;
		this.hospitalAdmission = hospitalAdmission;
		this.pinyin = pinyin;
		this.studies = studies;
	}

	// Property accessors
	@Id
	@Column(name = "patient_id", unique = true, nullable = false)
	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	@Column(name = "birthdate", length = 19)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Column(name = "patient_catagory")
	public Integer getPatientCatagory() {
		return this.patientCatagory;
	}

	public void setPatientCatagory(Integer patientCatagory) {
		this.patientCatagory = patientCatagory;
	}

	@Column(name = "medical_record_number", length = 40)
	public String getMedicalRecordNumber() {
		return this.medicalRecordNumber;
	}

	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	@Column(name = "treatment_id")
	public Integer getTreatmentId() {
		return this.treatmentId;
	}

	public void setTreatmentId(Integer treatmentId) {
		this.treatmentId = treatmentId;
	}

	@Column(name = "patient_name", length = 40)
	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "telephone_number", length = 40)
	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Column(name = "department_code", length = 40)
	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	@Column(name = "department_name", length = 40)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "war_code", length = 40)
	public String getWarCode() {
		return this.warCode;
	}

	public void setWarCode(String warCode) {
		this.warCode = warCode;
	}

	@Column(name = "ward_name", length = 40)
	public String getWardName() {
		return this.wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	@Column(name = "bed_number", length = 40)
	public String getBedNumber() {
		return this.bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	@Column(name = "diagnostic_name", length = 40)
	public String getDiagnosticName() {
		return this.diagnosticName;
	}

	public void setDiagnosticName(String diagnosticName) {
		this.diagnosticName = diagnosticName;
	}

	@Column(name = "diagnostic_code", length = 40)
	public String getDiagnosticCode() {
		return this.diagnosticCode;
	}

	public void setDiagnosticCode(String diagnosticCode) {
		this.diagnosticCode = diagnosticCode;
	}

	@Column(name = "social_security_card_number", length = 40)
	public String getSocialSecurityCardNumber() {
		return this.socialSecurityCardNumber;
	}

	public void setSocialSecurityCardNumber(String socialSecurityCardNumber) {
		this.socialSecurityCardNumber = socialSecurityCardNumber;
	}

	@Column(name = "medicare_card_number", length = 40)
	public String getMedicareCardNumber() {
		return this.medicareCardNumber;
	}

	public void setMedicareCardNumber(String medicareCardNumber) {
		this.medicareCardNumber = medicareCardNumber;
	}

	@Column(name = "hospital_internal_card_number", length = 40)
	public String getHospitalInternalCardNumber() {
		return this.hospitalInternalCardNumber;
	}

	public void setHospitalInternalCardNumber(String hospitalInternalCardNumber) {
		this.hospitalInternalCardNumber = hospitalInternalCardNumber;
	}

	@Column(name = "weight")
	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Column(name = "height")
	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@Column(name = "blood_type", length = 2)
	public String getBloodType() {
		return this.bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	@Column(name = "admission_date", length = 19)
	public Date getAdmissionDate() {
		return this.admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	@Column(name = "discharge_date", length = 19)
	public Date getDischargeDate() {
		return this.dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "resident_number", length = 40)
	public String getResidentNumber() {
		return this.residentNumber;
	}

	public void setResidentNumber(String residentNumber) {
		this.residentNumber = residentNumber;
	}

	@Column(name = "resident_name", length = 40)
	public String getResidentName() {
		return this.residentName;
	}

	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}

	@Column(name = "attending_number", length = 40)
	public String getAttendingNumber() {
		return this.attendingNumber;
	}

	public void setAttendingNumber(String attendingNumber) {
		this.attendingNumber = attendingNumber;
	}

	@Column(name = "attending_name", length = 40)
	public String getAttendingName() {
		return this.attendingName;
	}

	public void setAttendingName(String attendingName) {
		this.attendingName = attendingName;
	}

	@Column(name = "chief_physician_number", length = 40)
	public String getChiefPhysicianNumber() {
		return this.chiefPhysicianNumber;
	}

	public void setChiefPhysicianNumber(String chiefPhysicianNumber) {
		this.chiefPhysicianNumber = chiefPhysicianNumber;
	}

	@Column(name = "chief_physician_name", length = 40)
	public String getChiefPhysicianName() {
		return this.chiefPhysicianName;
	}

	public void setChiefPhysicianName(String chiefPhysicianName) {
		this.chiefPhysicianName = chiefPhysicianName;
	}

	@Column(name = "id_card_number", length = 40)
	public String getIdCardNumber() {
		return this.idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	@Column(name = "patient_main_index_id", length = 40)
	public String getPatientMainIndexId() {
		return this.patientMainIndexId;
	}

	public void setPatientMainIndexId(String patientMainIndexId) {
		this.patientMainIndexId = patientMainIndexId;
	}

	@Column(name = "hospital_admission")
	public Integer getHospitalAdmission() {
		return this.hospitalAdmission;
	}

	public void setHospitalAdmission(Integer hospitalAdmission) {
		this.hospitalAdmission = hospitalAdmission;
	}

	@Column(name = "pinyin", length = 40)
	public String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Study> getStudies() {
		return this.studies;
	}

	public void setStudies(Set<Study> studies) {
		this.studies = studies;
	}

}