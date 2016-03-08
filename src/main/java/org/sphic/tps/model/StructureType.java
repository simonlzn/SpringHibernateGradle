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
 * StructureType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "structure_type", catalog = "sphic")
public class StructureType implements java.io.Serializable {

	// Fields

	private Integer structureTypeId;
	private String name;
	private Set<Structure> structures = new HashSet<Structure>(0);

	// Constructors

	/** default constructor */
	public StructureType() {
	}

	/** minimal constructor */
	public StructureType(Integer structureTypeId) {
		this.structureTypeId = structureTypeId;
	}

	/** full constructor */
	public StructureType(Integer structureTypeId, String name,
			Set<Structure> structures) {
		this.structureTypeId = structureTypeId;
		this.name = name;
		this.structures = structures;
	}

	// Property accessors
	@Id
	@Column(name = "structure_type_id", unique = true, nullable = false)
	public Integer getStructureTypeId() {
		return this.structureTypeId;
	}

	public void setStructureTypeId(Integer structureTypeId) {
		this.structureTypeId = structureTypeId;
	}

	@Column(name = "name", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "structureType")
	public Set<Structure> getStructures() {
		return this.structures;
	}

	public void setStructures(Set<Structure> structures) {
		this.structures = structures;
	}

}