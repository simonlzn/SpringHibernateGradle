package org.sphic.tps.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Contour entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contour", catalog = "sphic")
public class Contour implements java.io.Serializable {

	// Fields

	private Integer contourId;
	private Slice slice;
	private Structure structure;
	private String name;
	private String description;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private String SOPInstanceUID;
	private String contourData;
	private String ROIcolor;
	// Constructors

	/** default constructor */
	public Contour() {
	}

	public Contour(String SOPInstanceUID, Date created, Date updated, Date deleted, String description,
				   String contourData) {
		this.SOPInstanceUID = SOPInstanceUID;
		this.createdAt = created;
		this.updatedAt = updated;
		this.deletedAt = deleted;
		this.description = description;
		this.contourData = contourData;
	}

	/** minimal constructor */
	public Contour(Integer contourId) {
		this.contourId = contourId;
	}

	/** full constructor */
	public Contour(Integer contourId, Slice slice, Structure structure,
			String name, String description, Date createdAt,
			Date updatedAt, Date deletedAt) {
		this.contourId = contourId;
		this.slice = slice;
		this.structure = structure;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	// Property accessors
	@Id
	@Column(name = "contour_id", unique = true, nullable = false)
	public Integer getContourId() {
		return this.contourId;
	}

	public void setContourId(Integer contourId) {
		this.contourId = contourId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slice_id")
	public Slice getSlice() {
		return this.slice;
	}

	public void setSlice(Slice slice) {
		this.slice = slice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "structure_id")
	public Structure getStructure() {
		return this.structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
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

	public String getContourData() {
		return contourData;
	}

	public void setContourData(String contourData) {
		this.contourData = contourData;
	}

	public String getROIcolor() {
		return ROIcolor;
	}

	public void setROIcolor(String ROIcolor) {
		this.ROIcolor = ROIcolor;
	}
}