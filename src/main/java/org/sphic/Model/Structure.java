package org.sphic.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="structure")
public class Structure {
    @Id
    @Column(name="id")
    private int structureId;

    @Column(name="structure_set_id")
    private int structureSetId;

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

    @OneToMany(targetEntity = Contour.class)
    @JoinColumn(name = "structure_id")
    private List<Contour> contours;


    public Structure(int structureId, int structureSetId, String name, Date created, Date updated, Date deleted, String description, List<Contour> contours) {
        this.structureId = structureId;
        this.structureSetId = structureSetId;
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

    public int getStructureSetId() {
        return structureSetId;
    }

    public void setStructureSetId(int structureSetId) {
        this.structureSetId = structureSetId;
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
}