package org.sphic.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="contour")
@SecondaryTable(name="structure")
public class Contour {

    @Id
    @Column(name="id")
    private int contourId;

    @Column(name="structure_id")
    private int structureId;

    @Column(name="name")
    private String name;

    @Column(name = "color_id", table = "structure")
//    @JoinColumn(name = "structure_id")
    private int colorId;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;

    @Column(name="deleted")
    private Date deleted;

    @Column(name="description")
    private String description;

    @Column(name="data")
    private String data;

    public Contour(int contourId, int structureId, String name, int colorId, Date created, Date updated, Date deleted, String description) {
        this.contourId = contourId;
        this.structureId = structureId;
        this.name = name;
        this.colorId = colorId;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.description = description;
    }

    public Contour() {
    }

    public int getContourId() {
        return contourId;
    }

    public void setContourId(int contourId) {
        this.contourId = contourId;
    }

    public int getStructureId() {
        return structureId;
    }

    public void setStructureId(int structureId) {
        this.structureId = structureId;
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

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
