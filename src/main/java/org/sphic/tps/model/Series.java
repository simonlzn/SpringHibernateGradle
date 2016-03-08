package org.sphic.tps.model;

import java.util.Date;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Series entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "series", catalog = "sphic")
public class Series implements java.io.Serializable {

	// Fields

	private Integer seriesId;
	private Study study;
	private String modality;
	private String name;
	private String description;
	private Integer seriesNumber;
	private String manufacturer;
	private String manufctModel;
	private Date seriesDateTime;
	private String seriesInsId;
	private String seriesInsUid;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private Boolean isActive;
	private ImageSeries imageSeries = new ImageSeries();
	private Set<StructureSet> structureSets = new HashSet<StructureSet>(0);
	private Set<Slice> slices = new HashSet<Slice>(0);
	private Set<Fusion> fusionsForPrimarySeriesId = new HashSet<Fusion>(0);
	private Set<FourDImagesSeries> fourDImagesSerieses = new HashSet<FourDImagesSeries>(
			0);
	private Set<Fusion> fusionsForSecondarySeriesId = new HashSet<Fusion>(0);
	private Set<ReferencePoint> referencePoints = new HashSet<ReferencePoint>(0);
	private Set<Image> images = new HashSet<Image>(0);

	// Constructors

	/** default constructor */
	public Series() {
	}

	public Series(String series_ins_id, String seriesInsUID, int seriesNumber, Date seriesDateTime, String description,
				  String modality, String manufacturer, String manufctModel, Date created, Date updated, Date deleted, String name) {

		this.seriesInsId = series_ins_id;
		this.seriesInsUid = seriesInsUID;
		this.seriesNumber = seriesNumber;
		this.seriesDateTime = seriesDateTime;
		this.description = description;
		this.modality = modality;
		this.manufacturer = manufacturer;
		this.manufctModel = manufctModel;
		this.createdAt = created;
		this.updatedAt = updated;
		this.deletedAt = deleted;
		this.name = name;
	}

	/** minimal constructor */
	public Series(Integer seriesId) {
		this.seriesId = seriesId;
	}

	/** full constructor */
	public Series(Integer seriesId, Study study, String modality, String name,
			String description, Integer seriesNumber, String manufacturer,
			String manufctModel, Date seriesDateTime, String seriesInsUid,
			Date createdAt, Date updatedAt, Date deletedAt,
			Boolean isActive, ImageSeries imageSeries,
			Set<StructureSet> structureSets, Set<Slice> slices,
			Set<Fusion> fusionsForPrimarySeriesId,
			Set<FourDImagesSeries> fourDImagesSerieses,
			Set<Fusion> fusionsForSecondarySeriesId,
			Set<ReferencePoint> referencePoints, Set<Image> images) {
		this.seriesId = seriesId;
		this.study = study;
		this.modality = modality;
		this.name = name;
		this.description = description;
		this.seriesNumber = seriesNumber;
		this.manufacturer = manufacturer;
		this.manufctModel = manufctModel;
		this.seriesDateTime = seriesDateTime;
		this.seriesInsUid = seriesInsUid;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.isActive = isActive;
		this.imageSeries = imageSeries;
		this.structureSets = structureSets;
		this.slices = slices;
		this.fusionsForPrimarySeriesId = fusionsForPrimarySeriesId;
		this.fourDImagesSerieses = fourDImagesSerieses;
		this.fusionsForSecondarySeriesId = fusionsForSecondarySeriesId;
		this.referencePoints = referencePoints;
		this.images = images;
	}

	// Property accessors
	@Id
	@Column(name = "series_id", unique = true, nullable = false)
	public Integer getSeriesId() {
		return this.seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "study_id")
	public Study getStudy() {
		return this.study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	@Column(name = "modality", length = 20)
	public String getModality() {
		return this.modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 300)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "series_number")
	public Integer getSeriesNumber() {
		return this.seriesNumber;
	}

	public void setSeriesNumber(Integer seriesNumber) {
		this.seriesNumber = seriesNumber;
	}

	@Column(name = "manufacturer", length = 100)
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "manufct_model", length = 100)
	public String getManufctModel() {
		return this.manufctModel;
	}

	public void setManufctModel(String manufctModel) {
		this.manufctModel = manufctModel;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "series_date_time", length = 10)
	public Date getSeriesDateTime() {
		return this.seriesDateTime;
	}

	public void setSeriesDateTime(Date seriesDateTime) {
		this.seriesDateTime = seriesDateTime;
	}

	@Column(name = "series_ins_uid", length = 800)
	public String getSeriesInsUid() {
		return this.seriesInsUid;
	}

	public void setSeriesInsUid(String seriesInsUid) {
		this.seriesInsUid = seriesInsUid;
	}

	@Column(name = "series_ins_id", length = 800)
	public String getSeriesInsId() {
		return this.seriesInsId;
	}

	public void setSeriesInsId(String seriesInsId) {
		this.seriesInsId = seriesInsId;
	}

	@Column(name = "created_at", length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at", length = 19)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "deleted_at", length = 19)
	public Date getDeletedAt() {
		return this.deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Column(name = "is_active")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@OneToOne(mappedBy = "series", targetEntity = ImageSeries.class, cascade = CascadeType.ALL)
	public ImageSeries getImageSeries() {
		return this.imageSeries;
	}

	public void setImageSerieses(ImageSeries imageSeries) {
		this.imageSeries = imageSeries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "series")
	public Set<StructureSet> getStructureSets() {
		return this.structureSets;
	}

	public void setStructureSets(Set<StructureSet> structureSets) {
		this.structureSets = structureSets;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "series")
	public Set<Slice> getSlices() {
		return this.slices;
	}

	public void setSlices(Set<Slice> slices) {
		this.slices = slices;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "seriesByPrimarySeriesId")
	public Set<Fusion> getFusionsForPrimarySeriesId() {
		return this.fusionsForPrimarySeriesId;
	}

	public void setFusionsForPrimarySeriesId(
			Set<Fusion> fusionsForPrimarySeriesId) {
		this.fusionsForPrimarySeriesId = fusionsForPrimarySeriesId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "series")
	public Set<FourDImagesSeries> getFourDImagesSerieses() {
		return this.fourDImagesSerieses;
	}

	public void setFourDImagesSerieses(
			Set<FourDImagesSeries> fourDImagesSerieses) {
		this.fourDImagesSerieses = fourDImagesSerieses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "seriesBySecondarySeriesId")
	public Set<Fusion> getFusionsForSecondarySeriesId() {
		return this.fusionsForSecondarySeriesId;
	}

	public void setFusionsForSecondarySeriesId(
			Set<Fusion> fusionsForSecondarySeriesId) {
		this.fusionsForSecondarySeriesId = fusionsForSecondarySeriesId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "series")
	public Set<ReferencePoint> getReferencePoints() {
		return this.referencePoints;
	}

	public void setReferencePoints(Set<ReferencePoint> referencePoints) {
		this.referencePoints = referencePoints;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "series")
	public Set<Image> getImages() {
		return this.images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

}