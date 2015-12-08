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

	public Images(int imageId, String sop_ins_uid, double ins_number, int slice_location, String image_pos_pat, int SeriesID ) {
		super();
		this.imageId = imageId;
		this.sop_ins_uid = sop_ins_uid;
		this.ins_number = ins_number;
		this.slice_location = slice_location;
		this.image_pos_pat = image_pos_pat;
		this.series_id = SeriesID;
	}
//	public int getSeriesId() {
//		return series_id;
//	}
//
//	public void setSeriesId(int seriesId) {
//		this.series_id = seriesId;
//	}

	public String getSOPInstanceUID() {
		return this.sop_ins_uid;
	}

	public double getSliceThickness() {
		return this.ins_number;
	}

	public int getSliceLocation() {
		return this.slice_location;
	}

	public String getPatientImageLocation() {
		return this.image_pos_pat;
	}

	public int getSeries_id(){ return this.series_id; }
	
}
