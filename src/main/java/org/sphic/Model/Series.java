package org.sphic.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "series")
public class Series {
    @Id
    @Column(name = "id")
    private int seriesId;

//    @Column(name = "study_id")
//    private int studyId;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "deleted")
    private Date deleted;

    @Column(name = "comments")
    private String comments;

    @Column(name = "seriesInsUID")
    private String seriesInsUID;

    @Column(name = "seriesNumber")
    private int seriesNumber;

    @Column(name = "seriesDate")
    private String seriesDate;

    @Column(name = "seriesTime")
    private String seriesTime;

    @Column(name = "seriesDescrip")
    private String seriesDescrip;

    @Column(name = "modality")
    private String modality;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "manufctModel")
    private String manufctModel;

    @ManyToOne(targetEntity = Study.class)
    @JoinColumn(name ="study_id")
    private Study study;

    @OneToMany(targetEntity = StructureSet.class)
    @JoinColumn(name = "series_id")
    private List<StructureSet> structureSets;

    @OneToMany(targetEntity = Slice.class)
    @JoinColumn(name = "series_id")
    private List<StructureSet> slices;

    @OneToOne(mappedBy = "series", targetEntity = ImageSeries.class, cascade = CascadeType.ALL)
    private ImageSeries imageSeries;

    @OneToMany(targetEntity = Images.class)
    @JoinColumn(name = "series_id")
    private List<Images> Images;

    public Series() {

    }

    public Series(int seriesId, int studyId, String seriesInsUID, int seriesNumber, String seriesDate, String seriesTime, String seriesDescrip,
                  String modality, String manufacturer, String manufctModel,Date creted, Date updated, Date deleted, String comments) {

        this.seriesId = seriesId;
//        this.studyId = studyId;
        this.seriesInsUID = seriesInsUID;
        this.seriesNumber = seriesNumber;
        this.seriesDate = seriesDate;
        this.seriesTime = seriesTime;
        this.seriesDescrip = seriesDescrip;
        this.modality = modality;
        this.manufacturer = manufacturer;
        this.manufctModel = manufctModel;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

//    public int getStudyId() {
//        return studyId;
//    }
//
//    public void setStudyId(int studyId) {
//        this.studyId = studyId;
//    }

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSeriesInsUID() {
        return seriesInsUID;
    }

    public void setSeriesInsUID(String seriesInsUID) {
        this.seriesInsUID = seriesInsUID;
    }

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getSeriesDate() {
        return seriesDate;
    }

    public void setSeriesDate(String seriesDate) {
        this.seriesDate = seriesDate;
    }

    public String getSeriesTime() {
        return seriesTime;
    }

    public void setSeriesTime(String seriesTime) {
        this.seriesTime = seriesTime;
    }

    public String getSeriesDescrip() {
        return seriesDescrip;
    }

    public void setSeriesDescrip(String seriesDescrip) {
        this.seriesDescrip = seriesDescrip;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufctModel() {
        return manufctModel;
    }

    public void setManufctModel(String manufctModel) {
        this.manufctModel = manufctModel;
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

    public ImageSeries getImageSeries() {
        return imageSeries;
    }

    public void setImageSeries(ImageSeries imageSeries) {
        this.imageSeries = imageSeries;
    }

    public List<org.sphic.Model.Images> getImages() {
        return Images;
    }

    public void setImages(List<org.sphic.Model.Images> images) {
        Images = images;
    }
}