package org.sphic.tps.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ReferencePointType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reference_point_type", catalog = "sphic")
public class ReferencePointType implements java.io.Serializable {

	// Fields

	private Integer referencePointTypeId;
	private String name;
	private Set<ReferencePoint> referencePoints = new HashSet<ReferencePoint>(0);

	// Constructors

	/** default constructor */
	public ReferencePointType() {
	}

	/** minimal constructor */
	public ReferencePointType(Integer referencePointTypeId) {
		this.referencePointTypeId = referencePointTypeId;
	}

	/** full constructor */
	public ReferencePointType(Integer referencePointTypeId, String name,
			Set<ReferencePoint> referencePoints) {
		this.referencePointTypeId = referencePointTypeId;
		this.name = name;
		this.referencePoints = referencePoints;
	}

	// Property accessors
	@Id
	@Column(name = "reference_point_type_id", unique = true, nullable = false)
	public Integer getReferencePointTypeId() {
		return this.referencePointTypeId;
	}

	public void setReferencePointTypeId(Integer referencePointTypeId) {
		this.referencePointTypeId = referencePointTypeId;
	}

	@Column(name = "name", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "referencePointType")
	public Set<ReferencePoint> getReferencePoints() {
		return this.referencePoints;
	}

	public void setReferencePoints(Set<ReferencePoint> referencePoints) {
		this.referencePoints = referencePoints;
	}

}