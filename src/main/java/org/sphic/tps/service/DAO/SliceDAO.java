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
import org.sphic.tps.model.Slice;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for Slice
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.sphic.tps.model.Slice
 * @author MyEclipse Persistence Tools
 */

@Repository
public class SliceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SliceDAO.class);
	// property constants
	public static final String VIEW = "view";
	public static final String NUMBER = "number";
	public static final String ROW = "row";
	public static final String COLUMN = "column";
	public static final String ROWSPACING = "rowspacing";
	public static final String COLUMNSPACING = "columnspacing";
	public static final String DATA = "data";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	public void save(Slice transientInstance) {
		log.debug("saving Slice instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Slice persistentInstance) {
		log.debug("deleting Slice instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Slice findById(java.lang.Integer id) {
		log.debug("getting Slice instance with id: " + id);
		try {
			Slice instance = (Slice) getSession().get(
					"org.sphic.tps.model.Slice", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Slice> findByExample(Slice instance) {
		log.debug("finding Slice instance by example");
		try {
			List<Slice> results = (List<Slice>) getSession()
					.createCriteria("org.sphic.tps.model.Slice")
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
		log.debug("finding Slice instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Slice as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Slice> getBySeriesId(int id){
		Session session = HibernateUtil.currentSession();

		Criteria criteria = session.createCriteria(Slice.class).add(Restrictions.eq("series.id", id));
		List sliceList = criteria.list();

		return sliceList;
	}

	public Slice getByViewAndNumber(int seriesId, char view, int number){
		Session session = HibernateUtil.currentSession();

		Criteria cr = session.createCriteria(Slice.class).add(Restrictions.eq("series.id", seriesId)).add(Restrictions.eq("view", view)).add(Restrictions.eq("number", number));
		List<Slice> sliceList = cr.list();

		if (sliceList.isEmpty())
			return null;

		return sliceList.get(0);
	}

	public List<Slice> findByView(Object view) {
		return findByProperty(VIEW, view);
	}

	public List<Slice> findByNumber(Object number) {
		return findByProperty(NUMBER, number);
	}

	public List<Slice> findByRow(Object row) {
		return findByProperty(ROW, row);
	}

	public List<Slice> findByColumn(Object column) {
		return findByProperty(COLUMN, column);
	}

	public List<Slice> findByRowspacing(Object rowspacing) {
		return findByProperty(ROWSPACING, rowspacing);
	}

	public List<Slice> findByColumnspacing(Object columnspacing) {
		return findByProperty(COLUMNSPACING, columnspacing);
	}

	public List<Slice> findByData(Object data) {
		return findByProperty(DATA, data);
	}

	public List<Slice> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Slice> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all Slice instances");
		try {
			String queryString = "from Slice";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Slice merge(Slice detachedInstance) {
		log.debug("merging Slice instance");
		try {
			Slice result = (Slice) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Slice instance) {
		log.debug("attaching dirty Slice instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Slice instance) {
		log.debug("attaching clean Slice instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}