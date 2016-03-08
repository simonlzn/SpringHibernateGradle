package org.sphic.tps.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FusionId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class FusionId implements java.io.Serializable {

	// Fields

	private Integer primarySeriesId;
	private Integer secondarySeriesId;

	// Constructors

	/** default constructor */
	public FusionId() {
	}

	/** full constructor */
	public FusionId(Integer primarySeriesId, Integer secondarySeriesId) {
		this.primarySeriesId = primarySeriesId;
		this.secondarySeriesId = secondarySeriesId;
	}

	// Property accessors

	@Column(name = "primary_series_id", nullable = false)
	public Integer getPrimarySeriesId() {
		return this.primarySeriesId;
	}

	public void setPrimarySeriesId(Integer primarySeriesId) {
		this.primarySeriesId = primarySeriesId;
	}

	@Column(name = "secondary_series_id", nullable = false)
	public Integer getSecondarySeriesId() {
		return this.secondarySeriesId;
	}

	public void setSecondarySeriesId(Integer secondarySeriesId) {
		this.secondarySeriesId = secondarySeriesId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FusionId))
			return false;
		FusionId castOther = (FusionId) other;

		return ((this.getPrimarySeriesId() == castOther.getPrimarySeriesId()) || (this
				.getPrimarySeriesId() != null
				&& castOther.getPrimarySeriesId() != null && this
				.getPrimarySeriesId().equals(castOther.getPrimarySeriesId())))
				&& ((this.getSecondarySeriesId() == castOther
						.getSecondarySeriesId()) || (this
						.getSecondarySeriesId() != null
						&& castOther.getSecondarySeriesId() != null && this
						.getSecondarySeriesId().equals(
								castOther.getSecondarySeriesId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPrimarySeriesId() == null ? 0 : this.getPrimarySeriesId()
						.hashCode());
		result = 37
				* result
				+ (getSecondarySeriesId() == null ? 0 : this
						.getSecondarySeriesId().hashCode());
		return result;
	}

}