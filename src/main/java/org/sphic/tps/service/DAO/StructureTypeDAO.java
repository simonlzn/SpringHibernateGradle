package org.sphic.tps.service.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.model.StructureType;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * StructureType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.sphic.tps.model.StructureType
 * @author MyEclipse Persistence Tools
 */
@Repository
public class StructureTypeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(StructureTypeDAO.class);
	// property constants
	public static final String NAME = "name";

	public void save(StructureType transientInstance) {
		log.debug("saving StructureType instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StructureType persistentInstance) {
		log.debug("deleting StructureType instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StructureType findById(java.lang.Integer id) {
		log.debug("getting StructureType instance with id: " + id);
		try {
			StructureType instance = (StructureType) getSession().get(
					"org.sphic.tps.model.StructureType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<StructureType> findByExample(StructureType instance) {
		log.debug("finding StructureType instance by example");
		try {
			List<StructureType> results = (List<StructureType>) getSession()
					.createCriteria("org.sphic.tps.model.StructureType")
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
		log.debug("finding StructureType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from StructureType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<StructureType> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all StructureType instances");
		try {
			String queryString = "from StructureType";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StructureType merge(StructureType detachedInstance) {
		log.debug("merging StructureType instance");
		try {
			StructureType result = (StructureType) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StructureType instance) {
		log.debug("attaching dirty StructureType instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StructureType instance) {
		log.debug("attaching clean StructureType instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}