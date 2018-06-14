package com.developer.server.authenticplaces.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "markers")
public class Marker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "marker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<Snapshot> snapshots;

    @OneToMany(mappedBy = "marker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<Comment> comments;

    public Marker() {
    }

    public Marker(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", creator=" + creator +
                ", snapshots=" + snapshots +
                ", comments=" + comments +
                '}';
    }
}
