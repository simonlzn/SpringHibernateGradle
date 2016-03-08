package org.sphic.tps.service.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.hibernateConfig.HibernateUtil;
import org.sphic.tps.model.Series;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Series entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.sphic.tps.model.Series
 * @author MyEclipse Persistence Tools
 */
@Repository
public class SeriesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SeriesDAO.class);
	// property constants
	public static final String MODALITY = "modality";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String SERIES_NUMBER = "seriesNumber";
	public static final String MANUFACTURER = "manufacturer";
	public static final String MANUFCT_MODEL = "manufctModel";
	public static final String SERIES_INS_UID = "seriesInsUid";
	public static final String IS_ACTIVE = "isActive";

	public void save(Series transientInstance) {
		log.debug("saving Series instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Series persistentInstance) {
		log.debug("deleting Series instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Series findById(java.lang.Integer id) {
		log.debug("getting Series instance with id: " + id);
		try {
			Series instance = (Series) getSession().get(
					"org.sphic.tps.model.Series", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Series> findByExample(Series instance) {
		log.debug("finding Series instance by example");
		try {
			List<Series> results = (List<Series>) getSession()
					.createCriteria("org.sphic.tps.model.Series")
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
		log.debug("finding Series instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Series as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public Series getBySeriesUID(String uid){
		Session session = HibernateUtil.currentSession();
		Criteria criteria = session.createCriteria(Series.class).add(Restrictions.eq("seriesInsUID", uid));

		List seriesList = criteria.list();

		if (seriesList.isEmpty())
			return null;

		return (Series) seriesList.get(0);
	}

	public List<Series> findByModality(Object modality) {
		return findByProperty(MODALITY, modality);
	}

	public List<Series> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Series> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Series> findBySeriesNumber(Object seriesNumber) {
		return findByProperty(SERIES_NUMBER, seriesNumber);
	}

	public List<Series> findByManufacturer(Object manufacturer) {
		return findByProperty(MANUFACTURER, manufacturer);
	}

	public List<Series> findByManufctModel(Object manufctModel) {
		return findByProperty(MANUFCT_MODEL, manufctModel);
	}

	public List<Series> findBySeriesInsUid(Object seriesInsUid) {
		return findByProperty(SERIES_INS_UID, seriesInsUid);
	}

	public List<Series> findByIsActive(Object isActive) {
		return findByProperty(IS_ACTIVE, isActive);
	}

	public List findAll() {
		log.debug("finding all Series instances");
		try {
			String queryString = "from Series";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Series merge(Series detachedInstance) {
		log.debug("merging Series instance");
		try {
			Series result = (Series) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Series instance) {
		log.debug("attaching dirty Series instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Series instance) {
		log.debug("attaching clean Series instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}