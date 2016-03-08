package org.sphic.tps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FourDImagesSeries entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "four_d_images_series", catalog = "sphic")
public class FourDImagesSeries implements java.io.Serializable {

	// Fields

	private Integer seriesId;
	private FourDImages fourDImages;
	private Series series;
	private String phase;
	private Boolean isAverage;
	private Boolean isMip;
	private Boolean isMinip;

	// Constructors

	/** default constructor */
	public FourDImagesSeries() {
	}

	/** minimal constructor */
	public FourDImagesSeries(Integer seriesId, Series series) {
		this.seriesId = seriesId;
		this.series = series;
	}

	/** full constructor */
	public FourDImagesSeries(Integer seriesId, FourDImages fourDImages,
			Series series, String phase, Boolean isAverage, Boolean isMip,
			Boolean isMinip) {
		this.seriesId = seriesId;
		this.fourDImages = fourDImages;
		this.series = series;
		this.phase = phase;
		this.isAverage = isAverage;
		this.isMip = isMip;
		this.isMinip = isMinip;
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
	@JoinColumn(name = "four_d_images_id")
	public FourDImages getFourDImages() {
		return this.fourDImages;
	}

	public void setFourDImages(FourDImages fourDImages) {
		this.fourDImages = fourDImages;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id", unique = true, nullable = false, insertable = false, updatable = false)
	public Series getSeries() {
		return this.series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	@Column(name = "phase", length = 40)
	public String getPhase() {
		return this.phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	@Column(name = "is_average")
	public Boolean getIsAverage() {
		return this.isAverage;
	}

	public void setIsAverage(Boolean isAverage) {
		this.isAverage = isAverage;
	}

	@Column(name = "is_mip")
	public Boolean getIsMip() {
		return this.isMip;
	}

	public void setIsMip(Boolean isMip) {
		this.isMip = isMip;
	}

	@Column(name = "is_minip")
	public Boolean getIsMinip() {
		return this.isMinip;
	}

	public void setIsMinip(Boolean isMinip) {
		this.isMinip = isMinip;
	}

}