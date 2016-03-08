package org.sphic.tps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Image entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "image", catalog = "sphic")
public class Image implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Series series;
	private String sopInsUid;
	private String insNum;
	private Integer sliceLocation;
	private String imagePosPat;

	// Constructors

	/** default constructor */
	public Image() {
	}

	/** minimal constructor */
	public Image(Integer imageId, Series series) {
		this.imageId = imageId;
		this.series = series;
	}

	/** full constructor */
	public Image(Integer imageId, Series series, String sopInsUid,
			String insNum, Integer sliceLocation, String imagePosPat) {
		this.imageId = imageId;
		this.series = series;
		this.sopInsUid = sopInsUid;
		this.insNum = insNum;
		this.sliceLocation = sliceLocation;
		this.imagePosPat = imagePosPat;
	}

	// Property accessors
	@Id
	@Column(name = "image_id", unique = true, nullable = false)
	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id", nullable = false)
	public Series getSeries() {
		return this.series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	@Column(name = "sop_ins_uid", length = 200)
	public String getSopInsUid() {
		return this.sopInsUid;
	}

	public void setSopInsUid(String sopInsUid) {
		this.sopInsUid = sopInsUid;
	}

	@Column(name = "ins_num", length = 50)
	public String getInsNum() {
		return this.insNum;
	}

	public void setInsNum(String insNum) {
		this.insNum = insNum;
	}

	@Column(name = "slice_location")
	public Integer getSliceLocation() {
		return this.sliceLocation;
	}

	public void setSliceLocation(Integer sliceLocation) {
		this.sliceLocation = sliceLocation;
	}

	@Column(name = "image_pos_pat", length = 50)
	public String getImagePosPat() {
		return this.imagePosPat;
	}

	public void setImagePosPat(String imagePosPat) {
		this.imagePosPat = imagePosPat;
	}

}