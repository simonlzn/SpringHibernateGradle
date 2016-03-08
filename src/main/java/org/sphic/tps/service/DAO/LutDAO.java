package org.sphic.tps.service.DAO;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.model.Lut;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for Lut
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.sphic.tps.model.Lut
 * @author MyEclipse Persistence Tools
 */
@Repository
public class LutDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(LutDAO.class);
	// property constants
	public static final String INDEX = "index";
	public static final String R = "r";
	public static final String G = "g";
	public static final String B = "b";

	public void save(Lut transientInstance) {
		log.debug("saving Lut instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Lut persistentInstance) {
		log.debug("deleting Lut instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Lut findById(java.lang.Integer id) {
		log.debug("getting Lut instance with id: " + id);
		try {
			Lut instance = (Lut) getSession()
					.get("org.sphic.tps.model.Lut", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Lut> findByExample(Lut instance) {
		log.debug("finding Lut instance by example");
		try {
			List<Lut> results = (List<Lut>) getSession()
					.createCriteria("org.sphic.tps.model.Lut")
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
		log.debug("finding Lut instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Lut as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Lut> findByIndex(Object index) {
		return findByProperty(INDEX, index);
	}

	public List<Lut> findByR(Object r) {
		return findByProperty(R, r);
	}

	public List<Lut> findByG(Object g) {
		return findByProperty(G, g);
	}

	public List<Lut> findByB(Object b) {
		return findByProperty(B, b);
	}

	public List findAll() {
		log.debug("finding all Lut instances");
		try {
			String queryString = "from Lut";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Lut merge(Lut detachedInstance) {
		log.debug("merging Lut instance");
		try {
			Lut result = (Lut) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Lut instance) {
		log.debug("attaching dirty Lut instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Lut instance) {
		log.debug("attaching clean Lut instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}