package org.sphic.tps.service.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.model.Study;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for Study
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.sphic.tps.model.Study
 * @author MyEclipse Persistence Tools
 */
@Repository
public class StudyDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(StudyDAO.class);
	// property constants
	public static final String ACCESSION_NUMBER = "accessionNumber";
	public static final String REFERRING_PHYSICIAN_NAME = "referringPhysicianName";
	public static final String INSTITUTION_NAME = "institutionName";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String STUDY_INS_UID = "studyInsUid";
	public static final String IS_ACTIVE = "isActive";

	public void save(Study transientInstance) {
		log.debug("saving Study instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Study persistentInstance) {
		log.debug("deleting Study instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Study findById(java.lang.Integer id) {
		log.debug("getting Study instance with id: " + id);
		try {
			Study instance = (Study) getSession().get(
					"org.sphic.tps.model.Study", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Study> findByExample(Study instance) {
		log.debug("finding Study instance by example");
		try {
			List<Study> results = (List<Study>) getSession()
					.createCriteria("org.sphic.tps.model.Study")
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
		log.debug("finding Study instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Study as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Study> findByAccessionNumber(Object accessionNumber) {
		return findByProperty(ACCESSION_NUMBER, accessionNumber);
	}

	public List<Study> findByReferringPhysicianName(
			Object referringPhysicianName) {
		return findByProperty(REFERRING_PHYSICIAN_NAME, referringPhysicianName);
	}

	public List<Study> findByInstitutionName(Object institutionName) {
		return findByProperty(INSTITUTION_NAME, institutionName);
	}

	public List<Study> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Study> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Study> findByStudyInsUid(Object studyInsUid) {
		return findByProperty(STUDY_INS_UID, studyInsUid);
	}

	public List<Study> findByIsActive(Object isActive) {
		return findByProperty(IS_ACTIVE, isActive);
	}

	public List findAll() {
		log.debug("finding all Study instances");
		try {
			String queryString = "from Study";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Study merge(Study detachedInstance) {
		log.debug("merging Study instance");
		try {
			Study result = (Study) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Study instance) {
		log.debug("attaching dirty Study instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Study instance) {
		log.debug("attaching clean Study instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}