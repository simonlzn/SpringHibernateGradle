package org.sphic.Model;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="images")
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

	@Column(name="series_id")
	private int series_id;
	
	public Images(){
		
	}

	public Images(int imageId, String sop_ins_uid, double ins_number, int slice_location, String image_pos_pat, int series_id) {
		this.imageId = imageId;
		this.sop_ins_uid = sop_ins_uid;
		this.ins_number = ins_number;
		this.slice_location = slice_location;
		this.image_pos_pat = image_pos_pat;
		this.series_id = series_id;
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

	public int getSeries_id() {
		return series_id;
	}

	public void setSeries_id(int series_id) {
		this.series_id = series_id;
	}
}
