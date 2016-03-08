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
 * Material entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "material", catalog = "sphic")
public class Material implements java.io.Serializable {

	// Fields

	private Integer materialId;
	private String description;
	private Integer value;
	private Integer type;
	private Set<Structure> structuresForPhysicalMaterial = new HashSet<Structure>(
			0);
	private Set<Structure> structuresForBiologicalMaterial = new HashSet<Structure>(
			0);

	// Constructors

	/** default constructor */
	public Material() {
	}

	/** minimal constructor */
	public Material(Integer materialId) {
		this.materialId = materialId;
	}

	/** full constructor */
	public Material(Integer materialId, String description, Integer value,
			Integer type, Set<Structure> structuresForPhysicalMaterial,
			Set<Structure> structuresForBiologicalMaterial) {
		this.materialId = materialId;
		this.description = description;
		this.value = value;
		this.type = type;
		this.structuresForPhysicalMaterial = structuresForPhysicalMaterial;
		this.structuresForBiologicalMaterial = structuresForBiologicalMaterial;
	}

	// Property accessors
	@Id
	@Column(name = "material_id", unique = true, nullable = false)
	public Integer getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "value")
	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "materialByPhysicalMaterial")
	public Set<Structure> getStructuresForPhysicalMaterial() {
		return this.structuresForPhysicalMaterial;
	}

	public void setStructuresForPhysicalMaterial(
			Set<Structure> structuresForPhysicalMaterial) {
		this.structuresForPhysicalMaterial = structuresForPhysicalMaterial;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "materialByBiologicalMaterial")
	public Set<Structure> getStructuresForBiologicalMaterial() {
		return this.structuresForBiologicalMaterial;
	}

	public void setStructuresForBiologicalMaterial(
			Set<Structure> structuresForBiologicalMaterial) {
		this.structuresForBiologicalMaterial = structuresForBiologicalMaterial;
	}

}