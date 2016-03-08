package org.sphic.tps.model;

import javax.persistence.*;

/**
 * ImageSeries entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "image_series", catalog = "sphic")
public class ImageSeries implements java.io.Serializable {

	// Fields

	private Integer imageSeriesId;
	private Series series;
	private String sopClsUid;
	private Double sliceThick;
	private String imageOrientPat;
	private Integer rows;
	private Integer columns;
	private String patientPosition;
	private String pixelSpacing;
	private Double slope;
	private Double intercept;
	private String imageType;
	private String derivationDescrpt;
	private String patientOrient;
	private String specificCharacterSet;
	private String samplePerPixel;
	private String photometricInterpretation;
	private Integer bitsAllocated;
	private Integer bitsStored;
	private Integer highBit;
	private Integer pixelRepresentation;
	private Integer smallestImgPixelVal;
	private Integer largestImgPixelVal;
	private Integer windowLevel;
	private Integer windowWidth;
	private Integer sliceNum;

	// Constructors

	/** default constructor */
	public ImageSeries() {
	}

	public ImageSeries(String sop_cls_uid, double slice_thick, String image_orient_pat, int rows, int columns, int sliceNum,
					   String patient_position, String pixel_spacing, double slope, double intercept, String image_type,
					   String derivation_descrpt, String patient_orient, String specific_character_set, String sample_per_pixel,
					   String photometric_interpretation, int bits_allocated, int bits_stored, int high_bit, int pixel_representation,
					   int smallest_image_pixel_value, int largest_image_pixel_value) {
		this.sopClsUid = sop_cls_uid;
		this.sliceThick = slice_thick;
		this.imageOrientPat = image_orient_pat;
		this.rows = rows;
		this.columns = columns;
		this.sliceNum = sliceNum;
		this.patientPosition = patient_position;
		this.pixelSpacing = pixel_spacing;
		this.slope = slope;
		this.intercept = intercept;
		this.imageType = image_type;
		this.derivationDescrpt = derivation_descrpt;
		this.patientOrient = patient_orient;
		this.specificCharacterSet = specific_character_set;
		this.samplePerPixel = sample_per_pixel;
		this.photometricInterpretation = photometric_interpretation;
		this.bitsAllocated = bits_allocated;
		this.bitsStored = bits_stored;
		this.highBit = high_bit;
		this.pixelRepresentation = pixel_representation;
		this.smallestImgPixelVal = smallest_image_pixel_value;
		this.largestImgPixelVal = largest_image_pixel_value;
	}

	/** minimal constructor */
	public ImageSeries(Integer imageSeriesId, Series series) {
		this.imageSeriesId = imageSeriesId;
		this.series = series;
	}

	/** full constructor */
	public ImageSeries(Integer imageSeriesId, Series series, String sopClsUid,
			Double sliceThick, String imageOrientPat, Integer rows,
			Integer columns, String patientPosition, String pixelSpacing,
			Double slope, Double intercept, String imageType,
			String derivationDescrpt, String patientOrient,
			String specificCharacterSet, String samplePerPixel,
			String photometricInterpretation, Integer bitsAllocated,
			Integer bitsStored, Integer highBit, Integer pixelRepresentation,
			Integer smallestImgPixelVal, Integer largestImgPixelVal,
			Integer windowLevel, Integer windowWidth) {
		this.imageSeriesId = imageSeriesId;
		this.series = series;
		this.sopClsUid = sopClsUid;
		this.sliceThick = sliceThick;
		this.imageOrientPat = imageOrientPat;
		this.rows = rows;
		this.columns = columns;
		this.patientPosition = patientPosition;
		this.pixelSpacing = pixelSpacing;
		this.slope = slope;
		this.intercept = intercept;
		this.imageType = imageType;
		this.derivationDescrpt = derivationDescrpt;
		this.patientOrient = patientOrient;
		this.specificCharacterSet = specificCharacterSet;
		this.samplePerPixel = samplePerPixel;
		this.photometricInterpretation = photometricInterpretation;
		this.bitsAllocated = bitsAllocated;
		this.bitsStored = bitsStored;
		this.highBit = highBit;
		this.pixelRepresentation = pixelRepresentation;
		this.smallestImgPixelVal = smallestImgPixelVal;
		this.largestImgPixelVal = largestImgPixelVal;
		this.windowLevel = windowLevel;
		this.windowWidth = windowWidth;
	}

	// Property accessors
	@Id
	@Column(name = "image_series_id", unique = true, nullable = false)
	public Integer getImageSeriesId() {
		return this.imageSeriesId;
	}

	public void setImageSeriesId(Integer imageSeriesId) {
		this.imageSeriesId = imageSeriesId;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "series_id")
	public Series getSeries() {
		return this.series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	@Column(name = "sop_cls_uid", length = 500)
	public String getSopClsUid() {
		return this.sopClsUid;
	}

	public void setSopClsUid(String sopClsUid) {
		this.sopClsUid = sopClsUid;
	}

	@Column(name = "slice_thick", precision = 22, scale = 0)
	public Double getSliceThick() {
		return this.sliceThick;
	}

	public void setSliceThick(Double sliceThick) {
		this.sliceThick = sliceThick;
	}

	@Column(name = "image_orient_pat", length = 100)
	public String getImageOrientPat() {
		return this.imageOrientPat;
	}

	public void setImageOrientPat(String imageOrientPat) {
		this.imageOrientPat = imageOrientPat;
	}

	@Column(name = "rows")
	public Integer getRows() {
		return this.rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Column(name = "columns")
	public Integer getColumns() {
		return this.columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	@Column(name = "patient_position", length = 100)
	public String getPatientPosition() {
		return this.patientPosition;
	}

	public void setPatientPosition(String patientPosition) {
		this.patientPosition = patientPosition;
	}

	@Column(name = "pixel_spacing", length = 100)
	public String getPixelSpacing() {
		return this.pixelSpacing;
	}

	public void setPixelSpacing(String pixelSpacing) {
		this.pixelSpacing = pixelSpacing;
	}

	@Column(name = "slope", precision = 22, scale = 0)
	public Double getSlope() {
		return this.slope;
	}

	public void setSlope(Double slope) {
		this.slope = slope;
	}

	@Column(name = "intercept", precision = 22, scale = 0)
	public Double getIntercept() {
		return this.intercept;
	}

	public void setIntercept(Double intercept) {
		this.intercept = intercept;
	}

	@Column(name = "image_type", length = 100)
	public String getImageType() {
		return this.imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Column(name = "derivation_descrpt", length = 100)
	public String getDerivationDescrpt() {
		return this.derivationDescrpt;
	}

	public void setDerivationDescrpt(String derivationDescrpt) {
		this.derivationDescrpt = derivationDescrpt;
	}

	@Column(name = "patient_orient", length = 100)
	public String getPatientOrient() {
		return this.patientOrient;
	}

	public void setPatientOrient(String patientOrient) {
		this.patientOrient = patientOrient;
	}

	@Column(name = "specific_character_set", length = 100)
	public String getSpecificCharacterSet() {
		return this.specificCharacterSet;
	}

	public void setSpecificCharacterSet(String specificCharacterSet) {
		this.specificCharacterSet = specificCharacterSet;
	}

	@Column(name = "sample_per_pixel", length = 100)
	public String getSamplePerPixel() {
		return this.samplePerPixel;
	}

	public void setSamplePerPixel(String samplePerPixel) {
		this.samplePerPixel = samplePerPixel;
	}

	@Column(name = "photometric_interpretation", length = 100)
	public String getPhotometricInterpretation() {
		return this.photometricInterpretation;
	}

	public void setPhotometricInterpretation(String photometricInterpretation) {
		this.photometricInterpretation = photometricInterpretation;
	}

	@Column(name = "bits_allocated")
	public Integer getBitsAllocated() {
		return this.bitsAllocated;
	}

	public void setBitsAllocated(Integer bitsAllocated) {
		this.bitsAllocated = bitsAllocated;
	}

	@Column(name = "bits_stored")
	public Integer getBitsStored() {
		return this.bitsStored;
	}

	public void setBitsStored(Integer bitsStored) {
		this.bitsStored = bitsStored;
	}

	@Column(name = "high_bit")
	public Integer getHighBit() {
		return this.highBit;
	}

	public void setHighBit(Integer highBit) {
		this.highBit = highBit;
	}

	@Column(name = "pixel_representation")
	public Integer getPixelRepresentation() {
		return this.pixelRepresentation;
	}

	public void setPixelRepresentation(Integer pixelRepresentation) {
		this.pixelRepresentation = pixelRepresentation;
	}

	@Column(name = "smallest_img_pixel_val")
	public Integer getSmallestImgPixelVal() {
		return this.smallestImgPixelVal;
	}

	public void setSmallestImgPixelVal(Integer smallestImgPixelVal) {
		this.smallestImgPixelVal = smallestImgPixelVal;
	}

	@Column(name = "largest_img_pixel_val")
	public Integer getLargestImgPixelVal() {
		return this.largestImgPixelVal;
	}

	public void setLargestImgPixelVal(Integer largestImgPixelVal) {
		this.largestImgPixelVal = largestImgPixelVal;
	}

	@Column(name = "window_level")
	public Integer getWindowLevel() {
		return this.windowLevel;
	}

	public void setWindowLevel(Integer windowLevel) {
		this.windowLevel = windowLevel;
	}

	@Column(name = "window_width")
	public Integer getWindowWidth() {
		return this.windowWidth;
	}

	public void setWindowWidth(Integer windowWidth) {
		this.windowWidth = windowWidth;
	}

	@Column(name="slice_num")
	public Integer getSliceNum() {
		return sliceNum;
	}

	public void setSliceNum(Integer sliceNum) {
		this.sliceNum = sliceNum;
	}
}