package org.sphic.tps.service.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.model.ReferencePointType;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReferencePointType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.sphic.tps.model.ReferencePointType
 * @author MyEclipse Persistence Tools
 */
@Repository
public class ReferencePointTypeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ReferencePointTypeDAO.class);
	// property constants
	public static final String NAME = "name";

	public void save(ReferencePointType transientInstance) {
		log.debug("saving ReferencePointType instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReferencePointType persistentInstance) {
		log.debug("deleting ReferencePointType instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReferencePointType findById(java.lang.Integer id) {
		log.debug("getting ReferencePointType instance with id: " + id);
		try {
			ReferencePointType instance = (ReferencePointType) getSession()
					.get("org.sphic.tps.model.ReferencePointType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ReferencePointType> findByExample(ReferencePointType instance) {
		log.debug("finding ReferencePointType instance by example");
		try {
			List<ReferencePointType> results = (List<ReferencePointType>) getSession()
					.createCriteria("org.sphic.tps.model.ReferencePointType")
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
		log.debug("finding ReferencePointType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReferencePointType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ReferencePointType> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all ReferencePointType instances");
		try {
			String queryString = "from ReferencePointType";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReferencePointType merge(ReferencePointType detachedInstance) {
		log.debug("merging ReferencePointType instance");
		try {
			ReferencePointType result = (ReferencePointType) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReferencePointType instance) {
		log.debug("attaching dirty ReferencePointType instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReferencePointType instance) {
		log.debug("attaching clean ReferencePointType instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}