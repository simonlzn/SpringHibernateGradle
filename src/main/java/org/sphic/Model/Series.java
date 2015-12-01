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
	
	@Column(name="created")
	private Date created;

	@Column(name="updated")
	private Date updated;

	@Column(name="deleted")
	private Date deleted;
	
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getDeleted() {
		return deleted;
	}

	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}
}
