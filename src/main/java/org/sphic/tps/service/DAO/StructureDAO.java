package org.sphic.tps.service.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.model.Structure;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Structure entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.sphic.tps.model.Structure
 * @author MyEclipse Persistence Tools
 */
@Repository
public class StructureDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(StructureDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String COLOR = "color";
	public static final String TRANSPARENCY = "transparency";
	public static final String VOLUME = "volume";
	public static final String IS_VISIBLE = "isVisible";
	public static final String IS_APPROVED = "isApproved";
	public static final String IS_LOCKED = "isLocked";

	public void save(Structure transientInstance) {
		log.debug("saving Structure instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Structure persistentInstance) {
		log.debug("deleting Structure instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Structure findById(java.lang.Integer id) {
		log.debug("getting Structure instance with id: " + id);
		try {
			Structure instance = (Structure) getSession().get(
					"org.sphic.tps.model.Structure", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Structure> findByExample(Structure instance) {
		log.debug("finding Structure instance by example");
		try {
			List<Structure> results = (List<Structure>) getSession()
					.createCriteria("org.sphic.tps.model.Structure")
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
		log.debug("finding Structure instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Structure as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Structure> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Structure> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Structure> findByColor(Object color) {
		return findByProperty(COLOR, color);
	}

	public List<Structure> findByTransparency(Object transparency) {
		return findByProperty(TRANSPARENCY, transparency);
	}

	public List<Structure> findByVolume(Object volume) {
		return findByProperty(VOLUME, volume);
	}

	public List<Structure> findByIsVisible(Object isVisible) {
		return findByProperty(IS_VISIBLE, isVisible);
	}

	public List<Structure> findByIsApproved(Object isApproved) {
		return findByProperty(IS_APPROVED, isApproved);
	}

	public List<Structure> findByIsLocked(Object isLocked) {
		return findByProperty(IS_LOCKED, isLocked);
	}

	public List findAll() {
		log.debug("finding all Structure instances");
		try {
			String queryString = "from Structure";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Structure merge(Structure detachedInstance) {
		log.debug("merging Structure instance");
		try {
			Structure result = (Structure) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Structure instance) {
		log.debug("attaching dirty Structure instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Structure instance) {
		log.debug("attaching clean Structure instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}