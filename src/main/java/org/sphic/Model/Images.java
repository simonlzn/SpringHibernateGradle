package org.sphic.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="images")
@JsonIgnoreProperties(value={"series"})
public class Images {
	@Id
	@Column(name="id")
	private int imageId;
	
	@Column(name="sop_ins_uid")
	private String sop_ins_uid;

	@Column(name="ins_number")
	private double ins_number;
	
	@Column(name="slice_location")
	private int slice_location;
	
	@Column(name="image_pos_pat")
	private String image_pos_pat;

	@ManyToOne(targetEntity = Series.class)
	@JoinColumn(name = "series_id")
	private Series series;
	
	public Images(){
		
	}

	public Images(int imageId, String sop_ins_uid, double ins_number, int slice_location, String image_pos_pat) {
		this.imageId = imageId;
		this.sop_ins_uid = sop_ins_uid;
		this.ins_number = ins_number;
		this.slice_location = slice_location;
		this.image_pos_pat = image_pos_pat;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getSop_ins_uid() {
		return sop_ins_uid;
	}

	public void setSop_ins_uid(String sop_ins_uid) {
		this.sop_ins_uid = sop_ins_uid;
	}

	public double getIns_number() {
		return ins_number;
	}

	public void setIns_number(double ins_number) {
		this.ins_number = ins_number;
	}

	public int getSlice_location() {
		return slice_location;
	}

	public void setSlice_location(int slice_location) {
		this.slice_location = slice_location;
	}

	public String getImage_pos_pat() {
		return image_pos_pat;
	}

	public void setImage_pos_pat(String image_pos_pat) {
		this.image_pos_pat = image_pos_pat;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}
}
