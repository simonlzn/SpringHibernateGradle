package org.sphic.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="series")
public class Series {
	@Id
	@Column(name="id")
	private int seriesId;
	
	@Column(name="study_id")
	private int studyId;	
	
	@Column(name="time")
	private Date time;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="institution")
	private String institution;
	
	@Column(name="modality")
	private String modality;

	@OneToMany(targetEntity = StructureSet.class)
	@JoinColumn(name = "series_id")
	private List<StructureSet> structureSets;

	@OneToMany(targetEntity = Slice.class)
	@JoinColumn(name = "series_id")
	private List<StructureSet> slices;
	
	public Series(){
		
	}

	public Series(int seriesId, int studyId, Date time, String comments,
			String institution, String modality, List<StructureSet> structureSets) {
		this.seriesId = seriesId;
		this.studyId = studyId;
		this.time = time;
		this.comments = comments;
		this.institution = institution;
		this.modality = modality;
		this.structureSets = structureSets;
	}

	public int getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	public int getStudyId() {
		return studyId;
	}

	public void setStudyId(int studyId) {
		this.studyId = studyId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public List<StructureSet> getStructureSets() {
		return structureSets;
	}

	public void setStructureSets(List<StructureSet> structureSets) {
		this.structureSets = structureSets;
	}

	public List<StructureSet> getSlices() {
		return slices;
	}

	public void setSlices(List<StructureSet> slices) {
		this.slices = slices;
	}
}
