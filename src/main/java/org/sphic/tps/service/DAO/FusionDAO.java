package org.sphic.tps.service.DAO;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sphic.tps.model.Fusion;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fusion entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.sphic.tps.model.Fusion
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FusionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(FusionDAO.class);
	// property constants
	public static final String IS_RIGID = "isRigid";
	public static final String IS_DEFORMABLE = "isDeformable";

	public void save(Fusion transientInstance) {
		log.debug("saving Fusion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Fusion persistentInstance) {
		log.debug("deleting Fusion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fusion findById(org.sphic.tps.model.FusionId id) {
		log.debug("getting Fusion instance with id: " + id);
		try {
			Fusion instance = (Fusion) getSession().get(
					"org.sphic.tps.model.Fusion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Fusion> findByExample(Fusion instance) {
		log.debug("finding Fusion instance by example");
		try {
			List<Fusion> results = (List<Fusion>) getSession()
					.createCriteria("org.sphic.tps.model.Fusion")
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
		log.debug("finding Fusion instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Fusion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Fusion> findByIsRigid(Object isRigid) {
		return findByProperty(IS_RIGID, isRigid);
	}

	public List<Fusion> findByIsDeformable(Object isDeformable) {
		return findByProperty(IS_DEFORMABLE, isDeformable);
	}

	public List findAll() {
		log.debug("finding all Fusion instances");
		try {
			String queryString = "from Fusion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Fusion merge(Fusion detachedInstance) {
		log.debug("merging Fusion instance");
		try {
			Fusion result = (Fusion) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Fusion instance) {
		log.debug("attaching dirty Fusion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fusion instance) {
		log.debug("attaching clean Fusion instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}