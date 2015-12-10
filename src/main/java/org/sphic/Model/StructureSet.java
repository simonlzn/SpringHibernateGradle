package org.sphic.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="structure_set")
public class StructureSet {
	@Id
	@Column(name="id")
	private int structureSetId;

	@ManyToOne(targetEntity = Series.class)
	@JoinColumn(name = "series_id")
	private Series series;

	@Column(name="name")
	private String name;

	@Column(name="created")
	private Date created;

	@Column(name="updated")
	private Date updated;

	@Column(name="deleted")
	private Date deleted;

	@Column(name="description")
	private String description;


	@OneToMany(targetEntity = Structure.class, mappedBy = "structureSet")
	private List<Structure> structures;

	public StructureSet(){

	}

	public StructureSet(int structureSetId, int seriesId, String name, Date created, Date updated, Date deleted, String description, List<Structure> structures) {
		this.structureSetId = structureSetId;
		this.name = name;
		this.created = created;
		this.updated = updated;
		this.deleted = deleted;
		this.description = description;
		this.structures = structures;
	}

	public int getStructureSetId() {
		return structureSetId;
	}

	public void setStructureSetId(int structureSetId) {
		this.structureSetId = structureSetId;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Structure> getStructures() {
		return structures;
	}

	public void setStructures(List<Structure> structures) {
		this.structures = structures;
	}
}
