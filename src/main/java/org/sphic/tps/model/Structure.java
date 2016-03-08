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
 * Structure entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "structure", catalog = "sphic")
public class Structure implements java.io.Serializable {

	// Fields

	private Integer structureId;
	private StructureSet structureSet;
	private Material materialByPhysicalMaterial;
	private User userByLockedBy;
	private Material materialByBiologicalMaterial;
	private StructureType structureType;
	private User userByLastModified;
	private String name;
	private String description;
	private Integer color;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private Double transparency;
	private Integer volume;
	private Boolean isVisible;
	private Boolean isApproved;
	private Boolean isLocked;
	private Integer ROINumber;
	private String ROIcolor;
	private Set<Contour> contours = new HashSet<Contour>(0);

	// Constructors

	/** default constructor */
	public Structure() {
	}

	public Structure(int ROINumber, String name, Date created, Date updated, Date deleted, String description, Set<Contour> contours) {
		this.ROINumber = ROINumber;
		this.name = name;
		this.createdAt = created;
		this.updatedAt = updated;
		this.deletedAt = deleted;
		this.description = description;
		this.contours = contours;
	}
	/** minimal constructor */
	public Structure(Integer structureId, StructureSet structureSet,
			Material materialByPhysicalMaterial,
			Material materialByBiologicalMaterial, StructureType structureType,
			User userByLastModified) {
		this.structureId = structureId;
		this.structureSet = structureSet;
		this.materialByPhysicalMaterial = materialByPhysicalMaterial;
		this.materialByBiologicalMaterial = materialByBiologicalMaterial;
		this.structureType = structureType;
		this.userByLastModified = userByLastModified;
	}

	/** full constructor */
	public Structure(Integer structureId, StructureSet structureSet,
			Material materialByPhysicalMaterial, User userByLockedBy,
			Material materialByBiologicalMaterial, StructureType structureType,
			User userByLastModified, String name, String description,
			Integer color, Date createdAt, Date updatedAt,
			Date deletedAt, Double transparency, Integer volume,
			Boolean isVisible, Boolean isApproved, Boolean isLocked,
			Set<Contour> contours) {
		this.structureId = structureId;
		this.structureSet = structureSet;
		this.materialByPhysicalMaterial = materialByPhysicalMaterial;
		this.userByLockedBy = userByLockedBy;
		this.materialByBiologicalMaterial = materialByBiologicalMaterial;
		this.structureType = structureType;
		this.userByLastModified = userByLastModified;
		this.name = name;
		this.description = description;
		this.color = color;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.transparency = transparency;
		this.volume = volume;
		this.isVisible = isVisible;
		this.isApproved = isApproved;
		this.isLocked = isLocked;
		this.contours = contours;
	}

	// Property accessors
	@Id
	@Column(name = "structure_id", unique = true, nullable = false)
	public Integer getStructureId() {
		return this.structureId;
	}

	public void setStructureId(Integer structureId) {
		this.structureId = structureId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "structure_set_id", nullable = false)
	public StructureSet getStructureSet() {
		return this.structureSet;
	}

	public void setStructureSet(StructureSet structureSet) {
		this.structureSet = structureSet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "physical_material", nullable = false)
	public Material getMaterialByPhysicalMaterial() {
		return this.materialByPhysicalMaterial;
	}

	public void setMaterialByPhysicalMaterial(
			Material materialByPhysicalMaterial) {
		this.materialByPhysicalMaterial = materialByPhysicalMaterial;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "locked_by")
	public User getUserByLockedBy() {
		return this.userByLockedBy;
	}

	public void setUserByLockedBy(User userByLockedBy) {
		this.userByLockedBy = userByLockedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "biological material", nullable = false)
	public Material getMaterialByBiologicalMaterial() {
		return this.materialByBiologicalMaterial;
	}

	public void setMaterialByBiologicalMaterial(
			Material materialByBiologicalMaterial) {
		this.materialByBiologicalMaterial = materialByBiologicalMaterial;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type", nullable = false)
	public StructureType getStructureType() {
		return this.structureType;
	}

	public void setStructureType(StructureType structureType) {
		this.structureType = structureType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "last_modified", nullable = false)
	public User getUserByLastModified() {
		return this.userByLastModified;
	}

	public void setUserByLastModified(User userByLastModified) {
		this.userByLastModified = userByLastModified;
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

	@Column(name = "color")
	public Integer getColor() {
		return this.color;
	}

	public void setColor(Integer color) {
		this.color = color;
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

	@Column(name = "transparency", precision = 22, scale = 0)
	public Double getTransparency() {
		return this.transparency;
	}

	public void setTransparency(Double transparency) {
		this.transparency = transparency;
	}

	@Column(name = "volume")
	public Integer getVolume() {
		return this.volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	@Column(name = "is_visible")
	public Boolean getIsVisible() {
		return this.isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Column(name = "is_approved")
	public Boolean getIsApproved() {
		return this.isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	@Column(name = "is_locked")
	public Boolean getIsLocked() {
		return this.isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	@Column(name="roi_number")
	public Integer getROINumber() {
		return ROINumber;
	}

	public void setROINumber(Integer ROINumber) {
		this.ROINumber = ROINumber;
	}

	@Column(name="roi_color")
	public String getROIcolor() {
		return ROIcolor;
	}

	public void setROIcolor(String ROIcolor) {
		this.ROIcolor = ROIcolor;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "structure")
	public Set<Contour> getContours() {
		return this.contours;
	}

	public void setContours(Set<Contour> contours) {
		this.contours = contours;
	}

}