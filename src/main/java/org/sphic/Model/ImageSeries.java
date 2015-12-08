package org.sphic.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="image_series")
public class ImageSeries {
	@Id
	@Column(name="id")
	private int id;

	@Column(name="series_id")
	private int series_id;
	
	@Column(name="sop_cls_uid")
	private String sop_cls_uid;

	@Column(name="slice_thick")
	private double slice_thick;
	
	@Column(name="Image_Orient_Pat")
	private String Image_Orient_Pat;
	
	@Column(name="rows")
	private int rows;
	
	@Column(name="columns")
	private int columns;
	
	@Column(name="patient_position")
	private String patient_position;
	
	@Column(name="pixel_spacing")
	private String pixel_spacing;

	@Column(name="slope")
	private double slope;
	
	@Column(name="intercept")
	private double intercept;
	
	public ImageSeries(){
		
	}

	public ImageSeries(int seriesId, String sop_cls_uid, double slice_thick, int rows, int columns, String patient_position,
			String pixel_spacing, String Image_Orient_Pat, double slope, double intercept  ) {
		super();
		this.series_id = seriesId;
		this.sop_cls_uid = sop_cls_uid;
		this.slice_thick = slice_thick;
		this.rows = rows;
		this.columns = columns;
		this.patient_position = patient_position;
		this.pixel_spacing = pixel_spacing;
		this.Image_Orient_Pat = Image_Orient_Pat;
		this.slope = slope;
		this.intercept = intercept;			
	}
	public int getSeriesId() {
		return series_id;
	}

	public void setSeriesId(int seriesId) {
		this.series_id = seriesId;
	}

	public String getSOPClassUID() {
		return sop_cls_uid;
	}

	public double getSliceThickness() {
		return slice_thick;
	}

	public int getRow() {
		return rows;
	}

	public int getColumn() {
		return columns;
	}
	
	public String getPatientPosistion() {
		return patient_position;
	}

	public String getImageOrientationPatient() {
		return Image_Orient_Pat;
	}

	public String getPixelSpacing() {
		return pixel_spacing;
	}
	
	public double getSlope(){
		return slope;
	}
	
	public double getIntercept(){
		return intercept;
	}

}
