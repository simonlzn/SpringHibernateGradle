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
import org.sphic.tps.model.ImageSeries;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * ImageSeries entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.sphic.tps.model.ImageSeries
 * @author MyEclipse Persistence Tools
 */
@Repository
public class ImageSeriesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ImageSeriesDAO.class);
	// property constants
	public static final String SOP_CLS_UID = "sopClsUid";
	public static final String SLICE_THICK = "sliceThick";
	public static final String IMAGE_ORIENT_PAT = "imageOrientPat";
	public static final String ROWS = "rows";
	public static final String COLUMNS = "columns";
	public static final String PATIENT_POSITION = "patientPosition";
	public static final String PIXEL_SPACING = "pixelSpacing";
	public static final String SLOPE = "slope";
	public static final String INTERCEPT = "intercept";
	public static final String IMAGE_TYPE = "imageType";
	public static final String DERIVATION_DESCRPT = "derivationDescrpt";
	public static final String PATIENT_ORIENT = "patientOrient";
	public static final String SPECIFIC_CHARACTER_SET = "specificCharacterSet";
	public static final String SAMPLE_PER_PIXEL = "samplePerPixel";
	public static final String PHOTOMETRIC_INTERPRETATION = "photometricInterpretation";
	public static final String BITS_ALLOCATED = "bitsAllocated";
	public static final String BITS_STORED = "bitsStored";
	public static final String HIGH_BIT = "highBit";
	public static final String PIXEL_REPRESENTATION = "pixelRepresentation";
	public static final String SMALLEST_IMG_PIXEL_VAL = "smallestImgPixelVal";
	public static final String LARGEST_IMG_PIXEL_VAL = "largestImgPixelVal";
	public static final String WINDOW_LEVEL = "windowLevel";
	public static final String WINDOW_WIDTH = "windowWidth";

	public void save(ImageSeries transientInstance) {
		log.debug("saving ImageSeries instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ImageSeries persistentInstance) {
		log.debug("deleting ImageSeries instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ImageSeries findById(java.lang.Integer id) {
		log.debug("getting ImageSeries instance with id: " + id);
		try {
			ImageSeries instance = (ImageSeries) getSession().get(
					"org.sphic.tps.model.ImageSeries", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ImageSeries> findByExample(ImageSeries instance) {
		log.debug("finding ImageSeries instance by example");
		try {
			List<ImageSeries> results = (List<ImageSeries>) getSession()
					.createCriteria("org.sphic.tps.model.ImageSeries")
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
		log.debug("finding ImageSeries instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ImageSeries as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public ImageSeries getBySeriesId(int id){
		Session session = HibernateUtil.currentSession();
		Criteria criteria = session.createCriteria(ImageSeries.class).add(Restrictions.eq("series.id", id));

		List imageSeriesList = criteria.list();

		if (imageSeriesList.isEmpty())
			return null;

		return (ImageSeries) imageSeriesList.get(0);
	}

	public List<ImageSeries> findBySopClsUid(Object sopClsUid) {
		return findByProperty(SOP_CLS_UID, sopClsUid);
	}

	public List<ImageSeries> findBySliceThick(Object sliceThick) {
		return findByProperty(SLICE_THICK, sliceThick);
	}

	public List<ImageSeries> findByImageOrientPat(Object imageOrientPat) {
		return findByProperty(IMAGE_ORIENT_PAT, imageOrientPat);
	}

	public List<ImageSeries> findByRows(Object rows) {
		return findByProperty(ROWS, rows);
	}

	public List<ImageSeries> findByColumns(Object columns) {
		return findByProperty(COLUMNS, columns);
	}

	public List<ImageSeries> findByPatientPosition(Object patientPosition) {
		return findByProperty(PATIENT_POSITION, patientPosition);
	}

	public List<ImageSeries> findByPixelSpacing(Object pixelSpacing) {
		return findByProperty(PIXEL_SPACING, pixelSpacing);
	}

	public List<ImageSeries> findBySlope(Object slope) {
		return findByProperty(SLOPE, slope);
	}

	public List<ImageSeries> findByIntercept(Object intercept) {
		return findByProperty(INTERCEPT, intercept);
	}

	public List<ImageSeries> findByImageType(Object imageType) {
		return findByProperty(IMAGE_TYPE, imageType);
	}

	public List<ImageSeries> findByDerivationDescrpt(Object derivationDescrpt) {
		return findByProperty(DERIVATION_DESCRPT, derivationDescrpt);
	}

	public List<ImageSeries> findByPatientOrient(Object patientOrient) {
		return findByProperty(PATIENT_ORIENT, patientOrient);
	}

	public List<ImageSeries> findBySpecificCharacterSet(
			Object specificCharacterSet) {
		return findByProperty(SPECIFIC_CHARACTER_SET, specificCharacterSet);
	}

	public List<ImageSeries> findBySamplePerPixel(Object samplePerPixel) {
		return findByProperty(SAMPLE_PER_PIXEL, samplePerPixel);
	}

	public List<ImageSeries> findByPhotometricInterpretation(
			Object photometricInterpretation) {
		return findByProperty(PHOTOMETRIC_INTERPRETATION,
				photometricInterpretation);
	}

	public List<ImageSeries> findByBitsAllocated(Object bitsAllocated) {
		return findByProperty(BITS_ALLOCATED, bitsAllocated);
	}

	public List<ImageSeries> findByBitsStored(Object bitsStored) {
		return findByProperty(BITS_STORED, bitsStored);
	}

	public List<ImageSeries> findByHighBit(Object highBit) {
		return findByProperty(HIGH_BIT, highBit);
	}

	public List<ImageSeries> findByPixelRepresentation(
			Object pixelRepresentation) {
		return findByProperty(PIXEL_REPRESENTATION, pixelRepresentation);
	}

	public List<ImageSeries> findBySmallestImgPixelVal(
			Object smallestImgPixelVal) {
		return findByProperty(SMALLEST_IMG_PIXEL_VAL, smallestImgPixelVal);
	}

	public List<ImageSeries> findByLargestImgPixelVal(Object largestImgPixelVal) {
		return findByProperty(LARGEST_IMG_PIXEL_VAL, largestImgPixelVal);
	}

	public List<ImageSeries> findByWindowLevel(Object windowLevel) {
		return findByProperty(WINDOW_LEVEL, windowLevel);
	}

	public List<ImageSeries> findByWindowWidth(Object windowWidth) {
		return findByProperty(WINDOW_WIDTH, windowWidth);
	}

	public List findAll() {
		log.debug("finding all ImageSeries instances");
		try {
			String queryString = "from ImageSeries";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ImageSeries merge(ImageSeries detachedInstance) {
		log.debug("merging ImageSeries instance");
		try {
			ImageSeries result = (ImageSeries) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ImageSeries instance) {
		log.debug("attaching dirty ImageSeries instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ImageSeries instance) {
		log.debug("attaching clean ImageSeries instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}