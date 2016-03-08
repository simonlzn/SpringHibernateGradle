package org.sphic.tps.service.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.model.Patient;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Patient entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.sphic.tps.model.Patient
 * @author MyEclipse Persistence Tools
 */
@Repository
public class PatientDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(PatientDAO.class);
	// property constants
	public static final String AGE = "age";
	public static final String ADDRESS = "address";
	public static final String IS_ACTIVE = "isActive";
	public static final String PATIENT_CATAGORY = "patientCatagory";
	public static final String MEDICAL_RECORD_NUMBER = "medicalRecordNumber";
	public static final String TREATMENT_ID = "treatmentId";
	public static final String PATIENT_NAME = "patientName";
	public static final String SEX = "sex";
	public static final String TELEPHONE_NUMBER = "telephoneNumber";
	public static final String DEPARTMENT_CODE = "departmentCode";
	public static final String DEPARTMENT_NAME = "departmentName";
	public static final String WAR_CODE = "warCode";
	public static final String WARD_NAME = "wardName";
	public static final String BED_NUMBER = "bedNumber";
	public static final String DIAGNOSTIC_NAME = "diagnosticName";
	public static final String DIAGNOSTIC_CODE = "diagnosticCode";
	public static final String SOCIAL_SECURITY_CARD_NUMBER = "socialSecurityCardNumber";
	public static final String MEDICARE_CARD_NUMBER = "medicareCardNumber";
	public static final String HOSPITAL_INTERNAL_CARD_NUMBER = "hospitalInternalCardNumber";
	public static final String WEIGHT = "weight";
	public static final String HEIGHT = "height";
	public static final String BLOOD_TYPE = "bloodType";
	public static final String STATUS = "status";
	public static final String RESIDENT_NUMBER = "residentNumber";
	public static final String RESIDENT_NAME = "residentName";
	public static final String ATTENDING_NUMBER = "attendingNumber";
	public static final String ATTENDING_NAME = "attendingName";
	public static final String CHIEF_PHYSICIAN_NUMBER = "chiefPhysicianNumber";
	public static final String CHIEF_PHYSICIAN_NAME = "chiefPhysicianName";
	public static final String ID_CARD_NUMBER = "idCardNumber";
	public static final String PATIENT_MAIN_INDEX_ID = "patientMainIndexId";
	public static final String HOSPITAL_ADMISSION = "hospitalAdmission";
	public static final String PINYIN = "pinyin";

	public void save(Patient transientInstance) {
		log.debug("saving Patient instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Patient persistentInstance) {
		log.debug("deleting Patient instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Patient findById(java.lang.Integer id) {
		log.debug("getting Patient instance with id: " + id);
		try {
			Patient instance = (Patient) getSession().get(
					"org.sphic.tps.model.Patient", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Patient> findByExample(Patient instance) {
		log.debug("finding Patient instance by example");
		try {
			List<Patient> results = (List<Patient>) getSession()
					.createCriteria("org.sphic.tps.model.Patient")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Patient instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Patient as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Patient> findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	public List<Patient> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Patient> findByIsActive(Object isActive) {
		return findByProperty(IS_ACTIVE, isActive);
	}

	public List<Patient> findByPatientCatagory(Object patientCatagory) {
		return findByProperty(PATIENT_CATAGORY, patientCatagory);
	}

	public List<Patient> findByMedicalRecordNumber(Object medicalRecordNumber) {
		return findByProperty(MEDICAL_RECORD_NUMBER, medicalRecordNumber);
	}

	public List<Patient> findByTreatmentId(Object treatmentId) {
		return findByProperty(TREATMENT_ID, treatmentId);
	}

	public List<Patient> findByPatientName(Object patientName) {
		return findByProperty(PATIENT_NAME, patientName);
	}

	public List<Patient> findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List<Patient> findByTelephoneNumber(Object telephoneNumber) {
		return findByProperty(TELEPHONE_NUMBER, telephoneNumber);
	}

	public List<Patient> findByDepartmentCode(Object departmentCode) {
		return findByProperty(DEPARTMENT_CODE, departmentCode);
	}

	public List<Patient> findByDepartmentName(Object departmentName) {
		return findByProperty(DEPARTMENT_NAME, departmentName);
	}

	public List<Patient> findByWarCode(Object warCode) {
		return findByProperty(WAR_CODE, warCode);
	}

	public List<Patient> findByWardName(Object wardName) {
		return findByProperty(WARD_NAME, wardName);
	}

	public List<Patient> findByBedNumber(Object bedNumber) {
		return findByProperty(BED_NUMBER, bedNumber);
	}

	public List<Patient> findByDiagnosticName(Object diagnosticName) {
		return findByProperty(DIAGNOSTIC_NAME, diagnosticName);
	}

	public List<Patient> findByDiagnosticCode(Object diagnosticCode) {
		return findByProperty(DIAGNOSTIC_CODE, diagnosticCode);
	}

	public List<Patient> findBySocialSecurityCardNumber(
			Object socialSecurityCardNumber) {
		return findByProperty(SOCIAL_SECURITY_CARD_NUMBER,
				socialSecurityCardNumber);
	}

	public List<Patient> findByMedicareCardNumber(Object medicareCardNumber) {
		return findByProperty(MEDICARE_CARD_NUMBER, medicareCardNumber);
	}

	public List<Patient> findByHospitalInternalCardNumber(
			Object hospitalInternalCardNumber) {
		return findByProperty(HOSPITAL_INTERNAL_CARD_NUMBER,
				hospitalInternalCardNumber);
	}

	public List<Patient> findByWeight(Object weight) {
		return findByProperty(WEIGHT, weight);
	}

	public List<Patient> findByHeight(Object height) {
		return findByProperty(HEIGHT, height);
	}

	public List<Patient> findByBloodType(Object bloodType) {
		return findByProperty(BLOOD_TYPE, bloodType);
	}

	public List<Patient> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<Patient> findByResidentNumber(Object residentNumber) {
		return findByProperty(RESIDENT_NUMBER, residentNumber);
	}

	public List<Patient> findByResidentName(Object residentName) {
		return findByProperty(RESIDENT_NAME, residentName);
	}

	public List<Patient> findByAttendingNumber(Object attendingNumber) {
		return findByProperty(ATTENDING_NUMBER, attendingNumber);
	}

	public List<Patient> findByAttendingName(Object attendingName) {
		return findByProperty(ATTENDING_NAME, attendingName);
	}

	public List<Patient> findByChiefPhysicianNumber(Object chiefPhysicianNumber) {
		return findByProperty(CHIEF_PHYSICIAN_NUMBER, chiefPhysicianNumber);
	}

	public List<Patient> findByChiefPhysicianName(Object chiefPhysicianName) {
		return findByProperty(CHIEF_PHYSICIAN_NAME, chiefPhysicianName);
	}

	public List<Patient> findByIdCardNumber(Object idCardNumber) {
		return findByProperty(ID_CARD_NUMBER, idCardNumber);
	}

	public List<Patient> findByPatientMainIndexId(Object patientMainIndexId) {
		return findByProperty(PATIENT_MAIN_INDEX_ID, patientMainIndexId);
	}

	public List<Patient> findByHospitalAdmission(Object hospitalAdmission) {
		return findByProperty(HOSPITAL_ADMISSION, hospitalAdmission);
	}

	public List<Patient> findByPinyin(Object pinyin) {
		return findByProperty(PINYIN, pinyin);
	}

	public List findAll() {
		log.debug("finding all Patient instances");
		try {
			String queryString = "from Patient";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Patient merge(Patient detachedInstance) {
		log.debug("merging Patient instance");
		try {
			Patient result = (Patient) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Patient instance) {
		log.debug("attaching dirty Patient instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Patient instance) {
		log.debug("attaching clean Patient instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}