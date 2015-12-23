package org.sphic.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="image_series")
@JsonIgnoreProperties(value={"series"})
public class ImageSeries {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "series_id")
	private Series series;

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}
	
	@Column(name="sop_cls_uid")
	private String sop_cls_uid;

	@Column(name="slice_thick")
	private double slice_thick;
	
	@Column(name="image_orient_pat")
	private String image_orient_pat;
	
	@Column(name="rows")
	private int rows;
	
	@Column(name="columns")
	private int columns;

	@Column(name="slice_num")
	private int sliceNum;

	@Column(name="patient_position")
	private String patient_position;
	
	@Column(name="pixel_spacing")
	private String pixel_spacing;

	@Column(name="slope")
	private double slope;
	
	@Column(name="intercept")
	private double intercept;

	@Column(name="image_type")
	private String image_type;

	@Column(name="derivation_descrpt")
	private String derivation_descrpt;

	@Column(name="patient_orient")
	private String patient_orient;

	@Column(name="specific_character_set")
	private String specific_character_set;

	@Column(name="sample_per_pixel")
	private String sample_per_pixel;

	@Column(name="photometric_interpretation")
	private String photometric_interpretation;

	@Column(name="bits_allocated")
	private int bits_allocated;

	@Column(name="bits_stored")
	private int bits_stored;

	@Column(name="high_bit")
	private int high_bit;

	@Column(name="pixel_representation")
	private int pixel_representation;

	@Column(name="smallest_img_pixel_val")
	private int smallest_image_pixel_value;

	@Column(name="largest_img_pixel_val")
	private int largest_image_pixel_value;

	public ImageSeries(){
		
	}

	public ImageSeries(String sop_cls_uid, double slice_thick, String image_orient_pat, int rows, int columns, int sliceNum,
					   String patient_position, String pixel_spacing, double slope, double intercept, String image_type,
					   String derivation_descrpt, String patient_orient, String specific_character_set, String sample_per_pixel,
					   String photometric_interpretation, int bits_allocated, int bits_stored, int high_bit, int pixel_representation,
					   int smallest_image_pixel_value, int largest_image_pixel_value) {
		this.sop_cls_uid = sop_cls_uid;
		this.slice_thick = slice_thick;
		this.image_orient_pat = image_orient_pat;
		this.rows = rows;
		this.columns = columns;
		this.sliceNum = sliceNum;
		this.patient_position = patient_position;
		this.pixel_spacing = pixel_spacing;
		this.slope = slope;
		this.intercept = intercept;
		this.image_type = image_type;
		this.derivation_descrpt = derivation_descrpt;
		this.patient_orient = patient_orient;
		this.specific_character_set = specific_character_set;
		this.sample_per_pixel = sample_per_pixel;
		this.photometric_interpretation = photometric_interpretation;
		this.bits_allocated = bits_allocated;
		this.bits_stored = bits_stored;
		this.high_bit = high_bit;
		this.pixel_representation = pixel_representation;
		this.smallest_image_pixel_value = smallest_image_pixel_value;
		this.largest_image_pixel_value = largest_image_pixel_value;
	}

	public String getSop_cls_uid() {
		return sop_cls_uid;
	}

	public void setSop_cls_uid(String sop_cls_uid) {
		this.sop_cls_uid = sop_cls_uid;
	}

	public double getSlice_thick() {
		return slice_thick;
	}

	public void setSlice_thick(double slice_thick) {
		this.slice_thick = slice_thick;
	}

	public String getImage_Orient_Pat() {
		return image_orient_pat;
	}

	public void setImage_Orient_Pat(String image_Orient_Pat) {
		image_orient_pat = image_Orient_Pat;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public String getPatient_position() {
		return patient_position;
	}

	public void setPatient_position(String patient_position) {
		this.patient_position = patient_position;
	}

	public String getPixel_spacing() {
		return pixel_spacing;
	}

	public void setPixel_spacing(String pixel_spacing) {
		this.pixel_spacing = pixel_spacing;
	}

	public double getSlope() {
		return slope;
	}

	public void setSlope(double slope) {
		this.slope = slope;
	}

	public double getIntercept() {
		return intercept;
	}

	public void setIntercept(double intercept) {
		this.intercept = intercept;
	}

	public String getImage_type() {
		return image_type;
	}

	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}

	public String getDerivation_descrpt() {
		return derivation_descrpt;
	}

	public void setDerivation_descrpt(String derivation_descrpt) {
		this.derivation_descrpt = derivation_descrpt;
	}

	public String getPatient_orient() {
		return patient_orient;
	}

	public void setPatient_orient(String patient_orient) {
		this.patient_orient = patient_orient;
	}

	public String getSpecific_character_set() {
		return specific_character_set;
	}

	public void setSpecific_character_set(String specific_character_set) {
		this.specific_character_set = specific_character_set;
	}

	public String getSample_per_pixel() {
		return sample_per_pixel;
	}

	public void setSample_per_pixel(String sample_per_pixel) {
		this.sample_per_pixel = sample_per_pixel;
	}

	public String getPhotometric_interpretation() {
		return photometric_interpretation;
	}

	public void setPhotometric_interpretation(String photometric_interpretation) {
		this.photometric_interpretation = photometric_interpretation;
	}

	public int getBits_allocated() {
		return bits_allocated;
	}

	public void setBits_allocated(int bits_allocated) {
		this.bits_allocated = bits_allocated;
	}

	public int getBits_stored() {
		return bits_stored;
	}

	public void setBits_stored(int bits_stored) {
		this.bits_stored = bits_stored;
	}

	public int getHigh_bit() {
		return high_bit;
	}

	public void setHigh_bit(int high_bit) {
		this.high_bit = high_bit;
	}

	public int getPixel_representation() {
		return pixel_representation;
	}

	public void setPixel_representation(int pixel_representation) {
		this.pixel_representation = pixel_representation;
	}

	public int getSmallest_image_pixel_value() {
		return smallest_image_pixel_value;
	}

	public void setSmallest_image_pixel_value(int smallest_image_pixel_value) {
		this.smallest_image_pixel_value = smallest_image_pixel_value;
	}

	public int getLargest_image_pixel_value() {
		return largest_image_pixel_value;
	}

	public void setLargest_image_pixel_value(int largest_image_pixel_value) {
		this.largest_image_pixel_value = largest_image_pixel_value;
	}

	public String getImage_orient_pat() {
		return image_orient_pat;
	}

	public void setImage_orient_pat(String image_orient_pat) {
		this.image_orient_pat = image_orient_pat;
	}

	public int getSliceNum() {
		return sliceNum;
	}

	public void setSliceNum(int sliceNum) {
		this.sliceNum = sliceNum;
	}
}
