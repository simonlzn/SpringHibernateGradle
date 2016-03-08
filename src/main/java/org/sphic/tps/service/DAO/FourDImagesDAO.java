package org.sphic.tps.service.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.model.FourDImages;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * FourDImages entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.sphic.tps.model.FourDImages
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FourDImagesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FourDImagesDAO.class);
	// property constants
	public static final String PATH = "path";
	public static final String PHASE_NUM = "phaseNum";
	public static final String TIME_INTERVAL = "timeInterval";

	public void save(FourDImages transientInstance) {
		log.debug("saving FourDImages instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FourDImages persistentInstance) {
		log.debug("deleting FourDImages instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FourDImages findById(java.lang.Integer id) {
		log.debug("getting FourDImages instance with id: " + id);
		try {
			FourDImages instance = (FourDImages) getSession().get(
					"org.sphic.tps.model.FourDImages", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<FourDImages> findByExample(FourDImages instance) {
		log.debug("finding FourDImages instance by example");
		try {
			List<FourDImages> results = (List<FourDImages>) getSession()
					.createCriteria("org.sphic.tps.model.FourDImages")
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
		log.debug("finding FourDImages instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FourDImages as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<FourDImages> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List<FourDImages> findByPhaseNum(Object phaseNum) {
		return findByProperty(PHASE_NUM, phaseNum);
	}

	public List<FourDImages> findByTimeInterval(Object timeInterval) {
		return findByProperty(TIME_INTERVAL, timeInterval);
	}

	public List findAll() {
		log.debug("finding all FourDImages instances");
		try {
			String queryString = "from FourDImages";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FourDImages merge(FourDImages detachedInstance) {
		log.debug("merging FourDImages instance");
		try {
			FourDImages result = (FourDImages) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FourDImages instance) {
		log.debug("attaching dirty FourDImages instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FourDImages instance) {
		log.debug("attaching clean FourDImages instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}