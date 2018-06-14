package com.developer.server.authenticplaces.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "snapshots")
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_photo")
    private User authorPhoto;

    @Column(name = "upload_date")
    private Timestamp uploadDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "marker_id")
    private Marker marker;

    public Snapshot() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getAuthorPhoto() {
        return authorPhoto;
    }

    public void setAuthorPhoto(User authorPhoto) {
        this.authorPhoto = authorPhoto;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
