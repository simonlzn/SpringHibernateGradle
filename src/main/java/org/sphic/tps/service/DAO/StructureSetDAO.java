package org.sphic.tps.service.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.model.StructureSet;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * StructureSet entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.sphic.tps.model.StructureSet
 * @author MyEclipse Persistence Tools
 */
@Repository
public class StructureSetDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(StructureSetDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	public void save(StructureSet transientInstance) {
		log.debug("saving StructureSet instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StructureSet persistentInstance) {
		log.debug("deleting StructureSet instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StructureSet findById(java.lang.Integer id) {
		log.debug("getting StructureSet instance with id: " + id);
		try {
			StructureSet instance = (StructureSet) getSession().get(
					"org.sphic.tps.model.StructureSet", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<StructureSet> findByExample(StructureSet instance) {
		log.debug("finding StructureSet instance by example");
		try {
			List<StructureSet> results = (List<StructureSet>) getSession()
					.createCriteria("org.sphic.tps.model.StructureSet")
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
		log.debug("finding StructureSet instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from StructureSet as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<StructureSet> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<StructureSet> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all StructureSet instances");
		try {
			String queryString = "from StructureSet";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StructureSet merge(StructureSet detachedInstance) {
		log.debug("merging StructureSet instance");
		try {
			StructureSet result = (StructureSet) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StructureSet instance) {
		log.debug("attaching dirty StructureSet instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StructureSet instance) {
		log.debug("attaching clean StructureSet instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}