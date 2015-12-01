package org.sphic.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="Image")
public class Image {
	@Id
	@Column(name="id")
	private String imageId;	
	
	@Column(name="description")
	private String description;	
	
	@Column(name="row")
	private int row;
	
	@Column(name="column")
	private int column;
	
	@Column(name="descrip")
	private String descprition;
	
	@Column(name="is_supine")
	private boolean is_supine;
	
	@Column(name="img_src")
	private String img_src;
	
	@Column(name="vendor")
	private String vendor;
	
	@Column(name="model")
	private String model;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="vendor_pos")
	private String vendor_pos;
	
	@Column(name="slice_thick")
	private double slice_thick;
	
	@Column(name="pixel_size")
	private double pixel_size;
	
	@Column(name="pixel_width")
	private double pixel_width;
	
	@Column(name="sop_cls_uid")
	private String sop_cls_uid;
	
	@Column(name="sop_ins_uid")
	private String sop_ins_uid;
	
	@Column(name="image_pos_pat_exists")
	private Boolean image_pos_pat_exists;
	
	@Column(name="image_pos_pat")
	private double[] image_pos_pat;
	
	@Column(name="image_orient_pat_exists")
	private Boolean image_orient_pat_exists;
	
	@Column(name="image_ipp_pat")
	private double[] image_ipp_pat;
	
	@Column(name="slice_location_exists")
	private boolean slice_location_exists;
	
	@Column(name="slice_location")
	private double slice_location;
	
	@Column(name="slope")
	private double slope;
	
	@Column(name="intercept")
	private double intercept;
	
	
		
	public Image(){
		
	}

	public Image(String SeriesId, Date date, String sop_cls_uid, String sop_ins_uid, String description, 
			String vendor, String model, double pixel_size, double pixel_width, boolean is_supine, double slice_thick, int row,
			int column, double intercept, double slope ) 
	{
		super();
		this.imageId = SeriesId;
		this.date = date;
		this.sop_cls_uid = sop_cls_uid;
		this.sop_ins_uid = sop_ins_uid;
		this.description = description;
		this.vendor = vendor;
		this.model = model;
		this.pixel_size = pixel_size;
		this.pixel_width = pixel_width;
		this.is_supine = is_supine;
		this.slice_thick = slice_thick;
		this.row = row;
		this.column = column;
		this.intercept = intercept;
		this.slope = slope;
	
	}

	public String getImageId() {
		return imageId;
	}

	public Date getDate() {
		return date;
	}
	
	public String getInstantUID() {
		return sop_ins_uid;
	}

	public String getSOPclassUID() {
		return sop_cls_uid;
	}

	public String getModel() {
		return model;
	}
	
	public String getVendor() {
		return vendor;
	}
	
	public double getPixelSize() {
		return pixel_size;
	}
	
	public double getPixelWidth() {
		return pixel_width;
	}
	
	public boolean isSupine() {
		return is_supine;
	}
	
	public double getSliceThickness(){
		return slice_thick;
	}
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	public double getIntercept(){
		return intercept;
	}
	
	public double getSlope(){
		return slope;
	}
	
}