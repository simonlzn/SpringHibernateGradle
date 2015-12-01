package org.sphic.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="contour")
public class Contour {

    @Id
    @Column(name="id")
    private int contourId;

    @Column(name="name")
    private String name;

    @ManyToOne(targetEntity = Structure.class)
    private Structure structure;

    @ManyToOne(targetEntity = Slice.class)
    private Slice slice;


    @Transient
    private char view;
    public int getView(){
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
    private String data;

    public Contour(int contourId, String name, Date created, Date updated, Date deleted, String description) {
        this.contourId = contourId;
        this.name = name;
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

//    public int getColorId() {
//        return colorId;
//    }
//
//    public void setColorId(int colorId) {
//        this.colorId = colorId;
//    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
