package org.sphic.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Entity
@Table(name="contour")
@JsonIgnoreProperties(value={"structure","slice"})
public class Contour {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contourId;

    @Column(name="name")
    private String name;

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Slice getSlice() {
        return slice;
    }

    public void setSlice(Slice slice) {
        this.slice = slice;
    }

    @ManyToOne(targetEntity = Structure.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "structure_id")
    private Structure structure;

    @ManyToOne(targetEntity = Slice.class)
    @JoinColumn(name = "slice_id")
    private Slice slice;


    @Transient
    private char view;
    public char getView(){
        return slice.getView();
    }

    public void setView(char view){
        slice.setView(view);
    }

    @Transient
    private int number;
    public int getNumber(){
        return slice.getNumber();
    }

    public void setNumber(int number){
        slice.setNumber(number);
    }

    @Transient
    private int colorId;
    public int getColorId(){
        return structure.colorId;
    }

    public void setColorId(int colorId){
        structure.colorId = colorId;
    }

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;

    @Column(name="deleted")
    private Date deleted;

    @Column(name="description")
    private String description;

    @Column(name="data")
    private String contourData;

    private String SOPInstanceUID;

    public Contour(String SOPInstanceUID, Date created, Date updated, Date deleted, String description,
                   String contourData) {
        this.SOPInstanceUID = SOPInstanceUID;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.description = description;
        this.contourData = contourData;
    }

    public Contour() {
    }

    public int getContourId() {
        return contourId;
    }

    public void setContourId(int contourId) {
        this.contourId = contourId;
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

    public String getContourData() {
        return contourData;
    }

    public void setContourData(String contourData) {
        this.contourData = contourData;
    }

    public String getSOPInstanceUID() {
        return SOPInstanceUID;
    }

    public void setSOPInstanceUID(String SOPInstanceUID) {
        this.SOPInstanceUID = SOPInstanceUID;
    }
}
