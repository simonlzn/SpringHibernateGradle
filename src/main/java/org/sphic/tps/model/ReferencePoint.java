package org.sphic.tps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ReferencePoint entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reference_point", catalog = "sphic")
public class ReferencePoint implements java.io.Serializable {

	// Fields

	private Integer referencePointId;
	private Series series;
	private ReferencePointType referencePointType;
	private String name;
	private String coordinates;

	// Constructors

	/** default constructor */
	public ReferencePoint() {
	}

	/** minimal constructor */
	public ReferencePoint(Integer referencePointId, Series series,
			ReferencePointType referencePointType, String coordinates) {
		this.referencePointId = referencePointId;
		this.series = series;
		this.referencePointType = referencePointType;
		this.coordinates = coordinates;
	}

	/** full constructor */
	public ReferencePoint(Integer referencePointId, Series series,
			ReferencePointType referencePointType, String name,
			String coordinates) {
		this.referencePointId = referencePointId;
		this.series = series;
		this.referencePointType = referencePointType;
		this.name = name;
		this.coordinates = coordinates;
	}

	// Property accessors
	@Id
	@Column(name = "reference_point_id", unique = true, nullable = false)
	public Integer getReferencePointId() {
		return this.referencePointId;
	}

	public void setReferencePointId(Integer referencePointId) {
		this.referencePointId = referencePointId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id", nullable = false)
	public Series getSeries() {
		return this.series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type", nullable = false)
	public ReferencePointType getReferencePointType() {
		return this.referencePointType;
	}

	public void setReferencePointType(ReferencePointType referencePointType) {
		this.referencePointType = referencePointType;
	}

	@Column(name = "name", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "coordinates", nullable = false, length = 40)
	public String getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

}