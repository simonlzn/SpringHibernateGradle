package org.sphic.tps.service.DAO;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.model.FourDImagesSeries;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * FourDImagesSeries entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.sphic.tps.model.FourDImagesSeries
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FourDImagesSeriesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FourDImagesSeriesDAO.class);
	// property constants
	public static final String PHASE = "phase";
	public static final String IS_AVERAGE = "isAverage";
	public static final String IS_MIP = "isMip";
	public static final String IS_MINIP = "isMinip";

	public void save(FourDImagesSeries transientInstance) {
		log.debug("saving FourDImagesSeries instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FourDImagesSeries persistentInstance) {
		log.debug("deleting FourDImagesSeries instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FourDImagesSeries findById(java.lang.Integer id) {
		log.debug("getting FourDImagesSeries instance with id: " + id);
		try {
			FourDImagesSeries instance = (FourDImagesSeries) getSession().get(
					"org.sphic.tps.model.FourDImagesSeries", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<FourDImagesSeries> findByExample(FourDImagesSeries instance) {
		log.debug("finding FourDImagesSeries instance by example");
		try {
			List<FourDImagesSeries> results = (List<FourDImagesSeries>) getSession()
					.createCriteria("org.sphic.tps.model.FourDImagesSeries")
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
		log.debug("finding FourDImagesSeries instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FourDImagesSeries as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<FourDImagesSeries> findByPhase(Object phase) {
		return findByProperty(PHASE, phase);
	}

	public List<FourDImagesSeries> findByIsAverage(Object isAverage) {
		return findByProperty(IS_AVERAGE, isAverage);
	}

	public List<FourDImagesSeries> findByIsMip(Object isMip) {
		return findByProperty(IS_MIP, isMip);
	}

	public List<FourDImagesSeries> findByIsMinip(Object isMinip) {
		return findByProperty(IS_MINIP, isMinip);
	}

	public List findAll() {
		log.debug("finding all FourDImagesSeries instances");
		try {
			String queryString = "from FourDImagesSeries";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FourDImagesSeries merge(FourDImagesSeries detachedInstance) {
		log.debug("merging FourDImagesSeries instance");
		try {
			FourDImagesSeries result = (FourDImagesSeries) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FourDImagesSeries instance) {
		log.debug("attaching dirty FourDImagesSeries instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FourDImagesSeries instance) {
		log.debug("attaching clean FourDImagesSeries instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}