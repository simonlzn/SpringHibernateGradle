package org.sphic.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="slice")
public class Slice {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int sliceId;

    @ManyToOne(targetEntity = Series.class)
    @JoinColumn(name = "series_id")
    private Series series;

    @Column(name="view")
    private char view;

    @Column(name="number")
    private int number;

    @Column(name="rows")
    private int row;

    @Column(name="columns")
    private int column;

    @Column(name="rowspacing")
    private double rowspacing;

    @Column(name="columnspacing")
    private double columnspacing;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;

    @Column(name="deleted")
    private Date deleted;

    @Column(name="comments")
    private String comments;

    @Column(name="data")
    private String data;

    @OneToMany(targetEntity = Contour.class, mappedBy = "slice")
    private List<Contour> contours;

    public Slice() {
    }

    public Slice(char view, int number, int row, int column, double rowspacing, double columnspacing, Date created, Date updated, Date deleted, String comments, String data) {
        this.view = view;
        this.number = number;
        this.row = row;
        this.column = column;
        this.rowspacing = rowspacing;
        this.columnspacing = columnspacing;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.comments = comments;
        this.data = data;
    }

    public int getSliceId() {
        return sliceId;
    }

    public void setSliceId(int sliceId) {
        this.sliceId = sliceId;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Contour> getContours() {
        return contours;
    }

    public void setContours(List<Contour> contours) {
        this.contours = contours;
    }

    public char getView() {
        return view;
    }

    public void setView(char view) {
        this.view = view;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public double getRowspacing() {
        return rowspacing;
    }

    public void setRowspacing(int rowspacing) {
        this.rowspacing = rowspacing;
    }

    public double getColumnspacing() {
        return columnspacing;
    }

    public void setColumnspacing(int columnspacing) {
        this.columnspacing = columnspacing;
    }
}
