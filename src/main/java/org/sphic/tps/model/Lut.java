package org.sphic.tps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Lut entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lut", catalog = "sphic")
public class Lut implements java.io.Serializable {

	// Fields

	private Integer lutId;
	private Integer index;
	private Double r;
	private Double g;
	private Double b;

	// Constructors

	/** default constructor */
	public Lut() {
	}

	/** minimal constructor */
	public Lut(Integer lutId) {
		this.lutId = lutId;
	}

	/** full constructor */
	public Lut(Integer lutId, Integer index, Double r, Double g, Double b) {
		this.lutId = lutId;
		this.index = index;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	// Property accessors
	@Id
	@Column(name = "lut_id", unique = true, nullable = false)
	public Integer getLutId() {
		return this.lutId;
	}

	public void setLutId(Integer lutId) {
		this.lutId = lutId;
	}

	@Column(name = "index")
	public Integer getIndex() {
		return this.index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Column(name = "R", precision = 22, scale = 0)
	public Double getR() {
		return this.r;
	}

	public void setR(Double r) {
		this.r = r;
	}

	@Column(name = "G", precision = 22, scale = 0)
	public Double getG() {
		return this.g;
	}

	public void setG(Double g) {
		this.g = g;
	}

	@Column(name = "B", precision = 22, scale = 0)
	public Double getB() {
		return this.b;
	}

	public void setB(Double b) {
		this.b = b;
	}

}