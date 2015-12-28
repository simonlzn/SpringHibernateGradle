package org.sphic.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "series")
@JsonIgnoreProperties(value={"study", "structureSets", "slices"})
public class Series {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seriesId;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "deleted")
    private Date deleted;

    @Column(name = "name")
    private String name;

    @Column(name = "series_ins_id")
    private String series_ins_id;

    @Column(name = "series_ins_uid")
    private String seriesInsUID;

    @Column(name = "series_number")
    private int seriesNumber;

    @Column(name = "series_date_time")
    private Date seriesDateTime;

    @Column(name = "description")
    private String description;

    @Column(name = "modality")
    private String modality;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "manufct_model")
    private String manufctModel;

    @ManyToOne(targetEntity = Study.class, cascade = CascadeType.ALL)
    @JoinColumn(name ="study_id")
    private Study study;

    @OneToMany(targetEntity = StructureSet.class, mappedBy = "series", cascade = CascadeType.ALL)
    private List<StructureSet> structureSets;

    @OneToMany(targetEntity = Slice.class, mappedBy = "series",cascade = CascadeType.ALL)
    private List<Slice> slices;

    @OneToOne(mappedBy = "series", targetEntity = ImageSeries.class, cascade = CascadeType.ALL)
    private ImageSeries imageSeries;

    public Series() {

    }

    public Series(String series_ins_id, String seriesInsUID, int seriesNumber, Date seriesDateTime, String description,
                  String modality, String manufacturer, String manufctModel, Date created, Date updated, Date deleted, String name) {

        this.series_ins_id = series_ins_id;
        this.seriesInsUID = seriesInsUID;
        this.seriesNumber = seriesNumber;
        this.seriesDateTime = seriesDateTime;
        this.description = description;
        this.modality = modality;
        this.manufacturer = manufacturer;
        this.manufctModel = manufctModel;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSeriesDateTime() {
        return seriesDateTime;
    }

    public void setSeriesDateTime(Date seriesDateTime) {
        this.seriesDateTime = seriesDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Slice> getSlices() {
        return slices;
    }

    public void setSlices(List<Slice> slices) {
        this.slices = slices;
    }

    public ImageSeries getImageSeries() {
        return imageSeries;
    }

    public void setImageSeries(ImageSeries imageSeries) {
        this.imageSeries = imageSeries;
    }
}