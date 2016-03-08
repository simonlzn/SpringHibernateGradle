package org.sphic.tps.model;

import java.util.Date;
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
 * Slice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "slice", catalog = "sphic")
public class Slice implements java.io.Serializable {

	// Fields

	private Integer sliceId;
	private Series series;
	private Character view;
	private Integer number;
	private Integer row;
	private Integer column;
	private Double rowspacing;
	private Double columnspacing;
	private Double sliceLocation;
	private String data;
	private String name;
	private String description;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private String sopInsUid;
	private Integer insNum;
	private String imagePosPat;
	private Set<Contour> contours = new HashSet<Contour>(0);

	// Constructors

	/** default constructor */
	public Slice() {
	}

	public Slice(char view, int number, int row, int column, double rowspacing, double columnspacing, String sop_ins_uid, int ins_number, double slice_location, String image_pos_pat, Date created, Date updated, Date deleted, String data, Set<Contour> contours) {
		this.view = view;
		this.number = number;
		this.row = row;
		this.column = column;
		this.rowspacing = rowspacing;
		this.columnspacing = columnspacing;
		this.sopInsUid = sop_ins_uid;
		this.insNum = ins_number;
		this.sliceLocation = slice_location;
		this.imagePosPat = image_pos_pat;
		this.createdAt = created;
		this.updatedAt = updated;
		this.deletedAt = deleted;
		this.data = data;
		this.contours = contours;
	}

	// Property accessors
	@Id
	@Column(name = "slice_id", unique = true, nullable = false)
	public Integer getSliceId() {
		return this.sliceId;
	}

	public void setSliceId(Integer sliceId) {
		this.sliceId = sliceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id")
	public Series getSeries() {
		return this.series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	@Column(name = "view", nullable = false, length = 1)
	public Character getView() {
		return this.view;
	}

	public void setView(Character view) {
		this.view = view;
	}

	@Column(name = "number", nullable = false)
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "row")
	public Integer getRow() {
		return this.row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	@Column(name = "column")
	public Integer getColumn() {
		return this.column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	@Column(name = "rowspacing", precision = 22, scale = 0)
	public Double getRowspacing() {
		return this.rowspacing;
	}

	public void setRowspacing(Double rowspacing) {
		this.rowspacing = rowspacing;
	}

	@Column(name = "columnspacing", precision = 22, scale = 0)
	public Double getColumnspacing() {
		return this.columnspacing;
	}

	public void setColumnspacing(Double columnspacing) {
		this.columnspacing = columnspacing;
	}

	@Column(name = "slice_location", precision = 22, scale = 0)
	public Double getSliceLocation() {
		return this.sliceLocation;
	}

	public void setSliceLocation(Double sliceLocation) {
		this.sliceLocation = sliceLocation;
	}

	@Column(name = "data", length = 16777215)
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
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

	@Column(name = "sop_ins_uid", length = 200)
	public String getSopInsUid() {
		return this.sopInsUid;
	}

	public void setSopInsUid(String sopInsUid) {
		this.sopInsUid = sopInsUid;
	}

	@Column(name = "ins_num", length = 50)
	public Integer getInsNum() {
		return this.insNum;
	}

	public void setInsNum(Integer insNum) {
		this.insNum = insNum;
	}

	@Column(name = "image_pos_pat", length = 50)
	public String getImagePosPat() {
		return this.imagePosPat;
	}

	public void setImagePosPat(String imagePosPat) {
		this.imagePosPat = imagePosPat;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "slice")
	public Set<Contour> getContours() {
		return this.contours;
	}

	public void setContours(Set<Contour> contours) {
		this.contours = contours;
	}

}