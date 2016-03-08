package org.sphic.tps.model;

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
 * FourDImages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "four_d_images", catalog = "sphic")
public class FourDImages implements java.io.Serializable {

	// Fields

	private Integer fourDImagesId;
	private Study study;
	private String path;
	private Integer phaseNum;
	private Integer timeInterval;
	private Set<FourDImagesSeries> fourDImagesSerieses = new HashSet<FourDImagesSeries>(
			0);

	// Constructors

	/** default constructor */
	public FourDImages() {
	}

	/** minimal constructor */
	public FourDImages(Integer fourDImagesId) {
		this.fourDImagesId = fourDImagesId;
	}

	/** full constructor */
	public FourDImages(Integer fourDImagesId, Study study, String path,
			Integer phaseNum, Integer timeInterval,
			Set<FourDImagesSeries> fourDImagesSerieses) {
		this.fourDImagesId = fourDImagesId;
		this.study = study;
		this.path = path;
		this.phaseNum = phaseNum;
		this.timeInterval = timeInterval;
		this.fourDImagesSerieses = fourDImagesSerieses;
	}

	// Property accessors
	@Id
	@Column(name = "four_d_images_id", unique = true, nullable = false)
	public Integer getFourDImagesId() {
		return this.fourDImagesId;
	}

	public void setFourDImagesId(Integer fourDImagesId) {
		this.fourDImagesId = fourDImagesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "study_id")
	public Study getStudy() {
		return this.study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	@Column(name = "path", length = 100)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "phase_num")
	public Integer getPhaseNum() {
		return this.phaseNum;
	}

	public void setPhaseNum(Integer phaseNum) {
		this.phaseNum = phaseNum;
	}

	@Column(name = "time_interval")
	public Integer getTimeInterval() {
		return this.timeInterval;
	}

	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fourDImages")
	public Set<FourDImagesSeries> getFourDImagesSerieses() {
		return this.fourDImagesSerieses;
	}

	public void setFourDImagesSerieses(
			Set<FourDImagesSeries> fourDImagesSerieses) {
		this.fourDImagesSerieses = fourDImagesSerieses;
	}

}