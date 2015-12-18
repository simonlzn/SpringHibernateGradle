package org.sphic.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="structure")
@JsonIgnoreProperties(value={"structureSet"})
public class Structure {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int structureId;

    @ManyToOne(targetEntity = StructureSet.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "structure_set_id")
    private StructureSet structureSet;

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

    @Column(name="color_id")
    public int colorId;

    @Column(name="roi_number")
    private int ROINumber;

    @Column(name="roi_color")
    private String ROIcolor;

    @OneToMany(targetEntity = Contour.class, mappedBy = "structure", cascade = CascadeType.ALL)
    private List<Contour> contours;

    public Structure(int ROINumber, String name, Date created, Date updated, Date deleted, String description, List<Contour> contours) {
        this.ROINumber = ROINumber;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.description = description;
        this.contours = contours;
    }

    public Structure() {
    }

    public int getStructureId() {
        return structureId;
    }

    public void setStructureId(int structureId) {
        this.structureId = structureId;
    }

    public StructureSet getStructureSet() {
        return structureSet;
    }

    public void setStructureSet(StructureSet structureSet) {
        this.structureSet = structureSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
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

    public List<Contour> getContours() {
        return contours;
    }

    public void setContours(List<Contour> contours) {
        this.contours = contours;
    }

    public int getROINumber() {
        return ROINumber;
    }

    public void setROINumber(int ROINumber) {
        this.ROINumber = ROINumber;
    }

    public String getROIcolor() {
        return ROIcolor;
    }

    public void setROIcolor(String ROIcolor) {
        this.ROIcolor = ROIcolor;
    }
}
