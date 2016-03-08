package org.sphic.tps.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Fusion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fusion", catalog = "sphic")
public class Fusion implements java.io.Serializable {

	// Fields

	private FusionId id;
	private Series seriesBySecondarySeriesId;
	private Series seriesByPrimarySeriesId;
	private Boolean isRigid;
	private Boolean isDeformable;

	// Constructors

	/** default constructor */
	public Fusion() {
	}

	/** minimal constructor */
	public Fusion(FusionId id, Series seriesBySecondarySeriesId,
			Series seriesByPrimarySeriesId) {
		this.id = id;
		this.seriesBySecondarySeriesId = seriesBySecondarySeriesId;
		this.seriesByPrimarySeriesId = seriesByPrimarySeriesId;
	}

	/** full constructor */
	public Fusion(FusionId id, Series seriesBySecondarySeriesId,
			Series seriesByPrimarySeriesId, Boolean isRigid,
			Boolean isDeformable) {
		this.id = id;
		this.seriesBySecondarySeriesId = seriesBySecondarySeriesId;
		this.seriesByPrimarySeriesId = seriesByPrimarySeriesId;
		this.isRigid = isRigid;
		this.isDeformable = isDeformable;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "primarySeriesId", column = @Column(name = "primary_series_id", nullable = false)),
			@AttributeOverride(name = "secondarySeriesId", column = @Column(name = "secondary_series_id", nullable = false)) })
	public FusionId getId() {
		return this.id;
	}

	public void setId(FusionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "secondary_series_id", nullable = false, insertable = false, updatable = false)
	public Series getSeriesBySecondarySeriesId() {
		return this.seriesBySecondarySeriesId;
	}

	public void setSeriesBySecondarySeriesId(Series seriesBySecondarySeriesId) {
		this.seriesBySecondarySeriesId = seriesBySecondarySeriesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "primary_series_id", nullable = false, insertable = false, updatable = false)
	public Series getSeriesByPrimarySeriesId() {
		return this.seriesByPrimarySeriesId;
	}

	public void setSeriesByPrimarySeriesId(Series seriesByPrimarySeriesId) {
		this.seriesByPrimarySeriesId = seriesByPrimarySeriesId;
	}

	@Column(name = "is_rigid")
	public Boolean getIsRigid() {
		return this.isRigid;
	}

	public void setIsRigid(Boolean isRigid) {
		this.isRigid = isRigid;
	}

	@Column(name = "is_deformable")
	public Boolean getIsDeformable() {
		return this.isDeformable;
	}

	public void setIsDeformable(Boolean isDeformable) {
		this.isDeformable = isDeformable;
	}

}