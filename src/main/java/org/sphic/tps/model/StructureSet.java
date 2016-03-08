package org.sphic.tps.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * StructureSet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "structure_set", catalog = "sphic")
public class StructureSet implements java.io.Serializable {

	// Fields

	private Integer structureSetId;
	private Series series;
	private String name;
	private String description;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private Set<Structure> structures = new HashSet<Structure>(0);

	// Constructors

	/** default constructor */
	public StructureSet() {
	}

	public StructureSet(String name, Date created, Date updated, Date deleted, String description, Set<Structure> structures) {
		this.name = name;
		this.createdAt = created;
		this.updatedAt = updated;
		this.deletedAt = deleted;
		this.description = description;
		this.structures = structures;
	}

	/** minimal constructor */
	public StructureSet(Integer structureSetId) {
		this.structureSetId = structureSetId;
	}

	/** full constructor */
	public StructureSet(Integer structureSetId, Series series, String name,
			String description, Date createdAt, Date updatedAt,
			Date deletedAt, Set<Structure> structures) {
		this.structureSetId = structureSetId;
		this.series = series;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.structures = structures;
	}

	// Property accessors
	@Id
	@Column(name = "structure_set_id", unique = true, nullable = false)
	public Integer getStructureSetId() {
		return this.structureSetId;
	}

	public void setStructureSetId(Integer structureSetId) {
		this.structureSetId = structureSetId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id")
	public Series getSeries() {
		return this.series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "structureSet")
	public Set<Structure> getStructures() {
		return this.structures;
	}

	public void setStructures(Set<Structure> structures) {
		this.structures = structures;
	}

}